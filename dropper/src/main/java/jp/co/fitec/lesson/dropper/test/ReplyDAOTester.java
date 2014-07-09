package jp.co.fitec.lesson.dropper.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.fitec.lesson.dropper.entity.MessageFactory;
import jp.co.fitec.lesson.dropper.entity.ReMessage;
import jp.co.fitec.lesson.dropper.entity.SimpleMessage;
import jp.co.fitec.lesson.dropper.integration.dao.DAOFactory;
import jp.co.fitec.lesson.dropper.integration.dao.MessageDAO;
import jp.co.fitec.lesson.dropper.integration.dao.ReMessageDAO;

/**
 * Servlet implementation class ReplyDAOTester
 */
@WebServlet("/ReplyDAOTester")
public class ReplyDAOTester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyDAOTester() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// common
		MessageDAO dao = DAOFactory.createMessageDAO();
		ReMessageDAO rdao = DAOFactory.createReMessageDAO();

		// find => OK!
		response.getWriter().println(
				dao.findByPlace(34.693738D, 135.502165D, 30));
		
		// insert
		ReMessage newMsg = MessageFactory.createReMessage();
		newMsg.setLatitude(34.693738D);
		newMsg.setLongitude(135.502165D);
		newMsg.setMessage("Testtesat");
		newMsg.setParentMessage((SimpleMessage) dao.findByNumber(2));
		rdao.insert(newMsg);
		
		response.getWriter().println();
		
		response.getWriter().println(
				dao.findByPlace(34.6937381D, 135.502165D, 30));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
