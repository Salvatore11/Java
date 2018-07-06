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

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet{
	public static final String NOME_UTENTE = "it.unipr.informatica.esercizio12.web.nome_utente";
	public static final String PASSWORD= "it.unipr.informatica.esercizio12.web.password";
	
	public static final String LOGIN_FALLITO= "it.unipr.informatica.esercizio12.web.errore";	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Configurazione configurazione=Configurazione.getConfigurazione();
			
			ModelloManager modelloManager=configurazione.getModelloManager();
			
			String nome=request.getParameter(NOME_UTENTE);
			
			String password= request.getParameter(PASSWORD);
			
			if(!modelloManager.verificaUtente(nome, password)) {
				HttpSession session=request.getSession();
				
				session.setAttribute(LOGIN_FALLITO, "Impossibile accedere. prego riprovare");
				
				request.getRequestDispatcher("/").forward(request, response);
				
				return;
			}
			
			request.getRequestDispatcher("/elencoStudenti").forward(request, response);
		}catch(Throwable throwable) {
			throwable.printStackTrace();
			
			request.getRequestDispatcher("/errore.jsp").forward(request, response);			
		}
	}

}
