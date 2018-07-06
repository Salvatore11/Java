package it.unipr.informatica.esercizio9.persistente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemoriaOggetti {
	private static final String SET = "set", GET = "get";
	
	public abstract <T extends Persistente> T crea(Class<T> classe) throws PersistenteException;
	
	public abstract <T extends Persistente> List<T> getAll(Class<T> classe) throws PersistenteException;

	protected abstract void salva(Persistente persistente) throws PersistenteException; // permette di salvare l'oggetto persistente
	
	protected byte[] serializza(Serializable oggetto) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// implica un flusso di output in cui i dati sono scritti in un array di byte
			
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			
		objectOutputStream.writeObject(oggetto);
			
		return byteArrayOutputStream.toByteArray();
	}
	
	protected Serializable deserializza(byte[] dati) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dati);
			
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			
		return (Serializable)objectInputStream.readObject();
	}
	
	protected <T extends Persistente> T creaProxy(Class<T> classe, OggettoPersistente risultato) {
		@SuppressWarnings("unchecked")
		T t = (T)Proxy.newProxyInstance(
				classe.getClassLoader(), 
				new Class[] { classe },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						String nomeMetodo = method.getName();
						
						if(nomeMetodo.startsWith(GET)) {
							Annotation annotazione = method.getAnnotation(Identificativo.class);
							
							if(annotazione != null)
								return risultato.getIdentificativo();
							
							String chiave = nomeMetodo.substring(GET.length());
							
							Object valore = risultato.get(chiave);
							
							return valore;
						}
						
						if(nomeMetodo.startsWith(SET)) {
							String chiave = nomeMetodo.substring(SET.length());
							
							risultato.put(chiave, args[0]);
							
							salva(risultato);
							
							return null;
						}
													
						Class<?> classeRisultato = risultato.getClass();

						Method metodoRisultato = classeRisultato.getMethod(
								method.getName(), 
								method.getParameterTypes());
						
						Object risultatoChiamata = metodoRisultato.invoke(risultato, args);
						
						return risultatoChiamata;
					}
				});
		
		return t;
	}
	
	protected <T> void verifica(Class<T> classe) {
		if(!classe.isInterface())
			throw new IllegalArgumentException();
		
		Map<String, Class<?>> tipi = new HashMap<>();
		
		Method[] metodi = classe.getMethods();
		
		for(Method metodo : metodi) {
			if(metodo.getAnnotation(Identificativo.class) != null && metodo.getReturnType() != int.class)
				throw new IllegalArgumentException();

			String nomeMetodo = metodo.getName();
			
			if(nomeMetodo.startsWith(GET)) {
				if(metodo.getParameterCount() != 0)
					throw new IllegalArgumentException();
				
				String nome = nomeMetodo.substring(GET.length());
				
				Class<?> tipo = tipi.get(nome);
				
				Class<?> nuovoTipo = metodo.getReturnType();
				
				if(tipo != null && tipo != nuovoTipo)
					throw new IllegalArgumentException();
				
				tipi.put(nome, nuovoTipo);
			}
			
			if(nomeMetodo.startsWith(SET)) {
				if(metodo.getReturnType() != Void.TYPE)
					throw new IllegalArgumentException();
				
				if(metodo.getParameterCount() != 1)
					throw new IllegalArgumentException();

				String nome = nomeMetodo.substring(SET.length());

				Class<?> tipo = tipi.get(nome);
				
				Class<?> nuovoTipo = metodo.getParameterTypes()[0];
				
				if(tipo != null && tipo != nuovoTipo)
					throw new IllegalArgumentException();
				
				tipi.put(nome, nuovoTipo);
			}
		}
	}
}
