package com.library.library_management.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnection {
	
	private static Connection con;

//	Method to create connect to database
	public static void connectDatabase() {
		
		try {
			
//			Load the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded successfully");
			
//			Connect Database and return the object
			String url = "jdbc:mysql://localhost:3306/library_management";
			String username = "root";
			String password = "root";
			con = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			System.out.println("error : "+e);
		}
	}
	
//	Method to get connection object
	public static Connection getConnection() {
		return con;
	}
	
}
