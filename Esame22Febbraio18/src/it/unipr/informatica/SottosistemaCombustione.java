package it.unipr.informatica;

public class SottosistemaCombustione implements SottoSistema{
	
	Evento evento= new Evento();
	
	@Override
	public void generaEventoPower() {
		Evento evento= new Evento();
		
		evento.setSottosistema(getClass().getName());
		evento.setPower(getPower());
		
		//aggiungo alla coda
		SistemaDiControllo.getInstance().addPowerQueue(evento);
		
	}
	
	
	@Override
	public void generaEventoAlert() {
		Evento evento= new Evento();
		String alert= getAlert();

		if(alert != null) {
			evento.setAlert(getAlert());
		}
		
		evento.setSottosistema(getClass().getName());
		
		//aggiungo alla coda
		SistemaDiControllo.getInstance().addAlertQueue(evento);
	}
	
	@Override
	public void generaEventoWarning() {
		Evento evento= new Evento();
		String warning= getWarning();
		
		if(warning != null) {
			evento.setWarning(getWarning());
		}
		
		evento.setSottosistema(getClass().getName());
		
		SistemaDiControllo.getInstance().addWarningQueue(evento);
	}
	
	
	@Override
	public int getPower() {
		return 95;
	}
	
	@Override
	public String getAlert() {
		return "sistema in avaria";
	}
	
	@Override
	public String getWarning() {
		return "possibille che non funzioni il freno";
	}
	
	

}
