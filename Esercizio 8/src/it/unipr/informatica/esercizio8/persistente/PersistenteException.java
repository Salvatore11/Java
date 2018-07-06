package it.unipr.informatica.esercizio8.persistente;

@SuppressWarnings("serial")
public class PersistenteException extends Exception {
	public PersistenteException(Throwable nested) {
		super(nested);
	}
}
