package jp.co.fitec.lesson.dropper.entity;

import java.util.Date;

import jp.co.fitec.lesson.dropper.integration.dao.DAOFactory;
import jp.co.fitec.lesson.dropper.integration.dao.MessageDAO;
import jp.co.fitec.lesson.dropper.integration.dao.ReMessageDAO;

public class MessageFactory {

	/**
	 * <p>
	 * dateTime, isActive, numberに初期値が設定された新しいMessageオブジェクトを作成するメソッド
	 * numberの初期値は、データベースに現在存在する最も大きなnumberの次の値
	 * </p>
	 * 
	 * @return
	 */
	public static Message createMessage() {
		MessageDAO dao = DAOFactory.createMessageDAO();

		Message msg = dao.getNewMessage(); // numberが設定されたMessageオブジェクトを取得

		msg.setDateTime(new Date());
		msg.setIsActive(1);
		return msg;
	}
	
	public static ReMessage createReMessage() {
		ReMessageDAO dao = DAOFactory.createReMessageDAO();

		ReMessage msg = (ReMessage)dao.getNewMessage(); // numberが設定されたMessageオブジェクトを取得

		msg.setDateTime(new Date());
		msg.setIsActive(1);
		return msg;
	}
}
