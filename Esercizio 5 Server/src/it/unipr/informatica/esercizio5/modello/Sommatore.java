package it.unipr.informatica.esercizio5.modello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sommatore extends Remote {
	public int somma(int a, int b) throws RemoteException;
}
