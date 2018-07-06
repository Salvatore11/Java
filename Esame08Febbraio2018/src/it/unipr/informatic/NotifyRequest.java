package it.unipr.informatic;

public class NotifyRequest implements Request{
	private int priority;
	Component component = new Component();
	Attivit� attivit�= new Attivit�();
	private int svolto =-1;
	
	
	public NotifyRequest(int p,Component comp, Attivit� a) {
		this.priority = p;
		this.component= comp;
		this.attivit�=a;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void exec() {
		attivit�.nomeAttivit�();
		//System.out.println(attivit�.nomeAttivit�());
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
