package jp.co.fitec.lesson.dropper.integration.dao;

import java.math.BigInteger;
import java.util.List;

import jp.co.fitec.lesson.dropper.entity.Message;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class MessageDao extends BaseDao {
	
	public List<Message> findByPlace(double latitude, double longitude, int distance) {
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery sql = session.createSQLQuery("select * from message" + 
			"where 6378137 * ACOS((sin(35.70720177894458 / 180 * PI()) * SIN("+ latitude +" / 180 * PI()))" + 
			"+ (COS(35.70720177894458 / 180 * PI()) * COS("+ latitude +" / 180 * PI())" + 
			"* COS(("+ longitude + " - 139.80443313717842) / 180 * PI()))) <= "+ distance +" AND is_active = 1" 
			).addEntity(Message.class);	
		
		List<Message> messageList = sql.list();
		
		if(messageList == null) {
			return null;
		}
		
		return messageList;
	}
	
	public boolean insert(Message message) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(message);
		return true;
	}
	
	public boolean remove(long number) {
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery sql = session.createSQLQuery("update message set is_active = 0 where number = ?");
		
		sql.setBigInteger(0, new BigInteger(new Long(number).toString()));
		
		return true;
	}
}
