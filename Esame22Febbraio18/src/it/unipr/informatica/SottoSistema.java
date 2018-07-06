package it.unipr.informatica;

public interface SottoSistema {
	
	public void generaEventoPower();
	public void generaEventoAlert();
	public void generaEventoWarning();
	
	public int getPower();
	
	public String getAlert();
	
	public String getWarning();
}
