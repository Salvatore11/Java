package it.unipr.informatica.esercizio5;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.unipr.informatica.esercizio5.modello.GestorePunti;
import it.unipr.informatica.esercizio5.modello.Sommatore;
import it.unipr.informatica.esercizio5.modello.impl.GestorePuntiImpl;
import it.unipr.informatica.esercizio5.modello.impl.SommatoreImpl;

public class Server {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2000);

			Sommatore s1 = new SommatoreImpl();
			
			Remote esportato1 = UnicastRemoteObject.exportObject(s1, 0);
						
			registry.rebind("oggetto1", esportato1);
			
			System.out.println("Oggetto 1 disponibile");
			
			GestorePunti s2 = new GestorePuntiImpl();

			Remote esportato2 = UnicastRemoteObject.exportObject(s2, 0);
			
			//rebind= rinnova il nome specificato in un nuovo oggetto remoto
			registry.rebind("oggetto2", esportato2);
			
			System.out.println("Oggetto 2 disponibile");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
