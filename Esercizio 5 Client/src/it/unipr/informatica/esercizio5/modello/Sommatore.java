package it.unipr.informatica.esercizio5.modello;

import java.rmi.Remote;
import java.rmi.RemoteException;
//Sommatore è l'interfaccia che deve sempre estendere Remote perchè i suoi metodi vengono implementati nel server
public interface Sommatore extends Remote {
	
	//é importante che i metodi un una classe remote debbano sempre avre throws RemoteException
	public int somma(int a, int b) throws RemoteException;
}
