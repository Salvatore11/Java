package it.unipr.informatica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StatusServlet extends HttpServlet{
	
	SensorManager sensorManager = SensorManager.getInstance();
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	
	}
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			HttpSession session = request.getSession();
			
			DatabaseManager databaseManager= new DatabaseManager();
			
			session.setAttribute("attribute_purity", sensorManager.purityFuture.get());
			
			request.getRequestDispatcher("elenco_studenti.jsp").forward(request, response);
			
			
		}catch (Throwable throwable) {
			throwable.printStackTrace();
			}
	}

}
