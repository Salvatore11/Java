package it.unipr.informatica.esercizio12.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unip.informatica.esercizio12.Configurazione;
import it.unipr.informatica.esercizio12.modello.ModelloManager;

@SuppressWarnings("serial")
@WebServlet("/cancella_studente")
public class CancellaStudenteServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Configurazione configurazione= Configurazione.getConfigurazione();
			
			ModelloManager modelloManager= configurazione.getModelloManager();
			
			String matricolaString= request.getParameter("matricola");
			
			if(matricolaString==null || matricolaString.length()==0)
				throw new IllegalArgumentException("matricola non valida");
			
			int matricola= Integer.parseInt(matricolaString);
			
			modelloManager.rimuoviStudente(matricola);
			
			request.getRequestDispatcher("elenco_studenti.jsp").forward(request, response);
		}catch (Throwable throwable) {
			throwable.printStackTrace();
			
			request.getRequestDispatcher("errore.jsp").forward(request, response);
		}
	}

}
