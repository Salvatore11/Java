package it.unipr.informatica;

import java.util.LinkedList;


public class SensorManager {
	
	private static SensorManager instance;
	
	private LinkedList<InterestedObject> temperatureInterestedQueue;
	private LinkedList<InterestedObject> purityInterestedQueue;
	
	private Sensor temperatureSensor;
	private Sensor puritySensor;
	
	public Future<Integer> purityFuture = new Future<>();
	public Future<Float> temperatureFuture= new Future<>();
	
	
	//per il singleton
	public static SensorManager getInstance() {
		
		if(instance== null)
			instance= new SensorManager();
		
		return instance;
		
	}
	
	private SensorManager() {
		
		temperatureInterestedQueue= new LinkedList<>();
		purityInterestedQueue= new LinkedList<>();
		
		temperatureSensor= new TemperatureSensor();
		puritySensor= new PuritySensor();
		
		//metto un thread che mi legge
		new Thread() {
			@Override
			public void run() {
				try {
					while(true) { //loop infinito
						readTemperature();
						readPurity();
						Thread.sleep(100); //ogni 100ms riparte la lettura 
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}.start();
	}
	
	public void readTemperature() throws InterruptedException{
		temperatureSensor.readValue();
		
		for(InterestedObject object : temperatureInterestedQueue)
			object.setValue(temperatureFuture.get());
		
		System.out.println(temperatureFuture.get());
		
		
	}
	
	public synchronized void readPurity() throws InterruptedException{
		puritySensor.readValue();
		
		for(InterestedObject object : purityInterestedQueue) {
				object.setValue(purityFuture.get());
		}
		
		System.out.println(purityFuture.get());
	
	}

	
	public void addTemperatureInterested(InterestedObject object) {
		synchronized (temperatureInterestedQueue) {
			temperatureInterestedQueue.add(object);
		}
		
	
	}
	
	public void addPurityInterested(InterestedObject object) {
		synchronized (purityInterestedQueue) {
			purityInterestedQueue.add(object);
		}
		
		
	}
	
	

}
