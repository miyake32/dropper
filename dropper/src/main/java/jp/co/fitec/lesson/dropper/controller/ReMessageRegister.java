package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.entity.MessageFactory;
import jp.co.fitec.lesson.dropper.entity.ReMessage;
import jp.co.fitec.lesson.dropper.integration.dao.DAOFactory;

public class ReMessageRegister implements Action {
	
	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Message msg = MessageFactory.createMessage();
		
		// requestのparameterを取得
		String message = request.getParameter("message");
		String name = request.getParameter("name");
		String deleteKey = request.getParameter("deleteKey");
		long replyTo = new Long(request.getParameter("replyTo"));
		
		// name, deleteKey, replyToに値がない場合はnull値を設定
		if (name == "") {
			name = null;
		}
		if (deleteKey == "") {
			deleteKey = null;
		}
		
		// sessionのattributeを取得
		Double lat = (Double) session.getAttribute("lat");
		Double lon = (Double) session.getAttribute("lon");
		
		// Messageオブジェクトに値を設定
		msg.setMessage(message);
		msg.setName(name);
		msg.setDeleteKey(deleteKey);
		((ReMessage) msg).setReplyTo(replyTo);
		msg.setLatitude(lat);
		msg.setLongitude(lon);
		
		// Databaseに追加
		DAOFactory.createMessageDAO().insert(msg);
		
		return "/index.jsp";
	}

}
