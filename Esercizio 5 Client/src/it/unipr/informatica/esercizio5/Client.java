package it.unipr.informatica.esercizio5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unipr.informatica.esercizio5.modello.GestorePunti;
import it.unipr.informatica.esercizio5.modello.Punto;

public class Client {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2000);
			
			//con il lookup prendo l'oggetto che ho preparato nel server
			GestorePunti gestorePunti2 = (GestorePunti)registry.lookup("oggetto2");

			if(gestorePunti2 != null) {
				Punto s = gestorePunti2.puntoMedio(new Punto(3, 4), new Punto(5, 6));
			
				System.out.println("Il punto medio vale: " + s.getX() + " " + s.getY());
			} else
				System.out.println("Oggetto non disponibile");				
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
