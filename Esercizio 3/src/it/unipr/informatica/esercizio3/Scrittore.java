package it.unipr.informatica.esercizio3;

public class Scrittore implements Runnable {
	private Coda coda; //viene creato un oggetto di tipo Coda
	
	public Scrittore(Coda coda) {
		this.coda = coda;
	}
	
	@Override
	public void run() {		
		try {
			int contatore = 0; // valore da scrivere

			for(int i = 0; i < 100; i++) { // ogni scrittore scrive 2 volte
				
				coda.scrivi(contatore);
				
				System.out.println("Scritto " + contatore);
				
				contatore++;
				
				Thread.sleep((int)(100*Math.random()));
			}
		} catch(InterruptedException exception) {
			// Volutamente vuota
		}
	}
}
