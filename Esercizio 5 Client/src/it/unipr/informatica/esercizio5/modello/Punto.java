package it.unipr.informatica.esercizio5.modello;

import java.io.Serializable;
//il punto è l'oggetto remoto, i cui metodi possono essere invocati tramite la rete. deve sempre implementare Serializable.
public class Punto implements Serializable {
	
	//è importante che ogni oggettoRemoto(serializable) debba avere sempre un serialVersioUID
	private static final long serialVersionUID = 20171114;

	private float x;
	
	private float y;
	
	public Punto(float x, float y) {
		this.x = x;
		
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
