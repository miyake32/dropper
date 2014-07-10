package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;
import java.util.ArrayList;
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
			msg.setMessage(msg.getMessage().replaceAll("\n", "\\<br\\>"));
			int reMessagesSize = ((SimpleMessage) msg).getReMessages().size();
			System.out.println(reMessagesSize);
			ArrayList<Integer> deleteInd = new ArrayList<>();

			if (reMessagesSize > 0) {
				for (int i = 0; i < reMessagesSize; i++) {
					if (((SimpleMessage) msg).getReMessages().get(i)
							.getIsActive() == 0) {
						deleteInd.add(i);
					}
					((SimpleMessage) msg)
							.getReMessages()
							.get(i)
							.setMessage(
									((SimpleMessage) msg).getReMessages()
											.get(i).getMessage()
											.replaceAll("\n", "\\<br\\>"));
				}

				for (int i = deleteInd.size() - 1; i >= 0; i--) {
					((SimpleMessage) msg).getReMessages().remove((int)
							deleteInd.get(i));
					
					

				}
			}
			if (((SimpleMessage) msg).getReMessages().size() == 0) {
				((SimpleMessage) msg).setReMessages(null);
			}
			System.out.println(((SimpleMessage) msg).getReMessages());
		}

		session.setAttribute("messages", message);

		return "/index.jsp";
	}

}
