package it.unipr.informatica.esercizio9.persistente.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import it.unipr.informatica.esercizio9.persistente.MemoriaOggetti;
import it.unipr.informatica.esercizio9.persistente.OggettoPersistente;
import it.unipr.informatica.esercizio9.persistente.Persistente;
import it.unipr.informatica.esercizio9.persistente.PersistenteException;

public class MemoriaOggettiDerby extends MemoriaOggetti {
	private String url;
	
	public MemoriaOggettiDerby(String url) {
		this.url = url;
		
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	@Override
	public <T extends Persistente> T crea(Class<T> classe) throws PersistenteException {
		if(classe == null)
			throw new IllegalArgumentException();

		try (
			Connection connessione = DriverManager.getConnection(url);
			PreparedStatement insert = connessione.prepareStatement("INSERT INTO OGGETTI(CLASSE, DATI) VALUES (?, ?)", RETURN_GENERATED_KEYS);
			PreparedStatement update = connessione.prepareStatement("UPDATE OGGETTI SET DATI=? WHERE ID=?");
		) {
			connessione.setAutoCommit(false);

			insert.setString(1, classe.getName());

			insert.setBlob(2, new SerialBlob(new byte[0]));
				
			insert.execute();

			ResultSet chiavi = insert.getGeneratedKeys();
			
			chiavi.next();

			int id = chiavi.getInt(1);

			chiavi.close();
			
			OggettoPersistente risultato = new OggettoPersistente(id);
			
			update.setBlob(1, new SerialBlob(serializza(risultato)));
			
			update.setInt(2, id);
			
			update.execute();
			
			connessione.commit();
			
			return creaProxy(classe, risultato);
		} catch(Throwable throwable) {			
			throw new PersistenteException(throwable);
		}
	}
	
	@Override
	public <T extends Persistente> List<T> getAll(Class<T> classe) throws PersistenteException {
		if(classe == null)
			throw new IllegalArgumentException();

		try (
			Connection connessione = DriverManager.getConnection(url);
			PreparedStatement select = connessione.prepareStatement("SELECT * FROM OGGETTI");
			ResultSet resultSet = select.executeQuery();
		) {
			List<T> risultato = new ArrayList<>();
			
			while(resultSet.next()) {
				Blob blob = resultSet.getBlob("DATI");
				
				byte[] dati = blob.getBytes(1, 1024);
				
				T oggetto = creaProxy(classe, (OggettoPersistente)deserializza(dati));
				
				risultato.add(oggetto);
			}
			
			return risultato;
		} catch (Throwable throwable) {
			throw new PersistenteException(throwable);
		}
	}
	
	@Override
	protected void salva(Persistente oggetto) throws PersistenteException {
		try (
			Connection connessione = DriverManager.getConnection(url);
			PreparedStatement update = connessione.prepareStatement("UPDATE OGGETTI SET DATI=? WHERE ID=?");
		) {
			update.setBlob(1, new SerialBlob(serializza(oggetto)));
			
			update.setInt(2, oggetto.getIdentificativo());
			
			update.execute();
		} catch (Throwable throwable) {
			throw new PersistenteException(throwable);
		}
	}
}
