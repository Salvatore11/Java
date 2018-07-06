package it.unip.informatica.esercizio12.database;

import it.unipr.informatica.esercizio12.modello.Utente;

public class DatabaseUtente implements Utente{
	
	String nome;
	
	public DatabaseUtente(String nome) {
		if(nome == null || "".equals(nome))
			throw new IllegalArgumentException();
		
		this.nome= nome;
	}
	
	
	@Override
	public String getNome() {
		return nome;
	}
	
	
}
