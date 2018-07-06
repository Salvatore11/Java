package it.unipr.informatica.esercizio9;

import java.util.List;

import it.unipr.informatica.esercizio9.modello.Studente;
import it.unipr.informatica.esercizio9.persistente.MemoriaOggetti;
import it.unipr.informatica.esercizio9.persistente.derby.MemoriaOggettiDerby;

public class Main {
	public static void main(String[] args) {
		try {
			MemoriaOggetti memoria = new MemoriaOggettiDerby("jdbc:derby://localhost/Oggetti");

			Studente studente = memoria.crea(Studente.class);
		
			studente.setCognome("Verdi");
		
			studente.setNome("Giuseppe");
		
			System.out.println("Costruito lo studente: " + studente.getMatricola() + " " + studente.getCognome() + " " + studente.getNome());

			List<Studente> studenti = memoria.getAll(Studente.class);
			
			for(Studente s : studenti)
				System.out.println("Letto lo studente: " + s);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
