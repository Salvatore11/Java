package it.unipr.informatica.esercizio12.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unip.informatica.esercizio12.Configurazione;
import it.unipr.informatica.esercizio12.modello.ModelloManager;
import it.unipr.informatica.esercizio12.modello.Studente;
@SuppressWarnings("serial")
@WebServlet("/nuovo_studente")
public class NuovoStudenteServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			HttpSession session= request.getSession();
			
			Configurazione configurazione= Configurazione.getConfigurazione();
			
			ModelloManager modelloManager= configurazione.getModelloManager();
			
			String cognome= request.getParameter("cognome");
			
			String nome= request.getParameter("nome");
			
			if(cognome== null || cognome.length()==0)
				throw new IllegalArgumentException("cognome non valido");
			
			if(nome== null || nome.length()==0)
				throw new IllegalArgumentException("nome non valido");
			
			Studente studente= modelloManager.nuovoStudente(cognome, nome);
			
			session.setAttribute("visualizza_studente.jsp", studente);
			
			request.getRequestDispatcher("visualizza_studente").forward(request, response);
			
		}catch (Throwable throwable) {
			throwable.printStackTrace();
			
			request.getRequestDispatcher("errore.jsp").forward(request, response);
		}
	}

}
