package it.unipr.informatica.esercizio8.modello;

import it.unipr.informatica.esercizio8.persistente.Persistente;
import it.unipr.informatica.esercizio8.persistente.Readonly;

public interface Studente extends Persistente {// e come se a persistente fossero aggiunti i seguenti metodi
	@Readonly //permette di specificare che una classe è di sola lettura
	public int getMatricola();
	
	@Readonly
	public String getCognome();
	
	public void setCognome(String cognome);
	
	@Readonly
	public String getNome();
	
	public void setNome(String nome);
}
