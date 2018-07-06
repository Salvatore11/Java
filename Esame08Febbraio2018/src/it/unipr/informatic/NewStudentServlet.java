package it.unipr.informatic;
/*
import java.io.IOException;

import javax.servlet.annotation.WebServlet;


@SuppressWarnings("serial")
@WebServlet("/nuovo_studente")
public class NewStudentServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			HttpSession session= request.getSession();
			
			DatabaseManager databaseManager= new DatabaseManager();
			
			String cognome= request.getParameter("cognome");
			String nome= request.getParameter("nome");
			
			if(cognome== null || cognome.length()==0)
				throw new IllegalArgumentException("cognome non valido");
			
			if(nome== null || nome.length()==0)
				throw new IllegalArgumentException("nome non valido");
			
		}catch(Throwable throwable) {
			throwable.printStackTrace();
		}
	}

}
*/