package it.unipr.informatica.esercizio6.modello;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Contatore implements Serializable {
	private static final long serialVersionUID = 20171114;

	private int valore;
	
	private transient Timer timer; //permette di non serializzare "timer" e lo forza a null
	
	public Contatore() {
		if(!carica())
			this.valore = 1;
		
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {  // mi permette di verificare ogni tot secondi se il timer e scaduto.
			@Override                                //viene programmata l'azione di timeoutScaduto
			public void run() {
				timeoutScaduto();
			}
		}, 0, 1000);
	}
	
	private void timeoutScaduto() {
		System.out.println("Valore attuale del contatore: " + valore);
		
		valore++;
		
		salva();
	}
	
	private boolean carica() {
		try (
			FileInputStream fileInputStream = new FileInputStream("contatore.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		) {
			Object oggetto = objectInputStream.readObject();

			Contatore contatore = (Contatore)oggetto;
			
			this.valore = contatore.valore;
			
			return true;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			
			return false;
		}
	}
	
	private boolean salva() {
		try (
			FileOutputStream fileOutputStream = new FileOutputStream("contatore.ser");
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		) {
			objectOutputStream.writeObject(this);

			return true;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			
			return false;
		}
	}
}
