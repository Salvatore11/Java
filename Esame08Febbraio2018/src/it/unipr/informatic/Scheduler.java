package it.unipr.informatic;

import java.util.LinkedList;

public class Scheduler {
	private static Scheduler instance = null;
	private LinkedList<Request> notifyQueue = new LinkedList<>();
	private LinkedList<Request> notNotifyQueue = new LinkedList<>();

	private Scheduler() {
		for(int i = 0; i < 10; ++i) {
			new Thread() {
				@Override
				public void run() {
					try {
						execRequest();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	public static Scheduler getInstance() {
		if(instance == null) {
			instance = new Scheduler();
		}
		return instance;
	}

	public synchronized void addNotifyRequest(Request r) {
		synchronized(notifyQueue) {
			notifyQueue.add(r);
			notifyAll();
		}
	}

	public synchronized void addNotNotifyRequest(Request r) {
		synchronized(notNotifyQueue) {
			notNotifyQueue.add(r);
			notifyAll();
		}
	}

	public synchronized void execRequest() throws InterruptedException{
		int index = 0;
		int min = 255;

		while(true) {
			while(notNotifyQueue.isEmpty() && notifyQueue.isEmpty())
				wait();

			if(!(notNotifyQueue.isEmpty())) {

				for(Request r : notNotifyQueue) {
					if(r.getPriority() < min) {
						index = notNotifyQueue.indexOf(r);
					}
				}
					min = 255;
					notNotifyQueue.remove(index).exec();
					
			}
			else {
				NotifyRequest request= (NotifyRequest) notifyQueue.removeFirst();
				request.exec();
				request.setSvolto(1);
			}
		}
	}

}
