package it.unipr.informatica.esercizio12.modello;

import java.util.List;

public interface ModelloManager {
	
	public boolean verificaUtente(String nome, String password) throws ModelloException;
	
	public List<Studente> getAllStudenti() throws ModelloException;
	
	public Studente getStudente(int matricola) throws ModelloException;
	
	public Studente nuovoStudente(String cognome, String nome) throws ModelloException;
	
	public Studente aggiornaStudente(int matricola, String cognome, String nome) throws ModelloException;
	
	public boolean rimuoviStudente(int matricola) throws ModelloException;
}
