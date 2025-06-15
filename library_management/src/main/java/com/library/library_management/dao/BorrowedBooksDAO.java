package com.library.library_management.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.library.library_management.dbConnection.DatabaseConnection;
import com.library.library_management.models.Book;
import com.library.library_management.models.BorrowedBook;

public class BorrowedBooksDAO {

	private Connection con = DatabaseConnection.getConnection();

	public void createBorrowedBooksTable() {

		try {

			String query = "CREATE TABLE borrowed_books(borrow_id INT PRIMARY KEY AUTO_INCREMENT, student_id INT, book_id INT, borrow_date DATE, return_date DATE, FOREIGN KEY(student_id) REFERENCES students(student_id), FOREIGN KEY(book_id) REFERENCES books(book_id))";

			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("BorrowBooks table created successfully");

			stmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void showAllBorrowedBooksWithStudentName() {

		try {

			String query = "SELECT s.name, bk.title, bk.author FROM students s JOIN borrowed_books bbk ON s.student_id = bbk.student_id JOIN books bk ON bbk.book_id = bk.book_id";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			System.out.println("Name Title Author");
			while (rs.next()) {

				System.out.print(rs.getString("name")+" "+rs.getString("title")+" "+rs.getString("author"));
			}

			stmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void showBorrowedBooksRecords() {

		try {

			String query = "SELECT s.name, bk.title, bbk.borrow_date FROM students s JOIN borrowed_books bbk ON s.student_id = bbk.student_id JOIN books bk ON bbk.book_id = bk.book_id";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				System.out.print(rs.getString("name"));
				System.out.print(" " + rs.getString("title"));
				System.out.print(" " + rs.getDate("borrow_date"));
				System.out.println();
			}

			stmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int getNumberOfBooksBorrowedByStudent(int id) {

		int noOfBooks = 0;

		try {

			String query = "SELECT COUNT(*) FROM borrowed_books WHERE student_id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				noOfBooks = rs.getInt(1);
			}

			pstmt.close();
			rs.close();
			return noOfBooks;

		} catch (Exception e) {
			System.out.println(e);
			return noOfBooks;
		}
	}

	public void showOverdueBooks(int overdue) {

		try {

			String query = "SELECT s.name, bk.title FROM students s JOIN borrowed_books bbk ON s.student_id = bbk.student_id JOIN books bk ON bbk.book_id = bk.book_id WHERE DATEDIFF(bbk.return_date, bbk.borrow_date) >= ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, overdue);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("name") + " " + rs.getString("title"));
			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void borrowingBookTransaction(BorrowedBook bbk) throws SQLException {

		try {

			con.setAutoCommit(false);

			String borrowBookQuery = "INSERT INTO borrowed_books(student_id, book_id, borrow_date, return_date) VALUES (?,?,?,?)";
			String updateTotalCopies = "UPDATE books SET total_copies = total_copies - 1 WHERE book_id = ?";

//			Check availability of book 
			BookDAO bkDAO = new BookDAO();
			if (!bkDAO.checkBookAvailability(bbk.getBookId())) {
				System.out.println("Book not available");
				return;
			}

			PreparedStatement pstmt1 = con.prepareStatement(borrowBookQuery);
			pstmt1.setInt(1, bbk.getStudentId());
			pstmt1.setInt(2, bbk.getBookId());
			pstmt1.setDate(3, bbk.getBorrowDate());
			pstmt1.setDate(4, bbk.getReturnDate());
			pstmt1.executeUpdate();

			PreparedStatement pstmt2 = con.prepareStatement(updateTotalCopies);
			pstmt2.setInt(1, bbk.getBookId());
			pstmt2.executeUpdate();

			con.commit();
			System.out.println("Book borrowed successfully");
			pstmt1.close();
			pstmt2.close();

		} catch (SQLException e) {
			System.out.println("Borrowing book transaction rolledback");
			con.rollback();
		}
	}

	public void returningBookTransaction(int student_id, int book_id, Date returnDate) throws SQLException {

		try {

			con.setAutoCommit(false);

			String returnBookQuery = "UPDATE borrowed_books SET return_date = ? WHERE student_id = ? AND book_id = ?";
			String updateTotalCopies = "UPDATE books SET total_copies = total_copies + 1 WHERE book_id = ?";

			PreparedStatement pstmt1 = con.prepareStatement(returnBookQuery);
			pstmt1.setDate(1, returnDate);
			pstmt1.setInt(2, student_id);
			pstmt1.setInt(3, book_id);
			pstmt1.executeUpdate();

			PreparedStatement pstmt2 = con.prepareStatement(updateTotalCopies);
			pstmt2.setInt(1, book_id);
			pstmt2.executeUpdate();

			con.commit();
			System.out.println("Book returned successfully");
			pstmt1.close();
			pstmt2.close();

		} catch (SQLException e) {
			System.out.println("Returning book transaction rolledback");
			con.rollback();
			System.out.println(e);
		}
	}

	public Book searchBookByTitle(String title) {

		try {

			String query = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setTotalCopies(rs.getInt("total_copies"));

				pstmt.close();
				rs.close();
				return book;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public ArrayList<String> getStudentsWithCustomBorrowedBooks(int noOfborrowedBooks) {

		ArrayList<String> students = new ArrayList<String>();

		try {

			String query = "SELECT s.name, COUNT(bbk.book_id) AS no_of_books_borrowed FROM students s JOIN borrowed_books bbk ON s.student_id = bbk.student_id GROUP BY s.student_id HAVING COUNT(bbk.book_id) >= ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noOfborrowedBooks);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				students.add(rs.getString("name"));
			}

			pstmt.close();
			rs.close();
			return students;

		} catch (Exception e) {
			System.out.println(e);
			return students;
		}
	}

	public void callBorrowBookProcedure(BorrowedBook bbk) {

		try {

			String call = "{CALL borrowBook(?,?,?)}";

			CallableStatement cs = con.prepareCall(call);
			cs.setInt(1, bbk.getStudentId());
			cs.setInt(2, bbk.getBookId());
			cs.setDate(3, bbk.getBorrowDate());
			cs.executeUpdate();

			cs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void callReturnBookProcedure(BorrowedBook bbk) {

		try {

			String call = "{CALL returnBook(?,?,?)}";

			CallableStatement cs = con.prepareCall(call);
			cs.setInt(1, bbk.getStudentId());
			cs.setInt(2, bbk.getBookId());
			cs.setDate(3, bbk.getReturnDate());
			cs.executeUpdate();

			cs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int callGetOverdueFineProcedure(BorrowedBook bbk) {

		try {

			String call = "{CALL getOverdueFineProcedure(?,?,?,?)}";

			CallableStatement cs = con.prepareCall(call);
			cs.setInt(1, bbk.getStudentId());
			cs.setInt(2, bbk.getBookId());
			cs.setDate(3, bbk.getReturnDate());
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();

			int fine = cs.getInt(4);

			cs.close();
			return fine;

		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}
}
