package it.unipr.informatica;


public class PuritySensor implements Sensor{

	private SensorManager sensorManager;

	@Override
	public void readValue() {
		
		sensorManager= SensorManager.getInstance();

		int tempor=8;

		if(tempor < 0 ||tempor >10)
			readValue();
		
		sensorManager.purityFuture.set(tempor);

	
	}

}
