package javapackage.dao.restaurant;

import java.sql.*;
import java.util.ArrayList;

import javapackage.domain.Restaurant;
import javapackage.util.JDBCUtil;

//@Repository не нужно, так как этот бин "поднимаем" не автосканом, а в файле applicationContext.xml
public class RestaurantDAOJDBCImpl implements RestaurantDAO  {
	
	// Возвращает список всех ресторанов, выбранных из БД. Параметр метода задает способ сортировки результата.
	@Override
	public ArrayList<Restaurant> readAllRestaurants(String sort) {
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		try {
			Connection conn = JDBCUtil.getDBConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rslt = null;
			switch (sort) {
				case "rateTotal":
					rslt = stmt.executeQuery("SELECT * FROM restaurants ORDER BY (cuisine_rating * 0.4 + interior_rating * 0.3 + service_rating * 0.3) DESC");
					break;
				case "rateCuisine":
					rslt = stmt.executeQuery("SELECT * FROM restaurants ORDER BY cuisine_rating DESC");
					break;
				case "rateInterior":
					rslt = stmt.executeQuery("SELECT * FROM restaurants ORDER BY interior_rating DESC");
					break;
				case "rateService":
					rslt = stmt.executeQuery("SELECT * FROM restaurants ORDER BY service_rating DESC");
					break;					
			}
			
			while (rslt.next()) {
				list.add(new Restaurant(rslt.getInt("id"), rslt.getString("name"), rslt.getString("review"), rslt.getByte("cuisine_rating"), rslt.getByte("interior_rating"), rslt.getByte("service_rating")));
			}
			
			conn.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
			//System.exit(0);
		}
		return list; 
	}
	
	
	// Возвращает список всех ресторанов, выбранных из БД и удовлетворяющих поисковому запросу
	@Override
	public ArrayList<Restaurant> readAllRestaurantsBySearch(String searchQuery) {
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		try {
			Connection conn = JDBCUtil.getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT id, name, review, cuisine_rating, interior_rating, service_rating FROM restaurants WHERE name LIKE ? OR review LIKE ?");
			stmt.setString(1, "%" + searchQuery + "%");
			stmt.setString(2, "%" + searchQuery + "%");
			ResultSet rslt = stmt.executeQuery();
			
			while (rslt.next()) {
				list.add(new Restaurant(rslt.getInt("id"), rslt.getString("name"), rslt.getString("review"), rslt.getByte("cuisine_rating"), rslt.getByte("interior_rating"), rslt.getByte("service_rating")));
			}
			
			conn.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
			//System.exit(0);
		}
		return list;
	}
	
	
	// Возвращает один заданный ресторан, выбранный из БД
	@Override
	public Restaurant readRestaurantById(int id) {
		Restaurant selectedRest = null;
		
		try {
			Connection conn = JDBCUtil.getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT id, name, review, cuisine_rating, interior_rating, service_rating FROM restaurants WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rslt = stmt.executeQuery();
			// Изначально курсор расположен непосредственно перед первой строкой объекта ResultSet. 
			// Первый вызов метода next() устанавливает курсор на первой строке и делает ее текущей.
			rslt.next();
			
			selectedRest = new Restaurant(rslt.getInt("id"), rslt.getString("name"), rslt.getString("review"), rslt.getByte("cuisine_rating"), rslt.getByte("interior_rating"), rslt.getByte("service_rating"));
			
			conn.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
			//System.exit(0);
		}
		
		return selectedRest; 
	}
	
	
	// Редактирует заданный ресторан а БД
	@Override
	public boolean updateRestaurant(Restaurant restaurant) {
		boolean result = false;
		
		try {
			Connection conn = JDBCUtil.getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("UPDATE restaurants SET name = ?, review = ?, cuisine_rating = ?, interior_rating = ?, service_rating = ? WHERE id = ?");
			stmt.setString(1, restaurant.getName());
			stmt.setString(2, restaurant.getReview());
			stmt.setByte(3, restaurant.getRateCuisine());
			stmt.setByte(4, restaurant.getRateInterior());
			stmt.setByte(5, restaurant.getRateService());
			stmt.setInt(6, restaurant.getId());
			stmt.executeUpdate();
			
			conn.close();
			result = true;
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
			//System.exit(0);
		}
		
		return result; 
	}
	
	
	// Удаляет заданные рестораны из БД
	@Override
	public boolean deleteRestaurants(int[] selected_rests) {
		boolean result = false;
		
		try {
			Connection conn = JDBCUtil.getDBConnection();
			
			for (int id : selected_rests) {
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM restaurants WHERE id = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}
			
			conn.close();
			result = true;
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
			//System.exit(0);
		}
		
		return result; 
	}		
	
	
	// Добавляет ресторан в БД
	@Override
	public int createRestaurant(Restaurant restaurant) {
		int result = 0;
		
		try {
			Connection conn = JDBCUtil.getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO restaurants(name, review, cuisine_rating, interior_rating, service_rating) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, restaurant.getName());
			stmt.setString(2, restaurant.getReview());
			stmt.setByte(3, restaurant.getRateCuisine());
			stmt.setByte(4, restaurant.getRateInterior());
			stmt.setByte(5, restaurant.getRateService());
			stmt.executeUpdate();
			
			// Находим id только что добавленного ресторана с помощью специального MySQL-запроса
			Statement stmt2 = conn.createStatement();
			ResultSet rslt2 = stmt2.executeQuery("SELECT LAST_INSERT_ID()");
			rslt2.next();
			result = rslt2.getInt(1);
			
			conn.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			//e.printStackTrace();
			//System.exit(0);
		}
		
		return result; 
	}

}
