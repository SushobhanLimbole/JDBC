package jdbc_with_notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*

🔌 JDBC (Java Database Connectivity)

🔹 What is JDBC?
JDBC is an API (Application Programming Interface) in Java that allows Java applications 
to interact with relational databases like MySQL, PostgreSQL, Oracle, etc.

It acts as a bridge between Java and databases, allowing you to:
Connect to a database
Send SQL queries
Retrieve and manipulate data

🔹 JDBC Architecture
The architecture consists of two main layers:
JDBC API: Provides classes and interfaces to connect and interact with the database.
JDBC Driver: It is a class where the implementation of the JDBC API interfaces is present.


java.sql : This is a Java package where Classes and Interfaces to interact with database is present.

The Java SQL framework allows for multiple database drivers.

Each driver should supply a class that implements the Driver interface.

Driver is a class where the implementation of the interfaces of the java.sql package is present.
The Driver contains the code to interact to the specific DBMS.
Means for example we have a mysql-driver 

Means java.sql package only contains 100% abstraction methods which has no implementation.
So A driver has to implement these interfaces and those implementation are for their specific DBMS.
for example :-
1. mysql-driver will implement the interfaces but will have code that interacts with MySQL only.
2. postgreSQL-driver implement the interfaces but will have code that interacts with PostgreSQL only.

*/

public class JdbcConnectionExample {

	public static void main(String[] args) {
		
		try {
			
			/* 
			
			Step 1 : Load the MySQL JDBC driver
			
			Loading the Driver means loading the Driver into the memory i.e. getting 
			loaded into JVM Heap and method area in JVM
			where a static method executes and an object of the Driver is created and 
			it registers itself into the DriverManger.
			
			DriverManager is class in java.sql package which manages the drivers.
			What happens when we do:
				
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Step-by-Step:
			1. Class Loading
			JVM loads the com.mysql.cj.jdbc.Driver class into memory.

			This means the Driver class gets loaded into JVM heap and method area.

			2. Static Block Execution
			Inside com.mysql.cj.jdbc.Driver class, there is a static block something like:

			static {
				try {
					java.sql.DriverManager.registerDriver(new Driver());
			    } catch (SQLException e) {
			        throw new RuntimeException("Can't register driver!", e);
			    }
			}
				
			✅ So when the class loads, the static block automatically executes.
			✅ The static block creates an object of Driver class (new Driver()) and registers it with DriverManager.
			
			*/
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/*
			 
			 Step 2 : Connect to the database
			 
			 DriverManager is a class which manages Drivers
			 DriverManager.getConnection() is a method which is used to establish a connection
			 It returns an object which contains implementation of the Connection interface methods
			 when we pass the url and credentials it automatically knows 
			 which registered Driver to load. 
			 
			 */
			
			String url = "jdbc:mysql://localhost:3306/jdbc_revision";
			String username = "root";
			String password = "root";
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			/*
			 
			 Step 3 : Create statement
			 
			 Here, con.createStatement() method is used to create a SQL statement
			 It returns an object that creates an SQL statement and contains methods to execute it. 
			 
			 */
			
			Statement stmt = con.createStatement();
			
			/*
			 
			 Step 4 : Execute Query
			 
			 Here, stmt.executeQuery() method executes the query and returns an object of ResultSet.
			 But, executeQuery() method only works on static SQL queries, 
			 it does not work on PreparedStatement and CallableStatement.
			 ResultSet: Holds data returned by SQL queries.
			 
			 */
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			
			/*
			 
			 Step 5. Process Result
			 
			 rs.next() method returns true if there is a next row present;
     		 false if there are no more rows
			 
			 */
			
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
			
			// Step 6 : Close connection
			con.close();
			
		} catch (Exception e) {
			System.out.println("error : "+e);
		}
	}
}
