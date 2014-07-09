package jp.co.fitec.lesson.dropper.integration.dao;


import java.util.List;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.entity.SimpleMessage;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class MessageDAO extends BaseDAO {
	
	public List<Message> findByPlace(double latitude, double longitude, int distance) {
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery sql = session.createSQLQuery("select * from message " + 
			"where 6378137 * ACOS((sin(latitude / 180 * PI()) * SIN("+ latitude +" / 180 * PI()))" + 
			"+ (COS(latitude / 180 * PI()) * COS("+ latitude +" / 180 * PI())" + 
			"* COS(("+ longitude + " - longitude) / 180 * PI()))) <= "+ distance +" AND is_active = 1 "
					+ "ORDER BY number DESC"
			).addEntity(SimpleMessage.class);	
		
		@SuppressWarnings("unchecked")
		List<Message> messageList = sql.list();
		
		if(messageList == null) {
			return null;
		}
		
		return messageList;
	}
	
	/**
	 * <p>現在の最大値よりも１つ大きなnumberを持つSimpleMessageオブジェクトを生成するメソッド</p>
	 * @return
	 */
	public Message getNewMessage() {
		Session session = sessionFactory.getCurrentSession();
		Message msg = (SimpleMessage) session.createSQLQuery("SELECT * FROM message WHERE number = (SELECT MAX(number) FROM message)").addEntity(SimpleMessage.class).list().get(0);
		Message newMsg = new SimpleMessage();
		newMsg.setNumber(msg.getNumber() + 1);
		return newMsg;
	}
	
	public void insert(Message message) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save((SimpleMessage)message);
	}
	
	public Message findByNumber(long number) {
		Session session = sessionFactory.getCurrentSession();
		return (Message) session.load(SimpleMessage.class, number);
	}
	
	public void remove(Message message) {
		Session session = sessionFactory.getCurrentSession();

		message.setIsActive(0);
		session.update((SimpleMessage)message);
	}
}
