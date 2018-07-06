package it.unipr.informatica.esercizio9.modello;

import it.unipr.informatica.esercizio9.persistente.Identificativo;
import it.unipr.informatica.esercizio9.persistente.Persistente;

public interface Studente extends Persistente {	
	@Identificativo
	public int getMatricola();
	
	public String getCognome();
	
	public void setCognome(String cognome);
	
	public String getNome();
	
	public void setNome(String nome);
}
