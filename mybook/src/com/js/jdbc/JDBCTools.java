package com.js.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTools {
	public static void release(ResultSet resultSet, Statement statement, Connection connection){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static Connection getConnection() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		String driverClass=null;
		String url=null;
		String user=null;
		String password=null;
		
		Properties properties = new Properties();
		InputStream is = 
				JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(is);
		driverClass = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("username");
		password = properties.getProperty("password");
		
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Connection connection = (Connection) driver.connect(url, info);
		return connection;
	}
}
