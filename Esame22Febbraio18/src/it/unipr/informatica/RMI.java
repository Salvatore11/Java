package it.unipr.informatica;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

public class RMI {
	
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(2000);
			
			Registry registry= LocateRegistry.getRegistry(2000);
			
			SistemaDiControllo sc= SistemaDiControllo.getInstance();
			
			RemoteObject object= UnicastRemoteObject.exportObject(sc);
			
			registry.bind("quello", object);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
