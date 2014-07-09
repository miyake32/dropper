package jp.co.fitec.lesson.dropper.integration.dao;

import java.util.List;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.entity.ReMessage;
import jp.co.fitec.lesson.dropper.entity.SimpleMessage;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ReMessageDAO extends MessageDAO {
	
	@Override
	public List<Message> findByPlace(double latitude, double longitude, int distance) {
			
		Session session = sessionFactory.getCurrentSession();
			
		SQLQuery sql = session.createSQLQuery("select * from remessage " + 
			"where 6378137 * ACOS((sin(latitude / 180 * PI()) * SIN("+ latitude +" / 180 * PI()))" + 
			"+ (COS(latitude / 180 * PI()) * COS("+ latitude +" / 180 * PI())" + 
			"* COS(("+ longitude + " - longitude) / 180 * PI()))) <= "+ distance +" AND is_active = 1" 
			).addEntity(ReMessage.class);	
			
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
	@Override
	public Message getNewMessage() {
		Session session = sessionFactory.getCurrentSession();
		Message msg = (ReMessage) session.createSQLQuery("SELECT * FROM remessage WHERE number = (SELECT MIN(number) FROM remessage)").addEntity(ReMessage.class).list().get(0);
		Message newMsg = new ReMessage();
		newMsg.setNumber(msg.getNumber() - 1);
		return newMsg;
	}
	
	@Override
	public void insert(Message message) {
		
		
		Session session = sessionFactory.getCurrentSession();
		session.save((ReMessage)message);
	}
	
	@Override
	public Message findByNumber(long number) {
		
		Session session = sessionFactory.getCurrentSession();
		return (Message) session.load(ReMessage.class, number);
	}
	
	@Override
	public void remove(Message message) {
		
		Session session = sessionFactory.getCurrentSession();

		message.setIsActive(0);
		session.update((ReMessage)message);
	}
}


