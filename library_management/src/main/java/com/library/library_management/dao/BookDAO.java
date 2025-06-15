package com.library.library_management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.library_management.dbConnection.DatabaseConnection;
import com.library.library_management.models.Book;

public class BookDAO {

	private Connection con = DatabaseConnection.getConnection();

	public void createBooksTable() {

		try {

			String query = "CREATE TABLE books( book_id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(255), author VARCHAR(100), total_copies INT)";

			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Books table created successfully");

			stmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Book> getAllBooks() {

		ArrayList<Book> books = new ArrayList<Book>();

		try {

			String query = "SELECT * FROM books";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Book bk = new Book();
				bk.setBookId(rs.getInt("book_id"));
				bk.setTitle(rs.getString("title"));
				bk.setAuthor(rs.getString("author"));
				bk.setTotalCopies(rs.getInt("total_copies"));
				books.add(bk);

			}

			stmt.close();
			rs.close();
			return books;

		} catch (Exception e) {
			System.out.println(e);
			return books;
		}
	}

	public void addBook(Book bk) {

		try {

			String query = "INSERT INTO books(title, author, total_copies) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, bk.getTitle());
			pstmt.setString(2, bk.getAuthor());
			pstmt.setInt(3, bk.getTotalCopies());
			pstmt.executeUpdate();
			System.out.println("Book added successfully");

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void addBooks(List<Book> books) {

		try {

			String query = "INSERT INTO books(title, author, total_copies) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			books.forEach((book) -> {
				try {
					pstmt.setString(1, book.getTitle());
					pstmt.setString(2, book.getAuthor());
					pstmt.setInt(3, book.getTotalCopies());
					pstmt.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			
			pstmt.executeBatch();
			System.out.println("Books added successfully");

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e);
		} 
	}

	public void updateBookTitle(int id, String title) {

		try {

			String query = "UPDATE books SET title = ? WHERE book_id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Book title updated successfully");

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateBookAuthor(int id, String author) {

		try {

			String query = "UPDATE books SET author = ? WHERE book_id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, author);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("book author updated successfully");

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateBookTotalCopies(int id, int totalCopies) {

		try {

			String query = "UPDATE books SET total_copies = ? WHERE book_id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, totalCopies);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Book total copies updated successfully");

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteBook(int id) {

		try {

			String query = "DELETE FROM books WHERE book_id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Book deleted successfully");

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateAllBooks() throws SQLException {

		try {
			
			con.setAutoCommit(false);

			String updateTitle = "UPDATE books SET title = UPPER(title)";
			String updateAuthor = "UPDATE books SET author = UPPER(author)";
			String updateTotalCopies = "UPDATE books SET total_copies = total_copies + 10";

			PreparedStatement pstmt1 = con.prepareStatement(updateTitle);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2 = con.prepareStatement(updateAuthor);
			pstmt2.executeUpdate();
			
			PreparedStatement pstmt3 = con.prepareStatement(updateTotalCopies);
			pstmt3.executeUpdate();
			
			con.commit();
			pstmt1.close();
			pstmt2.close();
			pstmt3.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println(e);
		}
	}
	
	public Boolean checkBookAvailability(int book_id) {
		
		try {
			
			String query = "SELECT total_copies FROM books WHERE book_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, book_id);
			ResultSet rs = pstmt.executeQuery();
			int copies = 0;
			
			if(rs.next()) {
				copies = rs.getInt("total_copies");
			}
			
			pstmt.close();
			
			if (copies > 0) {
				rs.close();
				return true;
			} else {
				rs.close();
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
