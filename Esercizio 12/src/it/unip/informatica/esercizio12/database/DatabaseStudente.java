package it.unip.informatica.esercizio12.database;

import it.unipr.informatica.esercizio12.modello.Studente;

public class DatabaseStudente implements Studente{
	
	private int matricola;
	
	private String cognome;
	
	private String nome;
	
	public DatabaseStudente(int matricola, String cognome, String nome) {
		
		if(matricola < 1 )
			throw new IllegalArgumentException();
		
		if(cognome == null || "".equals(cognome))
			throw new IllegalArgumentException();
		
		if(nome == null || "".equals(nome))
			throw new IllegalArgumentException();
		
		this.matricola= matricola;
		
		this.cognome= cognome;
		
		this.nome= nome;
	}
	
	@Override
	public int getMatricola() {
		return matricola;
	}
	
	@Override
	public String getCognome() {
		return cognome;
	}
	
	@Override
	public String getNome() {
		return nome;
	}

	
}
