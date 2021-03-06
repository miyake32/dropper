package jp.co.fitec.lesson.dropper.integration.dao;

import java.math.BigInteger;
import java.util.List;

import jp.co.fitec.lesson.dropper.entity.Message;
import jp.co.fitec.lesson.dropper.entity.SimpleMessage;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class MessageDao extends BaseDao {
	
	public List<Message> findByPlace(double latitude, double longitude, int distance) {
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery sql = session.createSQLQuery("select * from message " + 
			"where 6378137 * ACOS((sin(latitude / 180 * PI()) * SIN("+ latitude +" / 180 * PI()))" + 
			"+ (COS(latitude / 180 * PI()) * COS("+ latitude +" / 180 * PI())" + 
			"* COS(("+ longitude + " - longitude) / 180 * PI()))) <= "+ distance +" AND is_active = 1" 
			).addEntity(Message.class);	
		
		List<Message> messageList = sql.list();
		
		if(messageList == null) {
			return null;
		}
		
		return messageList;
	}
	
	public void insert(Message message) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(message);
	}
	
	public void remove(SimpleMessage message) {
		
		Session session = sessionFactory.getCurrentSession();
		message.setIsActive(0);
		session.update(message);
	}
}
