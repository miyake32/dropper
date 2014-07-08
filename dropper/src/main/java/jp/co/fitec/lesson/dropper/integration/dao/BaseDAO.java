package jp.co.fitec.lesson.dropper.integration.dao;

import org.hibernate.SessionFactory;

public class BaseDAO {

		 static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
}
