package it.unipr.informatica;

public class Main {
	public static void main(String[] args) {
		
		//SensorManager sensorManager=SensorManager.getInstance();
		
		InterestedObject object = new TemperatureInterested();
		
		InterestedObject purity= new PurityInterested();
	}

}
