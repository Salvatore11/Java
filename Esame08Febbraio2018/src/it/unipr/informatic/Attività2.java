package it.unipr.informatic;

public class Attivit�2 implements Attivit�Interface{
	
	DatabaseManager databaseManager= new DatabaseManager();
	@Override
	public void nomeAttivit�() {
		databaseManager.insertRequest("salvo","latino");
	}

}
