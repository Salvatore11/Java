package it.unip.informatica.esercizio12;


import java.util.ResourceBundle;

import it.unipr.informatica.esercizio12.modello.ModelloManager;

public class Configurazione {

	private static Configurazione istanza= null;
	
	private ModelloManager modelloManager;
	
	
	public static Configurazione getConfigurazione() {
		if(istanza == null)
			istanza= new Configurazione();
		
		return istanza;
	}
	
	private Configurazione() {
		//lascio vuoto
	}
	
	
	public ModelloManager getModelloManager() {
		
		try {

			if(modelloManager==null) {
				
				String nomeClasse= getStringa("it.unipr.informatica.esercizio12.modello");
				
				Class<?> classe= Class.forName(nomeClasse);
				
				modelloManager=(ModelloManager)classe.newInstance();
			}
			
			return modelloManager;	
		}catch(Throwable throwable) {
			throw new IllegalArgumentException(throwable);
		}	
	}
	
	
	public String getStringa(String nome) {
		
		if(nome == null || "".equals(nome))
			throw new IllegalArgumentException();
		
		ResourceBundle bundle = ResourceBundle.getBundle("configurazione");
		
		return bundle.getString(nome);
		
	}
	

}
