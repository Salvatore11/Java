package it.unipr.informatica;

public class PurityInterested  implements InterestedObject{
	
	private SensorManager sensorManager= SensorManager.getInstance();
	private DatabaseManager databaseManager= new DatabaseManager();
	
	private int value;
	
	//metodo doce richiesto un valore delle paurezza al sensorManager
	@Override
	public void requestValue() {
		sensorManager.addPurityInterested(this);
	}
	
	@Override
	public void setValue(Object o) {
		this.value=(int)o;
	}
	
	@Override
	public void recordValue() {
		databaseManager.insertPurity(value);
	}
	
	
	public PurityInterested() {
		
		new Thread() {
			@Override
			public void run() {
				requestValue();
				recordValue();
			}
		}.start();		
	}
	

}
