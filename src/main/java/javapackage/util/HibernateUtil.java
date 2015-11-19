/*
 * Было бы правильно вместо этого статитческого класса реализовать 
 * конфигурацию по-спринговому (через бины). Но это не получилось.
 * Как это сделать рассказывается здесь:
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/orm.html#orm-session-factory-setup
 */

package javapackage.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	static {
		try {
			// creates the session factory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
