package it.unipr.informatica.esercizio12.web;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/elenco_studenti")
public class ElencoStudentiServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		try {
			
			HttpSession session = request.getSession();
			
			Configurazione configurazione= Configurazione.getConfigurazione();
			
			ModelloManager modelloManager= configurazione.getModelloManager();
			
			List<Studente> studenti= modelloManager.getAllStudenti();
			
			session.setAttribute("elenco_studenti.jsp", studenti);
			
			request.getRequestDispatcher("elenco_studenti.jsp").forward(request, response);
			
		} catch (Throwable	throwable) {
			throwable.printStackTrace();
			
			request.getRequestDispatcher("errore.jsp").forward(request, response);
		}
	}

}
