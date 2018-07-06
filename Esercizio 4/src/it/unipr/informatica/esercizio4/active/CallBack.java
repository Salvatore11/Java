package it.unipr.informatica.esercizio4.active;

public interface CallBack<T> {
	public void risultato(T risultato);
	
	public void fallimento(Throwable throwable);
}
