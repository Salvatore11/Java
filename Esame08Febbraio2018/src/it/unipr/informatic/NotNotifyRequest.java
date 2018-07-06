package it.unipr.informatic;


public class NotNotifyRequest implements Request{
	private int priority;
	private Component component;
	private Attività attività;
	
	
	public NotNotifyRequest(int p, Component comp, Attività a) {
		this.priority = p;
		this.component=comp;
		this.attività=a;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void exec() {
		attività.nomeAttività();
	}
	
	@Override
	public Component getComponent() {
		return component;
	}
}
