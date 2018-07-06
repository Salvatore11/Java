package it.unipr.informatica.esercizio9.persistente;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class OggettoPersistente implements Persistente {	
	private int identificativo;
	
	private Map<String, Object> dati;
	
	public OggettoPersistente(int identificativo) {
		this.identificativo = identificativo;
		
		this.dati = new HashMap<>();
	}
	
	@Override
	public int getIdentificativo() {
		return identificativo;
	}
	
	public Object get(String chiave) {
		return dati.get(chiave);
	}
	
	public void put(String chiave, Object valore) {
		dati.put(chiave, valore);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("{ Identificativo : ");
		
		builder.append(identificativo);
		
		builder.append(' ');
		
		Set<String> chiavi = dati.keySet();
		
		for(String chiave : chiavi) {
			builder.append(chiave);
			
			builder.append(" : ");
			
			Object valore = dati.get(chiave); // prende il valore associato alla chiave
			
			builder.append(valore);
			
			builder.append(' ');
		}
		
		builder.append("}");
		
		return builder.toString();
	}
}
