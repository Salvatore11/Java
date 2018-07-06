package it.unipr.informatica.esercizio5.modello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestorePunti extends Remote {
	public Punto puntoMedio(Punto a, Punto b) throws RemoteException;
}
