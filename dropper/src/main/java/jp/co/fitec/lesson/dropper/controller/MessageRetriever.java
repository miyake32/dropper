package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.integration.dao.DAOFactory;
import jp.co.fitec.lesson.dropper.integration.dao.MessageDAO;

public class MessageRetriever implements Action {
	
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Double lat = (Double)session.getAttribute("lat");
		Double lon = (Double)session.getAttribute("lon");
		int dist = new Integer(request.getParameter("dist"));
		
		MessageDAO dao = DAOFactory.createMessageDAO();
		List<Message> message = dao.findByPlace(lat, lon, dist);
			
		session.setAttribute("messages", message);
		
		return "/index.jsp";
	}

}
