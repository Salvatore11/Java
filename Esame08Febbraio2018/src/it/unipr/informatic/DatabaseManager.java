package it.unipr.informatic;

import java.rmi.Remote;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseManager implements Remote, ComponentInterface{

	Scheduler scheduler = Scheduler.getInstance();
	

	public DatabaseManager() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		}catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	
	public void insertRequest(String cognome, String nome) {
		Request request= new NotNotifyRequest(26,this,attività) {
		@Override
		public void exec() {

				if(cognome==null || "".equals(cognome))
					throw new IllegalArgumentException();
				if(nome== null || "".equals(nome))
					throw new IllegalArgumentException();

				try(
						Connection connection= DriverManager.getConnection("jdbc:derby://localhost/C:\\Users\\utente\\Desktop\\java\\Derby DB\\Ateneo");
						PreparedStatement statement= connection.prepareStatement("INSERT INTO STUDENTI(COGNOME, NOME) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
						){

					statement.setString(1, cognome);
					statement.setString(2, nome);

					if(statement.execute())
						throw new IllegalArgumentException();

					statement.close();

				}catch(Throwable throwable) {
					throwable.printStackTrace();
				}
			}

		};
		
	}
	

}
