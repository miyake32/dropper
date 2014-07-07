package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.fitec.lesson.dropper.entity.SimpleMessage;
import jp.co.fitec.lesson.dropper.integration.dao.MessageDao;

/**
 * Servlet implementation class DAOTester
 */
@WebServlet("/DAOTester")
public class DAOTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DAOTester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleMessage msg = new SimpleMessage(new Long(99), 35, 135,new Date(), "Test message", "test", "test", 1);
		
		MessageDao dao = new MessageDao();
//		dao.insert(msg);
//		dao.remove(msg);
		System.out.println(dao.getNewMessage());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
