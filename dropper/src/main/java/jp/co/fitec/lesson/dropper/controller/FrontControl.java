package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontControl
 */
@WebServlet("*.do")
public class FrontControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setSessionAttributes(request);
		
		String forwardPath = ActionFactory.getAction(request.getServletPath())
								.doAction(request, response);
		
		if(forwardPath != null) {
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void setSessionAttributes(HttpServletRequest request){
		HttpSession session = request.getSession();
		String nameInStrage = request.getParameter("nameInStrage");
		String deleteKeyInStrage = request.getParameter("deleteKeyInStrage");
		
		if (!nameInStrage.equals(session.getAttribute("nameInStrage"))) {
			session.setAttribute("nameInStrage", nameInStrage);
		}
		if (!deleteKeyInStrage.equals(session.getAttribute("deleteKeyInStrage"))) {
			session.setAttribute("deleteKeyInStrage", deleteKeyInStrage);
		}
	}
	

}
