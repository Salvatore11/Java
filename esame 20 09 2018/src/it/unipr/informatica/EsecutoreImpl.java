package it.unipr.informatica;

import java.util.LinkedList;



public class EsecutoreImpl implements Esecutore, Runnable{
	
	private LinkedList<Attivit�> coda;
	
	private final int ID;
	
	private static int numIstanza =0;
	
	public EsecutoreImpl() {
		numIstanza++;
		
		coda= new LinkedList<>();
		
		ID= numIstanza;
	}
	
	@Override
	public void run() {
		esegui();
	}
	@Override
	public void esegui() {
		
		Attivit� attivit� = coda.removeFirst();
		
		attivit�.eseguiAttivit�();
	}
	
	@Override
	public int getId() {
		return ID;
	}
	
	@Override
	public void aggiungiAttivit�(Attivit� attivit�) {
		coda.add(attivit�);
	}
	

}
