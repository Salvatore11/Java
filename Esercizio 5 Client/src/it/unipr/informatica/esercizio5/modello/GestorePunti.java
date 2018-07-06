package it.unipr.informatica.esercizio5.modello;

import java.rmi.Remote;
import java.rmi.RemoteException;
//gestorePunti è un interfaccia che verrà implementata dal server e quindi viene messo extends Remote
public interface GestorePunti extends Remote {
	public Punto puntoMedio(Punto a, Punto b) throws RemoteException;
}
