package it.unipr.informatic;


public class NotNotifyRequest implements Request{
	private int priority;
	private Component component;
	private Attivit� attivit�;
	
	
	public NotNotifyRequest(int p, Component comp, Attivit� a) {
		this.priority = p;
		this.component=comp;
		this.attivit�=a;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void exec() {
		attivit�.nomeAttivit�();
	}
	
	@Override
	public Component getComponent() {
		return component;
	}
}
