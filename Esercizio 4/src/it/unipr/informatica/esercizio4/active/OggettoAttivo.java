package it.unipr.informatica.esercizio4.active;

import java.util.LinkedList;

public abstract class OggettoAttivo {
	private LinkedList<Comando> comandi; // creo una lista di comandi di tipo comando
	
	// metto il costruttore protected in modo che possa essere invocato solo dalle sue sotto classi
	protected OggettoAttivo() {
		this.comandi = new LinkedList<>();
		
		new Thread() {
			@Override
			public void run() {
				try {
					loop();
				} catch (InterruptedException exception) {
					// Volutamente in bianco
				}
			}
		}.start(); // fa partire il thread
	}

	//nella esegui passo il comando da inserire e lo inserisco dentro la lista dei comandi che prima
	//deve essere sincronizzata
	protected void esegui(Comando comando) {
		synchronized (comandi) { // avviene tutto in muta esclusione
			comandi.add(comando);
			
			comandi.notifyAll();		
		}
	}
	
	private void loop() throws InterruptedException {
		for(;;) {
			Comando comando;
			
			synchronized(comandi) {
				while(comandi.isEmpty())
					comandi.wait();
			
				comando = comandi.removeFirst();
			}
			
			try {
				comando.esegui(); //esegui dell'interfaccia
			} catch(Throwable throwable) {
				throwable.printStackTrace();
			}
		}
	}
}
