package it.unipr.informatica.esercizio8.persistente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public abstract class MemoriaOggetti {
	public abstract <T extends Persistente> T crea(Class<T> classe) throws PersistenteException;

	public abstract <T extends Persistente> List<T> getAll(Class<T> classe) throws PersistenteException;

	protected abstract void salva(Persistente persistente) throws PersistenteException;

	protected byte[] serializza(Serializable oggetto) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			
		objectOutputStream.writeObject(oggetto);
			
		return byteArrayOutputStream.toByteArray(); //crea un array di byte appena assegnato
	}
	
	protected Serializable deserializza(byte[] dati) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dati);
			
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			
		return (Serializable)objectInputStream.readObject();
	}
	
	protected <T> T creaOggetto(Class<T> classe, int id) {
		try {
			Package pacchetto = classe.getPackage(); // Ottieni tutti i pacchetti attualmente noti per l' ClassLoader istanza del chiamante
			
			String nomePacchetto = pacchetto.getName();
			
			String nomeClasse = classe.getSimpleName();
			
			String nomeClasseRisultato = nomePacchetto + ".impl." + nomeClasse + "Impl";
			
			Class<?> classeRisultato = Class.forName(nomeClasseRisultato);
			
			Constructor<?> costruttore = classeRisultato.getConstructor(new Class[] { int.class });
			
			@SuppressWarnings("unchecked")
			T risultato = (T)costruttore.newInstance(new Object[] { id });
			
			return risultato;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		
		return null;		
	}
	
	protected <T extends Persistente> T creaProxy(Class<T> classe, T risultato) {
		@SuppressWarnings("unchecked")
		T t = (T)Proxy.newProxyInstance(
				classe.getClassLoader(), 
				new Class[] { classe },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Class<?> classeRisultato = risultato.getClass();
						
						Method metodoRisultato = classeRisultato.getMethod(
								method.getName(), 
								method.getParameterTypes());
						
						Object risultatoChiamata = metodoRisultato.invoke(risultato, args);

						Annotation annotation = method.getAnnotation(Readonly.class);
						
						if(annotation == null)
							salva(risultato);
						
						return risultatoChiamata;
					}
				});
		
		return t;
	}
}
