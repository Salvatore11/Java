package it.unipr.informatica.esercizio8.persistente;

@SuppressWarnings("serial")
public abstract class PersistenteBase implements Persistente {	
	private int identificativo;
	
	protected PersistenteBase(int identificativo) {
		this.identificativo = identificativo;
	}
	
	@Override
	public int getIdentificativo() {
		return identificativo;
	}
}
