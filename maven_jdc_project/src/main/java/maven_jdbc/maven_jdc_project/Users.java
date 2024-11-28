package maven_jdbc.maven_jdc_project;

import java.sql.*;

public class Users {
	
	int id;
	String name;
	Connection con;
	
	Users(Connection con){
		this.con = con;
	}
	
	Users(Connection con,int id , String name) {
		this.id = id;
		this.name = name;
		this.con = con;
	}
	
	void createTable() {
		try {
			
			Statement s = con.createStatement();		
			String q = "create table users( id int , name varchar(255) )";
			s.executeUpdate(q);
			
			System.out.println("Table created");
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void insertData() {
		
		try {
			
			
			String q = "insert into users(id,name)values(?,?)";
			
			System.out.println("id = "+id+" name = "+name);
			
			PreparedStatement p = con.prepareStatement(q);
			p.setInt(1, id);
			p.setString(2, name);
			p.executeUpdate();
			
			System.out.println("inserted data successfully!");
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
}
