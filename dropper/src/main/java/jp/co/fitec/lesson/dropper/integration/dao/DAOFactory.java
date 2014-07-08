package jp.co.fitec.lesson.dropper.integration.dao;

public class DAOFactory {

	public static MessageDAO createMessageDAO() {
//		return new MessageDAO();
		return new ReMessageDAO();
		
	}
}


