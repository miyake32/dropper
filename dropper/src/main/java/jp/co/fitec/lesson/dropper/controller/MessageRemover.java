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

public class MessageRemover implements Action{

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MessageDAO dao;
		
		// parameterとしてMessageのnumberとdeleteKeyを受けとる
		long msgNum = new Long(request.getParameter("msgNum"));
		String deleteKey = request.getParameter("deleteKey");
		//numberの符号が+だったらSimpleMessage,-だったらRepMessage
		if (msgNum >= 0) {
			dao = DAOFactory.createMessageDAO();
		} else {
			dao = DAOFactory.createReMessageDAO();
		}
		
		
		// numberからMessageを取得
		Message message = dao.findByNumber(msgNum);
		
		// deleteKeyが一致すればMessageをremove
		if (message.getDeleteKey().equals(deleteKey)){
			dao.remove(message);
		} else {
			request.setAttribute("error", "wrong delete key");
		}
				
		return "index.jsp";
	}
	
}
