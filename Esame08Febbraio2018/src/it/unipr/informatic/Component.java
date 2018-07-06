package it.unipr.informatic;

public class Component implements ComponentInterface {
	//private int svolto =-1;
	Attività attività= new Attività();
	
	@Override
	public void creaRichiesta(Attività a) {
		this.attività=a;
		Request request= new NotifyRequest(6, this,attività);
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
