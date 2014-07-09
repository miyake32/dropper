package jp.co.fitec.lesson.dropper.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.entity.MessageFactory;
import jp.co.fitec.lesson.dropper.entity.SimpleMessage;
import jp.co.fitec.lesson.dropper.integration.dao.DAOFactory;
import jp.co.fitec.lesson.dropper.integration.dao.MessageDAO;

/**
 * Servlet implementation class DAOTester
 */
@WebServlet("/Test/DAOTester")
public class DAOTester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// common
		MessageDAO dao = DAOFactory.createMessageDAO();

		// find => OK!
		response.getWriter().println(dao.findByPlace(34.693738D, 135.502165D, 30));

		// remove => OK!
		// List<Message> msgs;
		// response.getWriter().println(msgs = dao.findByPlace(33D, 135D, 30));
		// dao.remove(msgs.get(0));
		// response.getWriter().println(msgs = dao.findByPlace(33D, 135D, 30));
		// dao.remove(msgs.get(0));
		// response.getWriter().println(msgs = dao.findByPlace(33D, 135D, 30));

		// insert => OK!
//		 Message newMsg = MessageFactory.createMessage();
//		 newMsg.setLatitude(33D);
//		 newMsg.setLongitude(135D);
//		 newMsg.setMessage("Now I'm testing DAO function!!\nIt's going fine!");
//		 newMsg.setName("Testman");
//		 newMsg.setDeleteKey("Test delete key");
//		 dao.insert(newMsg);
	}
}
