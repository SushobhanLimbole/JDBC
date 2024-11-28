package maven_jdbc.maven_jdc_project;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

import maven_jdbc.maven_jdc_project.Users;

public class App {
	public static void main(String[] args) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");

			String url = "jdbc:mysql://localhost:3306/jdbc";
			String userName = "root";
			String password = "root";

			Connection con = DriverManager.getConnection(url, userName, password);
			// Users obj = new Users(con);
			// obj.createTable();

			Scanner sc = new Scanner(System.in);
			// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Enter name = ");
			String name = sc.nextLine();
			System.out.print("Enter id = ");
			int id = sc.nextInt();
			// sc.skip("\n");

			Users user = new Users(con, id, name);
			user.insertData();

			sc.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
