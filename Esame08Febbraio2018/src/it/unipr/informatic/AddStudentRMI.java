package it.unipr.informatic;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AddStudentRMI {
	DatabaseManager databaseManager= new DatabaseManager();
	
	
	public void startServer() {
		try {
			LocateRegistry.createRegistry(2000);
			Registry registry= LocateRegistry.getRegistry(2000);
			
			Remote esportato1= UnicastRemoteObject.exportObject(databaseManager, 2000);
			
			registry.bind("database", esportato1);
			
			System.out.println("Database disponibile");
			
			
		}catch(Throwable throwable) {
			throwable.printStackTrace();
		}
	}

}
