package it.unipr.informatica;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args)  {
		
		LinkedList<Esecutore> listaEsec = new LinkedList<>();
		EsecutoreFacade facade = new EsecutoreFacade(listaEsec);
		Attività attivita = new Attività();
		
		for(int i = 0; i < 15; ++i) {
			Esecutore esecGen = new EsecutoreGenerale();
			listaEsec.add(esecGen);
			new Thread((EsecutoreGenerale)esecGen).start();
			
		}
		
		for(Esecutore esecutore : listaEsec) {
			facade.aggiungiAttività(attivita,esecutore.getID());
		}
	}
}
