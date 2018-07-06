package it.unipr.informatic;

public class Attività2 implements AttivitàInterface{
	
	DatabaseManager databaseManager= new DatabaseManager();
	@Override
	public void nomeAttività() {
		databaseManager.insertRequest("salvo","latino");
	}

}
