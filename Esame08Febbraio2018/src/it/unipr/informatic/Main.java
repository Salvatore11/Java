package it.unipr.informatic;

public class Main {
	public static void main(String[] args) {
		Scheduler scheduler = Scheduler.getInstance();
		//DatabaseManager databaseManager= new DatabaseManager();
		
		
		Component component= new Component();
		Attività a= new Attività();
		
		/*
		Request request = new NotNotifyRequest(155) {
			@Override
			public void exec() {
				System.out.println("Ciaooo");
			}
		};
		*/
		
		component.creaRichiesta(a);
		
		//Request request2 = new NotifyRequest(155,component,a) ;
		/*{
			@Override
			public void exec() {
				component.creaRichiesta(a);
				//System.out.println("Mizzica");
			}
		};
		*/
		
		//scheduler.addNotifyRequest(request2);
		//scheduler.addNotNotifyRequest(request);
		//databaseManager.insertRequest("Ruini", "Gabriele");
	
		
	}
}


