package it.unipr.informatica.esercizio12.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		try {
			HttpSession session = request.getSession();
			
			session.invalidate();
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}catch(Throwable throwable) {
			throwable.printStackTrace();
			
			request.getRequestDispatcher("/errore.jsp").forward(request, response);			
		}
	}
}
