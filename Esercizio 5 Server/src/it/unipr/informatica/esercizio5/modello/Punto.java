package it.unipr.informatica.esercizio5.modello;

import java.io.Serializable;

public class Punto implements Serializable {
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
