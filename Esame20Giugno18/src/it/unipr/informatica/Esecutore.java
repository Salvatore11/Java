package it.unipr.informatica;

public interface Esecutore {
	public void esegui() throws InterruptedException;
	public void aggiungiAttività(Attività a);
	public int getID();
}
