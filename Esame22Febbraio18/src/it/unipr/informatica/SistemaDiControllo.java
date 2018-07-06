package it.unipr.informatica;

import java.rmi.Remote;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class SistemaDiControllo implements Remote{
	
	private static SistemaDiControllo instance;
	
	private LinkedList<Evento> powerQueue;
	
	private LinkedList<Evento> alertQueue;
	
	private LinkedList<Evento> warningQueue;
	Logger logger = new Logger();
	
	private int idEvento=0;
	
	
	
	private Timer timerWarning;
	
	private SistemaDiControllo() {}
	
	public static SistemaDiControllo getInstance() {
		if(instance==null)
			instance= new SistemaDiControllo();
		
		return instance;
	}
	
	public void addPowerQueue(Evento power) {
		synchronized (powerQueue) {
			powerQueue.add(power);
			powerQueue.notifyAll();
		}
		
	}
	
	public void addAlertQueue(Evento alert) {
		synchronized (alertQueue) {
			alertQueue.add(alert);
			alertQueue.notifyAll();
		}
		
	}
	
	public void addWarningQueue(Evento warning) {
		synchronized (warningQueue) {
			warningQueue.add(warning);
			timerWarning.schedule(new TimerTask() {
				
				@Override
				public void run() {
				warningQueue.removeFirst();	
					
				}
			}, 50, 0);
			warningQueue.notifyAll();
		}
		
	}
	
	
	public synchronized void exec() throws InterruptedException{
		for(;;) {
            if(!(powerQueue.isEmpty()) && warningQueue.isEmpty() && alertQueue.isEmpty()) {
                Evento e = powerQueue.removeFirst();
                e.setId(idEvento++);
                logger.writeFile(e);
                System.out.println(e.getPower());
            }
            else if(!(warningQueue.isEmpty()) && alertQueue.isEmpty()) {
                Evento e = warningQueue.removeFirst();
                e.setId(idEvento++);
                logger.writeFile(e);
                System.out.println(e.getWarning());
            }
            else if(!(alertQueue.isEmpty())) {
                Evento e = alertQueue.removeFirst();
                e.setId(idEvento++);
                logger.writeFile(e);
                System.out.println(e.getAlert());
            }
            else
                wait();
        }
	}

}
