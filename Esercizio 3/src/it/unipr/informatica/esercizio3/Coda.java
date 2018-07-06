package it.unipr.informatica.esercizio3;

public class Coda {
	private Object[] buffer;
	
	private int numeroElementi;
	
	private int posizioneDaLeggere;
	
	private int posizioneDaScrivere;
	
	public Coda(int dimensione) {
		this.buffer = new Object[dimensione]; // riempio il buffer con la dimansione
		
		this.numeroElementi = 0;
		
		this.posizioneDaScrivere = 0;
		
		this.posizioneDaLeggere = 0;
	}
	
	public synchronized void scrivi(Object valore) throws InterruptedException {
		while(numeroElementi >= buffer.length)
			wait();

		buffer[posizioneDaScrivere] = valore;
		
		numeroElementi++;
		
		posizioneDaScrivere = (posizioneDaScrivere + 1) % buffer.length;
		
		notifyAll();
	}
	
	public synchronized Object leggi() throws InterruptedException {
		while(numeroElementi == 0)
			wait();
		
		Object risultato = buffer[posizioneDaLeggere];
		
		numeroElementi--;
		
		posizioneDaLeggere = (posizioneDaLeggere + 1) % buffer.length;
		
		notifyAll();
		
		return risultato;
	}
}
