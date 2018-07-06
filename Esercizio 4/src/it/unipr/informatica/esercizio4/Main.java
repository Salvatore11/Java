package it.unipr.informatica.esercizio4;

import it.unipr.informatica.esercizio4.active.CallBack;
import it.unipr.informatica.esercizio4.active.Futuro;

public class Main {
	public static void main(String[] args) throws Throwable {
		DownloadManager manager = new DownloadManager();
		
		
		// viene invoata la scarica che prende due parametri
		manager.scarica("http://www.unipr.it",
				new CallBack<String>() {
					@Override
					public void risultato(String risultato) {
						System.out.println("Letti " + risultato.length() + " caratteri");
					}
					
					@Override
					public void fallimento(Throwable throwable) {
						throwable.printStackTrace();
					}
				});
		
		Futuro<String> risultato = manager.scarica("http://www.unipr.it");
		
		String testo = risultato.get();
		
		if(testo != null)
			System.out.println("Letti " + testo.length() + " caratteri");
		else {
			Throwable t = risultato.getErrore();
			
			t.printStackTrace();
		}
	}
}
