package jp.co.fitec.lesson.dropper.integration.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceFactory {

	private static InitialContext context;
	private static DataSource ds;
	
	private DataSourceFactory() {}
	
	public static DataSource getDataSource() throws DAOException {
		
		try {
			
			if(context == null) {
				context = new InitialContext();
			}
			
			if(ds == null) {
				ds = (DataSource)context.lookup("java:/comp/env/jdbc/mysql");
			}
			
		} catch (NamingException e) {
			throw new DAOException(e);
		}
		
		return ds;
	}
}
