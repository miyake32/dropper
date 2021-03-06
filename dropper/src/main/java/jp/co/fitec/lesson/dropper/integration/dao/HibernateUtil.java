package jp.co.fitec.lesson.dropper.integration.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
    	
        try {
        	
        	Configuration cfg = new Configuration().configure();       
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(cfg.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();                   
            return cfg.buildSessionFactory(standardServiceRegistry);
            
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
