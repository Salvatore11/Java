package it.unipr.informatica.esercizio8.modello.impl;

import it.unipr.informatica.esercizio8.modello.Studente;
import it.unipr.informatica.esercizio8.persistente.PersistenteBase;

public class StudenteImpl extends PersistenteBase implements Studente {
	private static final long serialVersionUID = 20171116;

	private String cognome;
	
	private String nome;

	public StudenteImpl(int matricola) {
		super(matricola); //il super invoca il costruttore della classe genitore (nel nostro caso di persistente).
						//quindi deve avere i parametri attuali corrispondenti in numero e tipo a quelli formali 
		
		this.cognome = "Cognome"; 
		
		this.nome = "Nome";
	}

	public StudenteImpl(int matricola, String cognome, String nome) {
		super(matricola);
		
		this.cognome = cognome;

		this.nome = nome;
	}

	@Override
	public int getMatricola() {
		return getIdentificativo();
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
}
