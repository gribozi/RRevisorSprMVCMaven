package javapackage.dao.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javapackage.domain.Restaurant;
import javapackage.util.HibernateUtil;

// @Repository не нужно, так как этот бин "поднимаем" не автосканом, а в файле applicationContext.xml
@SuppressWarnings("unchecked")
public class RestaurantDAOHiberImpl implements RestaurantDAO {

	// Возвращает список всех ресторанов, выбранных из БД. Параметр метода задает способ сортировки результата.
	@Override
	public List<Restaurant> readAllRestaurants(String sort) {

		Session session = null;
		List<Restaurant> rests = new ArrayList<Restaurant>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (sort.equals("rateTotal")) {
				rests = session.createSQLQuery("SELECT * FROM restaurants ORDER BY (cuisine_rating * 0.4 + interior_rating * 0.3 + service_rating * 0.3) DESC")
						.addEntity(Restaurant.class)
						.list();
			} else {
				rests = session.createCriteria(Restaurant.class).addOrder(Order.desc(sort)).list();
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return rests;
	}

	// Возвращает список всех ресторанов, выбранных из БД и удовлетворяющих поисковому запросу
	@Override
	public List<Restaurant> readAllRestaurantsBySearch(String searchQuery) {

		Session session = null;
		List<Restaurant> rests = new ArrayList<Restaurant>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			rests = session.createCriteria(Restaurant.class)
					.add(Restrictions.or(
							Restrictions.like("name", searchQuery, MatchMode.ANYWHERE),
							Restrictions.like("review", searchQuery, MatchMode.ANYWHERE)
							)
						)
					.list();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return rests;
	}

	// Возвращает один заданный ресторан, выбранный из БД
	@Override
	public Restaurant readRestaurantById(int id) {

		Session session = null;
		Restaurant rest = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			// Не работает через load (что-то не то возвращается, проблема при обращении к объекту из jsp-файла). Зато работает через get.
			rest = (Restaurant) session.get(Restaurant.class, id);
			// Альтернатива через createSQLQuery
			// rest = (Restaurant) session.createSQLQuery("SELECT * FROM restaurants WHERE id = ?").addEntity(Restaurant.class).setInteger(0, id).uniqueResult();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return rest;
	}

	// Редактирует заданный ресторан а БД
	@Override
	public boolean updateRestaurant(Restaurant restaurant) {

		boolean result = false;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(restaurant);
			session.getTransaction().commit();

			result = true;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	// Удаляет заданные рестораны из БД
	@Override
	public boolean deleteRestaurants(int[] selected_rests) {

		boolean result = false;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			for (int rest : selected_rests) {
				Restaurant restaurant = (Restaurant) session.load(Restaurant.class, rest);
				session.delete(restaurant);
			}
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	// Добавляет ресторан в БД
	@Override
	public int createRestaurant(Restaurant restaurant) {

		int result = 0;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(restaurant);
			session.getTransaction().commit();

			result = restaurant.getId();

			// Convenience method to return a single instance that matches the query, or null if the query returns no results.
			// result = (int) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

}