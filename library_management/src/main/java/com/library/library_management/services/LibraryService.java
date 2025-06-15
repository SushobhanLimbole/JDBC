package com.library.library_management.services;

import java.sql.Date;
import java.util.List;

import com.library.library_management.dao.BookDAO;
import com.library.library_management.dao.BorrowedBooksDAO;
import com.library.library_management.dao.StudentDAO;
import com.library.library_management.models.Book;
import com.library.library_management.models.BorrowedBook;
import com.library.library_management.models.Student;

public class LibraryService {

	private StudentDAO stuDAO;
	private BookDAO bkDAO;
	private BorrowedBooksDAO bbkDAO;

	public LibraryService() {
		stuDAO = new StudentDAO();
		bkDAO = new BookDAO();
		bbkDAO = new BorrowedBooksDAO();
	}

	// Student services
	public void createStudentsTable() {
		stuDAO.createStudentsTable();
	}

	public void displayStudentRecords() {
		List<Student> students = stuDAO.getAllStudents();
		students.forEach((student) -> {
			System.out.print(student.getStudentId());
			System.out.print(" " + student.getName());
			System.out.print(" " + student.getEmail());
			System.out.println();
		});
	}

	public void registerStudent(Student student) {
		stuDAO.addStudent(student);
	}

	public void updateStudentName(int id, String name) {
		stuDAO.updateStudentName(id, name);
	}

	public void updateStudentEmail(int id, String email) {
		stuDAO.updateStudentEmail(id, email);
	}

	public void deleteStudent(int id) {
		stuDAO.deleteStudent(id);
	}

	// Book services
	public void createBooksTable() {
		bkDAO.createBooksTable();
	}

	public void displayAllBooks() {
		List<Book> books = bkDAO.getAllBooks();
		books.forEach((book) -> {
			System.out.print(book.getBookId());
			System.out.print(" " + book.getTitle());
			System.out.print(" " + book.getAuthor());
			System.out.print(" " + book.getTotalCopies());
			System.out.println();
		});
	}

	public void addBook(Book book) {
		bkDAO.addBook(book);
	}
	
	public void addBooks(List<Book> books) {
		bkDAO.addBooks(books);
	}

	public void updateBookTitle(int id, String title) {
		bkDAO.updateBookTitle(id, title);
	}

	public void updateBookAuthor(int id, String author) {
		bkDAO.updateBookAuthor(id, author);
	}

	public void updateBookTotalCopies(int id, int totalcopies) {
		bkDAO.updateBookTotalCopies(id, totalcopies);
	}

	public void deleteBook(int id) {
		bkDAO.deleteBook(id);
	}
	
	public void updateAllBooks() {
		
		try {
			bkDAO.updateAllBooks();	
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	public void checkBookAvailability(int book_id) {
		if (bkDAO.checkBookAvailability(book_id)) {
			System.out.println("Book available");
		} else {
			System.out.println("Book not available");
		}
	}

	// Borrowed books services
	public void createBorrowedBooksTable() {
		bbkDAO.createBorrowedBooksTable();
	}
	
	public void displayNoOfBooksBorrowedByStudent(int id) {
		System.out.println("No of books = "+bbkDAO.getNumberOfBooksBorrowedByStudent(id));
	}

	public void showBorrowedBookswithStudentName() {
		bbkDAO.showAllBorrowedBooksWithStudentName();
	}

	public void showBorrowedBooks() {
		bbkDAO.showBorrowedBooksRecords();
	}

	public void showOverdueBooks(int noOfDays) {
		bbkDAO.showOverdueBooks(noOfDays);
	}

	public void borrowBook(BorrowedBook bbk) {

		try {

			bbkDAO.borrowingBookTransaction(bbk);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void returnBook(int studentId, int bookId, Date returnDate) {

		try {

			bbkDAO.returningBookTransaction(studentId, bookId, returnDate);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Book searchBookByTitle(String title) {
		return bbkDAO.searchBookByTitle(title);
	}

	public List<String> getStudentsWithCustomBorrowedBooks(int limit) {
		return bbkDAO.getStudentsWithCustomBorrowedBooks(limit);
	}
	
	public void callBorrowBookProcedure(BorrowedBook bbk) {
		bbkDAO.callBorrowBookProcedure(bbk);
	}
	
	public void callReturnBookProcedure(BorrowedBook bbk) {
		bbkDAO.callReturnBookProcedure(bbk);
	}
	
	public int callGetOverdueFineProcedure(BorrowedBook bbk) {
		return bbkDAO.callGetOverdueFineProcedure(bbk);
	}
}
