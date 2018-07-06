package it.unipr.informatica;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Logger {
	
	public void writeFile(Evento evento) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("log.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            int id = evento.getId();
            int power;
            String warning;
            String alert;
           
            if(evento.getPower() != -1) {
                power = evento.getPower();
                objectOutputStream.writeUTF("ID: " + id + "\n" + "Power: " + power);
            }
            else if(evento.getWarning() != null) {
                warning = evento.getWarning();
                objectOutputStream.writeUTF("ID: " + id + "\n" + "Warning: " + warning);
            }
            else if(evento.getAlert() != null) {
                alert = evento.getAlert();
                objectOutputStream.writeUTF("ID: " + id + "\n" + "Alert: " + alert);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
