package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.entity.SimpleMessage;
import jp.co.fitec.lesson.dropper.integration.dao.DAOFactory;
import jp.co.fitec.lesson.dropper.integration.dao.MessageDAO;

public class MessageRetriever implements Action {

	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Double lat = (Double) session.getAttribute("lat");
		Double lon = (Double) session.getAttribute("lon");
		int dist = new Integer(request.getParameter("dist"));

		MessageDAO dao = DAOFactory.createMessageDAO();
		List<Message> message = dao.findByPlace(lat, lon, dist);

		
		for (Message msg : message) {
			int reMessagesSize = ((SimpleMessage)msg).getReMessages().size();
			int sizeCount = reMessagesSize;
			
			if (reMessagesSize > 0) {
				for (int i = 0; i < sizeCount; i++) {
					if (((SimpleMessage)msg).getReMessages().get(i).getIsActive() == 0) {
						((SimpleMessage)msg).getReMessages().remove(i);
						sizeCount--;
						i--;
					}
				}
			}
			if (sizeCount == 0) {
				((SimpleMessage)msg).setReMessages(null);
			}
		}

		session.setAttribute("messages", message);

		return "/index.jsp";
	}

}
