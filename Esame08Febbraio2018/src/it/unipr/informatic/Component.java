package it.unipr.informatic;

public class Component implements ComponentInterface {
	//private int svolto =-1;
	Attivit� attivit�= new Attivit�();
	
	@Override
	public void creaRichiesta(Attivit� a) {
		this.attivit�=a;
		Request request= new NotifyRequest(6, this,attivit�);
		Scheduler.getInstance().addNotifyRequest(request);	
	}
	
	/*
	@Override
	public void setSvolto(int svolto) {
		this.svolto=svolto;
		
		System.out.println("notifica componente: " + svolto);
		
	}
	*/

}
