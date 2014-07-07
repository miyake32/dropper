package jp.co.fitec.lesson.dropper.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jp.co.fitec.lesson.dropper.integration.dao.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * <p>HibernateのSession管理のためのFilter。</p>
 * 
 * @author M.Yoneyama
 *
 */
@WebFilter("/*")
public class HibernateSessionManagementFilter implements Filter {

	private SessionFactory factory; // HibernateのSessionFactory

	/* 
	 * <p>Filterの初期化処理。</p>
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		// Hibernate SessionFactoryオブジェクトのセット
		factory = HibernateUtil.getSessionFactory();
	}

	/* 
	 * <p>Filterの実行（リクエストおよびレスポンス）</p>
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			
			// HTPTリクエストを受け取った際に実行
			factory.getCurrentSession().beginTransaction();
			
			chain.doFilter(request, response);

			// HTTPレスポンスを返す直前に実行
			factory.getCurrentSession().getTransaction().commit();

		} catch(Exception e) {
			
			try {
				if(factory.getCurrentSession().getTransaction().isActive()) {
					factory.getCurrentSession().getTransaction().rollback();
				}
			} catch(Throwable rbEx) {
				System.err.println(rbEx);
			}

			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {
		
	}
}
