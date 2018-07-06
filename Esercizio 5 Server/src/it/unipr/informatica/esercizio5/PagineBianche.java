
package it.unipr.informatica.esercizio5;

import java.rmi.registry.LocateRegistry;

public class PagineBianche {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(2000);
			
			System.out.println("Registry attivato");
			
			Object mutex = new Object();
			
			synchronized (mutex) {
				mutex.wait();
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
