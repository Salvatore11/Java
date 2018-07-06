
package it.unipr.informatica.esercizio4;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import it.unipr.informatica.esercizio4.active.CallBack;
import it.unipr.informatica.esercizio4.active.Futuro;
import it.unipr.informatica.esercizio4.active.OggettoAttivo;

public class DownloadManager extends OggettoAttivo {
	
	public Futuro<String> scarica(String url) {
		Futuro<String> risultato = new Futuro<>();
		
		esegui(() -> {
			try {
				String testo = scaricaSincrona(url);
			
				risultato.set(testo);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
				risultato.setErrore(throwable);
			}
		});
		return risultato;
	}

	public void scarica(String url, CallBack<String> callback) {
//		esegui(new Comando() {
//			@Override
//			public void esegui() {
//				try {
//					String risultato = scaricaSincrona(url);
//		
//					callback.risultato(risultato);
//				} catch (Throwable throwable) {
//					callback.fallimento(throwable);
//				}
//			}
//		});

		esegui(() -> {
			try {
				String risultato = scaricaSincrona(url);
				//uso il metodo risultato della callback
				callback.risultato(risultato);
			} catch (Throwable throwable) {
				//uso fallimento della callBack
				callback.fallimento(throwable);
			}
		});
	}
	
	private String scaricaSincrona(String url) throws Throwable {
		URLConnection connection = new URL(url).openConnection();
		
		InputStream input = connection.getInputStream();
		
		StringBuilder builder = new StringBuilder();
		
		int c = input.read();
		
		while(c >= 0) {
			builder.append((char)c);
			
			c = input.read();
		}
		
		String risultato = builder.toString();
		
		input.close();
		
		return risultato;
	}
	
	
}
