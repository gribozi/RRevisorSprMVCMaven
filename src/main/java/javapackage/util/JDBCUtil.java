package javapackage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtil {
	
	// Возвращает соединение с БД, которое будем использовать в последующих методах
	public static Connection getDBConnection() throws Exception {
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
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

}
