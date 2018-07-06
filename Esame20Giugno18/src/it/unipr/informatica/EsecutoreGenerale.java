package it.unipr.informatica;

import java.util.LinkedList;



public class EsecutoreGenerale implements Esecutore, Runnable{
	private static int numIstanzaEsGen = 0;
	private LinkedList<Attivit�> coda;
	private final int ID;

	public EsecutoreGenerale()  {
		++numIstanzaEsGen;
		ID = numIstanzaEsGen;
		coda = new LinkedList<Attivit�>();
	}

	@Override
	public synchronized void aggiungiAttivit�(Attivit� a) {
		coda.add(a);
		notifyAll();

	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public synchronized void esegui() throws InterruptedException{

		while(coda.isEmpty()) {
			System.out.println("cazzo");
			wait();
		}

		Attivit� attivit� = coda.removeFirst();
		attivit�.eseguiAttivit�();
		

	}

	@Override
	public void run() {
		while(true) {
			try {
				esegui();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
}
