package com.library.library_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.library.library_management.dbConnection.DatabaseConnection;
import com.library.library_management.models.Student;

public class StudentDAO {

	private Connection con = DatabaseConnection.getConnection();
	
	public void createStudentsTable() {
		
		try {
			
			String query = "CREATE TABLE students("
					+ "student_id INT PRIMARY KEY AUTO_INCREMENT,"
					+ "name VARCHAR(255),"
					+ "email VARCHAR(255))";
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Students table created successfully");
			
			stmt.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Student> getAllStudents() {
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			
			String query = "SELECT * FROM students";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				Student stu = new Student();
				stu.setStudentId(rs.getInt("student_id"));
				stu.setName(rs.getString("name"));
				stu.setEmail(rs.getString("email"));
				students.add(stu);
			}
			
			stmt.close();
			return students;
			
		} catch (Exception e) {
			System.out.println(e);
			return students;
		}
	}

	public void addStudent(Student student) {
		
		try {
			
			String query = "INSERT INTO students(name, email) VALUES (?,?)";
			
			/*
			 
			 con.prepareStatement() method is used to create a pre-compiled SQL
			 statement which we can use multiple times.
			 This is used when we want to insert dynamic data in the query.
			 It returns an PreparedStatement object, this object has methods to insert the data into 
			 the '?' in the SQL statement.
			 
			 */
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			/*
			 
			 pstmt.setInt(1, student.getStudentId()) method's first parameter is the index of 
			 the '?', it starts from 1 and the other is the data to be inserted in it.  
			 
			 */
			
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			
			/*
			 
			 pstmt.executeUpdate() method executes the SQL statement for DML
			 and returns an int having no of rows affected.
			 
			 */
			
			int rowsAffected = pstmt.executeUpdate();
			System.out.println("no of rows affected : "+rowsAffected);
			System.out.println("Student added successfully");
			
			/*
			 
			 pstmt.close() method closes the object.
			 
			 */
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void updateStudentName(int id, String name) {
		
		try {
			
			String query = "UPDATE students SET name = ? WHERE student_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Student name updated successfully");
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void updateStudentEmail(int id, String email) {
		
		try {
			
			String query = "UPDATE students SET email = ? WHERE student_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Student email updated successfully");
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void deleteStudent(int id) {
		
		try {
			
			String query = "DELETE FROM students WHERE student_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Student data deleted successfully");
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
