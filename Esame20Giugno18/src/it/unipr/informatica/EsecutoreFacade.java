package it.unipr.informatica;

import java.util.LinkedList;

public class EsecutoreFacade {
	private LinkedList<Esecutore> listaEsecutori = new LinkedList<>();
	
	public EsecutoreFacade(LinkedList<Esecutore> lE) {
		this.listaEsecutori = lE;
	}
	
	public void aggiungiAttivit�(Attivit� a, int id) {
		for(Esecutore e : listaEsecutori) {
			if(e.getID() == id) {
				e.aggiungiAttivit�(a);
			}
		}
	}

}
