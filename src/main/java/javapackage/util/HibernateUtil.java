/*
 * Вместо данного олд-скульного подключения Гибернейта теперь он
 * подключается в стиле Спринга, т. е. "поднимается" как bean
 * (смотри applicationContext.xml -> Beans for Hibernate)
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/orm.html#orm-session-factory-setup
 * 
 * Данный класс и конфиг hibernate.cfg.xml больше не используются.
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
