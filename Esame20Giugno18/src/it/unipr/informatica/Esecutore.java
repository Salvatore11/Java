package it.unipr.informatica;

public interface Esecutore {
	public void esegui() throws InterruptedException;
	public void aggiungiAttivit�(Attivit� a);
	public int getID();
}
