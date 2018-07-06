package it.unipr.informatica.esercizio5.modello.impl;

import it.unipr.informatica.esercizio5.modello.GestorePunti;
import it.unipr.informatica.esercizio5.modello.Punto;

public class GestorePuntiImpl implements GestorePunti {
	@Override
	public Punto puntoMedio(Punto a, Punto b) {
		float x = (a.getX() + b.getX()) / 2;

		float y = (a.getY() + b.getY()) / 2;

		return new Punto(x, y);
	}
}
