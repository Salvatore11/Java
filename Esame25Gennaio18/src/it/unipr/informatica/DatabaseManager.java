package it.unipr.informatica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseManager {
	
	public DatabaseManager() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		}catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	public void insertTemperature(float value) {
		try(
			Connection connection = DriverManager.getConnection("jdbc:derby://localhost/C:\\Users\\utente\\Desktop\\java\\Derby DB\\Ateneo");
			PreparedStatement statement= connection.prepareStatement("INSERT INTO TEMPERATURE(GRADI) VALUES=?", Statement.RETURN_GENERATED_KEYS);
			)
			{
			statement.setFloat(1, value);
			if(statement.execute()) {
				throw new IllegalArgumentException();
				
			}
			
			statement.close();
			
		}catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public void insertPurity(int value) {
		try(
				Connection connection = DriverManager.getConnection("jdbc:derby://localhost/C:\\Users\\utente\\Desktop\\java\\Derby DB\\Ateneo");
				PreparedStatement statement= connection.prepareStatement("INSERT INTO PUREZZA(GRADI) VALUES=?", Statement.RETURN_GENERATED_KEYS);
			)
				{
				statement.setInt(1, value);
				if(statement.execute()) {
					throw new IllegalArgumentException();
					
				}
				
				statement.close();
				
			}catch (Exception e) {
				e.printStackTrace();	
			}
	}

}
