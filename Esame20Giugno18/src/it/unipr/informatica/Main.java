package it.unipr.informatica;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args)  {
		
		LinkedList<Esecutore> listaEsec = new LinkedList<>();
		EsecutoreFacade facade = new EsecutoreFacade(listaEsec);
		Attivit� attivita = new Attivit�();
		
		for(int i = 0; i < 15; ++i) {
			Esecutore esecGen = new EsecutoreGenerale();
			listaEsec.add(esecGen);
			new Thread((EsecutoreGenerale)esecGen).start();
			
		}
		
		for(Esecutore esecutore : listaEsec) {
			facade.aggiungiAttivit�(attivita,esecutore.getID());
		}
	}
}
