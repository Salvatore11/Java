package it.unipr.informatica;

import java.util.LinkedList;



public class EsecutoreImpl implements Esecutore, Runnable{
	
	private LinkedList<Attività> coda;
	
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
		
		Attività attività = coda.removeFirst();
		
		attività.eseguiAttività();
	}
	
	@Override
	public int getId() {
		return ID;
	}
	
	@Override
	public void aggiungiAttività(Attività attività) {
		coda.add(attività);
	}
	

}
