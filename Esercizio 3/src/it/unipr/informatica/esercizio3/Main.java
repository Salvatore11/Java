package it.unipr.informatica.esercizio3;

public class Main {
	public static void main(String[] args) {
	
		Coda coda= new Coda(100);
		
		for(int i =0; i< 10; i++) { // crea 10 lettori
			
			Lettore lettore = new Lettore(coda);
			
			new Thread(lettore).start();
		}
		
		
		for(int i= 0; i < 2; i++) { // crea 2 Scritori
			
			Scrittore scrittore= new Scrittore(coda);
			
			new Thread(scrittore).start();
		}
	}
}
