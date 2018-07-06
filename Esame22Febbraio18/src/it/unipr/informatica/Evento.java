package it.unipr.informatica;

import java.io.Serializable;

public class Evento  implements Serializable{
	
	public static final long serialVersionUID=3072018;
	
	private int power;
	private String alert;
	private String warning;
	private String sottoSistema;
	private int id;
	
	
	public Evento() {
		this.power=0;
		this.alert= null;
		this.warning=null;
		this.sottoSistema=null;

	}
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
	public void setPower(int p) {
		this.power=p;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setAlert(String a) {
		this.alert=a;
	}
	
	public String getAlert() {
		return alert;
	}
	
	public void setWarning(String w) {
		this.warning=w;
	}
	
	public String getWarning() {
		return warning;
	}
	
	public void setSottosistema(String s) {
		this.sottoSistema=s;
	}
	
	public String getSosstosistema() {
		return sottoSistema;
	}

}
