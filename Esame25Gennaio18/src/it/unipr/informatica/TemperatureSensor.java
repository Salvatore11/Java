package it.unipr.informatica;

public class TemperatureSensor implements Sensor{
	

	private SensorManager sensorManager;
	
	@Override
	public void readValue() {
		
		sensorManager= SensorManager.getInstance();
		
		sensorManager.temperatureFuture.set(3.6f);
		
	}
	
	
	

}
