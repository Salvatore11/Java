package it.unipr.informatic;

public interface Request {
	public void exec();
	public int getPriority();
	public Component getComponent();
}
