package javapackage.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import javapackage.domain.User;
import javapackage.util.HibernateUtil;

//@Repository не нужно, так как этот бин "поднимаем" не автосканом, а в файле applicationContext.xml
public class UserDAOImpl implements UserDAO {

	@SuppressWarnings("unchecked")
	public User readUserByName(String username) {

		Session session = null;
		List<User> users = new ArrayList<User>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			users = session.createQuery("from User where username=?")
					.setParameter(0, username)
					.list();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
