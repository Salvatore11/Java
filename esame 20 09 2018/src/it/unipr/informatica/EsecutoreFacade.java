package it.unipr.informatica;

import java.util.LinkedList;

public class EsecutoreFacade {
	
	private final Esecutore esecutore; //interfaccia
	
	private LinkedList<Esecutore> listaEsecutori = new LinkedList<>();
	
	public EsecutoreFacade(Esecutore esecutore) {
		
		for(int i=0; i<10; i++) {
			Esecutore es= new EsecutoreImpl();
			listaEsecutori.add(es);
		}
		this.esecutore=esecutore;
	}
	
	public void eseguiAttività() {
		esecutore.esegui();
	}	
	
	public void aggiungiCoda(Attività attività) {
		esecutore.aggiungiAttività(attività);
	}

}
