package javapackage.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class DBWork {
	
	// Возвращает соединение с БД, которое будем использовать в последующих методах
	private static Connection getDBConnection() throws Exception {
		
		String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
		String DB_URL = "jdbc:mysql://localhost/revisor";
		
		// Способ здания параметров подключения к БД через класс Properties
		// Это нужно, что бы задать кодировку подключения, а не только логин и пароль
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password","root");
		/*
		Настройки указывающие о необходимости конвертировать данные из Unicode
		в UTF-8, который используется в нашей таблице для хранения данных
		*/
		properties.setProperty("useUnicode","true");
		properties.setProperty("characterEncoding","UTF-8");
		
		Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(DB_URL, properties);
		// Обычный, простой формат Коннекшена
		// return = DriverManager.getConnection(DB_URL, "root", "root");
	}
	
	
	// Возвращает список всех ресторанов, выбранных из БД. Параметр метода задает способ сортировки результата.
	public static ArrayList<Restaurant> getAllRestaurants(String sort) {
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		try {
			Connection conn = getDBConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rslt = null;
			switch (sort) {
				case "total_rating":
					rslt = stmt.executeQuery("SELECT id, name, cuisine_rating, interior_rating, service_rating FROM restaurants ORDER BY (cuisine_rating * 0.4 + interior_rating * 0.3 + service_rating * 0.3) DESC");
					break;
				case "cuisine_rating":
					rslt = stmt.executeQuery("SELECT id, name, cuisine_rating, interior_rating, service_rating FROM restaurants ORDER BY cuisine_rating DESC");
					break;
				case "interior_rating":
					rslt = stmt.executeQuery("SELECT id, name, cuisine_rating, interior_rating, service_rating FROM restaurants ORDER BY interior_rating DESC");
					break;
				case "service_rating":
					rslt = stmt.executeQuery("SELECT id, name, cuisine_rating, interior_rating, service_rating FROM restaurants ORDER BY service_rating DESC");
					break;					
			}
			
			while (rslt.next()) {
				list.add(new Restaurant(rslt.getInt("id"), rslt.getString("name"), rslt.getByte("cuisine_rating"), rslt.getByte("interior_rating"), rslt.getByte("service_rating")));
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
	public static ArrayList<Restaurant> getAllRestaurantsBySearch(String searchQuery) {
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		
		try {
			Connection conn = getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT id, name, cuisine_rating, interior_rating, service_rating FROM restaurants WHERE name LIKE ? OR review LIKE ?");
			stmt.setString(1, "%" + searchQuery + "%");
			stmt.setString(2, "%" + searchQuery + "%");
			ResultSet rslt = stmt.executeQuery();
			
			while (rslt.next()) {
				list.add(new Restaurant(rslt.getInt("id"), rslt.getString("name"), rslt.getByte("cuisine_rating"), rslt.getByte("interior_rating"), rslt.getByte("service_rating")));
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
	public static Restaurant getRestaurant(int id) {
		Restaurant selectedRest = null;
		
		try {
			Connection conn = getDBConnection();
			
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
	public static boolean editRestaurant(int id, String name, String review, Byte cuisine, Byte interior, Byte service) {
		boolean result = false;
		
		try {
			Connection conn = getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("UPDATE restaurants SET name = ?, review = ?, cuisine_rating = ?, interior_rating = ?, service_rating = ? WHERE id = ?");
			stmt.setString(1, name);
			stmt.setString(2, review);
			stmt.setByte(3, cuisine);
			stmt.setByte(4, interior);
			stmt.setByte(5, service);
			stmt.setInt(6, id);
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
	public static boolean removeRestaurants(int[] selected_rests) {
		boolean result = false;
		
		try {
			Connection conn = getDBConnection();
			
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
	public static int addRestaurant(String name, String review, Byte cuisine, Byte interior, Byte service) {
		int result = 0;
		
		try {
			Connection conn = getDBConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO restaurants(name, review, cuisine_rating, interior_rating, service_rating) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, name);
			stmt.setString(2, review);
			stmt.setByte(3, cuisine);
			stmt.setByte(4, interior);
			stmt.setByte(5, service);
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
