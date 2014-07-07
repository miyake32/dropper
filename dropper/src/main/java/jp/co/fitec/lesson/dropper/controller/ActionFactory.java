package jp.co.fitec.lesson.dropper.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

@WebListener
public class ActionFactory implements ServletContextListener {

	private static ServletContext context;
	
	public static Action getAction(String servletPath) throws ServletException {
		
		Action action = null;
		
		String actionName = context.getInitParameter(servletPath);
		
		try {
			action = (Action)Class.forName(actionName).newInstance();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		return action;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Not implements
	}
}
