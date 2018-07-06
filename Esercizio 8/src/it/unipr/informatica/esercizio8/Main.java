package it.unipr.informatica.esercizio8;

import java.util.List;

import it.unipr.informatica.esercizio8.modello.Studente;
import it.unipr.informatica.esercizio8.persistente.MemoriaOggetti;
import it.unipr.informatica.esercizio8.persistente.derby.MemoriaOggettiDerby;

public class Main {
	public static void main(String[] args) {
		try {
			MemoriaOggetti memoria = new MemoriaOggettiDerby("jdbc:derby://localhost/C:\\Users\\utente\\Desktop\\java\\Derby DB\\Oggetti");

			Studente studente = memoria.crea(Studente.class);
		
			studente.setCognome("Rossi");
		
			studente.setNome("Mario");
		
			System.out.println("Creato lo studente: " + studente.getMatricola() + " " + studente.getCognome() + " " + studente.getNome());

			List<Studente> studenti = memoria.getAll(Studente.class);
			
			for(Studente s : studenti)
				System.out.println("Letto lo studente: " + s.getMatricola() + " " + s.getCognome() + " " + s.getNome());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
