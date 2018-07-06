package it.unipr.informatica.esercizio4.active;

public class Futuro<T> {
	private T dato;
	
	private Throwable errore; // con throwable errore è automaticmanete settato a null
	
	public Futuro() {
		this.dato = null; // inizializzo il dato a null perchè dovrà contenere il valore che gli passo
	}

	//setto le variabili
	public synchronized void set(T valore) {
		if(valore == null)
			throw new IllegalArgumentException();
		
		if(dato != null)
			throw new IllegalStateException();
			
		dato = valore;
		
		notifyAll();
	}
	
	public synchronized T get() throws InterruptedException {
		while(dato == null && errore == null) // se sia il dato che l'errore non sono ancora stati settati allora mi metto in attesa
			wait();
		
		return dato;
	}
	
	public synchronized void setErrore(Throwable throwable) {
		if(throwable == null)
			throw new IllegalArgumentException();
		
		if(errore != null)
			throw new IllegalStateException();
			
		errore = throwable;
		
		notifyAll();
	}
	
	public synchronized Throwable getErrore() throws InterruptedException {
		while(errore == null) // se la mia variabile errore è ancora settata a null allora mi metto in attesa
			wait();
		
		return errore;
	}
}
