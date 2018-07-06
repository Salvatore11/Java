package it.unipr.informatica.esercizio5.modello;

import java.rmi.Remote;
import java.rmi.RemoteException;
//Sommatore � l'interfaccia che deve sempre estendere Remote perch� i suoi metodi vengono implementati nel server
public interface Sommatore extends Remote {
	
	//� importante che i metodi un una classe remote debbano sempre avre throws RemoteException
	public int somma(int a, int b) throws RemoteException;
}
