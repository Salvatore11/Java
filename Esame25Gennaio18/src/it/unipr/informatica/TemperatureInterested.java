package it.unipr.informatica;

public class TemperatureInterested implements InterestedObject{
	
	private SensorManager sensorManager= SensorManager.getInstance();
	private DatabaseManager databaseManager= new DatabaseManager();
	
	private float value =-1;
	
	
	@Override
	public void requestValue() {
		
		//richiedo di aggiungermi alla lista degli interessati alla temperaturat
		sensorManager.addTemperatureInterested(this);
		
		
		
	}
	
	@Override
	public void setValue(Object o) {
		this.value=(float)o;
	}
	
	
	@Override
	public void recordValue() {
		databaseManager.insertTemperature(value);
	}
	
	public TemperatureInterested() {
		//creo un thread per ogni interessato
		
		new Thread() {
			@Override
			public void run() {
				requestValue();
				recordValue();
			}
		}.start();
	}

}
