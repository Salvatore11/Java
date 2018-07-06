package it.unipr.informatica.esercizio3;

public class Lettore implements Runnable {
	private Coda coda; 
	
	public Lettore(Coda coda) { // il lettore legge da una coda
		this.coda = coda;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < 20; i++) { // ogni lettore legge 20 volte
				Object risultato = coda.leggi();
				
				System.out.println("Letto: " + risultato);
				
				Thread.sleep(500);
			}
		} catch (InterruptedException exception) {
			// Volutamente vuota
		}
	}
}
