package it.unipr.informatica.esercizio9.persistente;

@SuppressWarnings("serial")
public class PersistenteException extends Exception {
	public PersistenteException(Throwable nested) {
		super(nested);
	}
}
