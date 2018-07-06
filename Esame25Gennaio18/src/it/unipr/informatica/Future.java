package it.unipr.informatica;

public class Future<T> {
	
	private T dato;
	
	public Future(){
		this.dato = null;
	}
	
	
	public synchronized void set(T valore) {
		if(valore == null)
			throw new IllegalArgumentException();
		if(dato != null)
			throw new IllegalStateException();
		
		this.dato=valore;
		
		notifyAll();
	}
	
	public synchronized T get() throws InterruptedException{
		if(dato == null)
			wait();
		
		return dato;
	}

}
