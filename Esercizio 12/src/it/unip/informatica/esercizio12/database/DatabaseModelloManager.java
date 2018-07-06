package it.unip.informatica.esercizio12.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unip.informatica.esercizio12.Configurazione;
import it.unipr.informatica.esercizio12.modello.ModelloException;
import it.unipr.informatica.esercizio12.modello.ModelloManager;
import it.unipr.informatica.esercizio12.modello.Studente;

public class DatabaseModelloManager implements ModelloManager{
	
	private String url;
	
	public DatabaseModelloManager() {
		try {
			
			Configurazione configurazione = Configurazione.getConfigurazione();
			
			this.url= configurazione.getStringa("it.unipr.informatica.esercizio12.database.url");
			
			String nomeClasse= configurazione.getStringa("it.unipr.informatica.esercizio12.database.driver");
			
			Class.forName(nomeClasse);
			
		}catch(Throwable throwable) {
			throw new IllegalArgumentException(throwable);
		}
	}
	
	
	@Override
	public List<Studente> getAllStudenti() throws ModelloException{
		try(
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			//nella ResultSet metto il risultato della query
			ResultSet resultSet= statement.executeQuery("SELECT * FROM STUDENTI ORDER BY MATRICOLA DESC");
			){
			
			List<Studente> studenti = new ArrayList<>() ;
			//next sposta il cursore in avanti di una riga dalla posizione corrente
			while(resultSet.next()) {
				
				int matricola= resultSet.getInt("MATRICOLA");
				
				String cognome= resultSet.getString("COGNOME");
				
				String nome= resultSet.getString("NOME");
				
				Studente studente = new DatabaseStudente(matricola, cognome, nome);
				
				studenti.add(studente);
			}
			
			return studenti;
			
		}catch(Throwable throwable) {
			throw new ModelloException(throwable);
		}	
	}
	
	
	@Override
	public Studente getStudente(int matricola) throws ModelloException{
		try(
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM STUDENTI WHERE MATRICOLA=?");
			){
			//al primo punto Interrogativo sostritutisce matricola""
			statement.setInt(1, matricola);
			
			ResultSet resultSet= statement.executeQuery();
			
			Studente studente = null;
			
			while(resultSet.next()) {
				
				String cognome= resultSet.getString("COGNOME");
				
				String nome= resultSet.getString("NOME");
				
				studente=new DatabaseStudente(matricola, cognome, nome);
			}
			return studente;
		}catch(Throwable throwable) {
			throw new ModelloException(throwable);
		}
	}
	
	@Override
	public Studente nuovoStudente(String cognome, String nome) throws ModelloException{
		
		if(cognome== null || "".equals(cognome))
			throw new IllegalArgumentException();
		if(nome == null || "".equals(nome))
			throw new IllegalArgumentException();
		
		try(
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement statement= connection.prepareStatement("INSERT INTO STUDENTI(COGNOME, NOME) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);					
			){
			
			statement.setString(1, cognome);
			
			statement.setString(2, nome);
			
			if(statement.execute())
				throw new IllegalArgumentException();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			if(!resultSet.next()) {
				resultSet.close();
				
				throw new IllegalArgumentException();
			}
			
			int matricola= resultSet.getInt(1);
			
			Studente studente = new DatabaseStudente(matricola, cognome, nome);
			
			resultSet.close();
			
			return studente;
		}catch(Throwable throwable) {
			throw new ModelloException(throwable);
		}
	}
	
	@Override
	public Studente aggiornaStudente(int matricola, String cognome, String nome) throws ModelloException {
		
		if(matricola < 1)
			throw new IllegalArgumentException();
		
		if(cognome== null || "".equals(cognome))
			throw new IllegalArgumentException();
		
		if(nome == null || "".equals(nome))
			throw new IllegalArgumentException();
		
		try(
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement statement = connection.prepareStatement("UPDATE STUDENTI SET COGNOME=? NOME=? WHERE MATRICOLA=?");
			){
			
			statement.setString(1, cognome);
			statement.setString(2, nome);
			statement.setInt(3, matricola);
			
			if(statement.execute())
				throw new IllegalArgumentException();
			
			Studente studente= new DatabaseStudente(matricola, cognome, nome);
			
			return studente;
			
		}catch (Throwable throwable) {
			throw new ModelloException(throwable);
		}
	}
	
	
	@Override
	public boolean rimuoviStudente(int matricola) throws ModelloException{
		
		if(matricola < 1)
			throw new IllegalArgumentException();
		
		try(
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement statement= connection.prepareStatement("DELETE FROM STUDENTI WHERE MATRICOLA=?");
			){
			
			statement.setInt(1, matricola);
			
			if(statement.execute())
				return false;
			
			return statement.getUpdateCount()>0;
		}catch(Throwable throwable) {
			throw new ModelloException(throwable);
		}
	}
	
	@Override
	public boolean verificaUtente(String nome, String password) throws ModelloException{
		if(nome== null || "".equals(nome))
			throw new IllegalArgumentException();
		
		
		if(password == null)
			throw new IllegalArgumentException();
		
		try(
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement statement= connection.prepareStatement("SELECT ID FROM UTENTI WHERE NOME=? AND PAROLA=?");
			){
			
			statement.setString(1, nome);
			
			statement.setString(2, codifica(password));
			
			ResultSet resultSet= statement.executeQuery();
			//prende la rica in cui si trova l'elemento cercato
			boolean risultato= resultSet.next();
			
			resultSet.close();
			
			return risultato;
			
		}catch(Throwable throwable) {
			throw new IllegalArgumentException(throwable);
		}
	}
	
	
	
	private String codifica(String password) throws NoSuchAlgorithmException{
		if(password == null)
			throw new IllegalArgumentException();
		
		MessageDigest md5Digest= MessageDigest.getInstance("MD5");
		
		md5Digest.update(password.getBytes());
		
		byte[] hash= md5Digest.digest();
		
		StringBuilder builder = new StringBuilder();
		
		for(byte b: hash) 
			builder.append(String.format("%02x", b));
			
		return builder.toString();

	}
		

}
