package it.unipr.informatic;

public class NotifyRequest implements Request{
	private int priority;
	Component component = new Component();
	Attività attività= new Attività();
	private int svolto =-1;
	
	
	public NotifyRequest(int p,Component comp, Attività a) {
		this.priority = p;
		this.component= comp;
		this.attività=a;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void exec() {
		attività.nomeAttività();
		//System.out.println(attività.nomeAttività());
	}
	
	@Override
	public Component getComponent() {
		return component;
	}
	
	
	public void setSvolto(int svolto) {
		this.svolto=svolto;
		
		System.out.println("notifica componente: " + svolto);
		
	}
	
}
