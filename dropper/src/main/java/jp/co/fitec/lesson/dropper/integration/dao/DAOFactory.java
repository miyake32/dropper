package jp.co.fitec.lesson.dropper.integration.dao;

public class DAOFactory {

	public static MessageDao createMessageDao() {
		return new MessageDao();
	}
}
