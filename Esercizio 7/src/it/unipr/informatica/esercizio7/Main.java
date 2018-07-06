package it.unipr.informatica.esercizio7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//ClientDriver
public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch(Throwable throwable) {
			throwable.printStackTrace();
			
			return;
		}
		//"jdbc:derby://localhost/Ateneo"
		try (
			Connection connessione = DriverManager.getConnection("jdbc:derby://localhost/C:\\Users\\utente\\Desktop\\java\\Derby DB\\Ateneo");
						
			Statement statement = connessione.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTI");
		) {
			while(resultSet.next()) {
				int matricola = resultSet.getInt("MATRICOLA");
				
				String cognome = resultSet.getString("COGNOME");
				
				String nome = resultSet.getString("NOME");
				
				System.out.println(matricola + " " + cognome + " " + nome);
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
