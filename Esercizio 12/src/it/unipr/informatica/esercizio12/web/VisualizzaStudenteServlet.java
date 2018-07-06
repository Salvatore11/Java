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
@WebServlet("/elenco_studenti")
public class VisualizzaStudenteServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			HttpSession session= request.getSession();
			
			Configurazione configurazione= Configurazione.getConfigurazione();
			
			ModelloManager modelloManager= configurazione.getModelloManager();
			
			//prendo la matricola come stringa, e faccio dei controlli su questa.
			String matricolaString= request.getParameter("matricola");
			
			if(matricolaString == null || matricolaString.length()==0)
				throw new IllegalArgumentException("matricola non valida");
			//se la matrciolaString è corretta allora la converto in numero.
			int matricola= Integer.parseInt(matricolaString);
			
			Studente studente= modelloManager.getStudente(matricola);
			
			session.setAttribute("elenco_studenti", studente);
			
			request.getRequestDispatcher("elenco_studenti.jsp").forward(request, response);
			
		}catch(Throwable throwable) {
			throwable.printStackTrace();
			
			request.getRequestDispatcher("errore.jsp").forward(request, response);
		}
	}

}
