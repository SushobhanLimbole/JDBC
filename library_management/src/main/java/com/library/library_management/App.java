package com.library.library_management;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.library.library_management.dbConnection.DatabaseConnection;
import com.library.library_management.models.Book;
import com.library.library_management.models.BorrowedBook;
import com.library.library_management.models.Student;
import com.library.library_management.services.LibraryService;

public class App {
	public static void main(String[] args) {

		DatabaseConnection.connectDatabase();

		LibraryService service = new LibraryService();

		service.createStudentsTable();
		service.createBooksTable();
		service.createBorrowedBooksTable();

		int choice = -1;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("\n========= Library Management System =========");
			System.out.println("1.  Display all students");
			System.out.println("2.  Register a student");
			System.out.println("3.  Update student name");
			System.out.println("4.  Update student email");
			System.out.println("5.  Delete a student");
			System.out.println("6.  Display all books");
			System.out.println("7.  Add a book");
			System.out.println("8.  Add multiple books");
			System.out.println("9.  Update book title");
			System.out.println("10.  Update book author");
			System.out.println("11.  Update book copies");
			System.out.println("12. Delete a book");
			System.out.println("13. Update all books (bulk)");
			System.out.println("14. Check book availability");
			System.out.println("15. Search book by title");
			System.out.println("16. Borrow a book");
			System.out.println("17. Return a book");
			System.out.println("18. Show borrowed books");
			System.out.println("19. Show borrowed books with student names");
			System.out.println("20. Show overdue books");
			System.out.println("21. Get number of books borrowed by a student");
			System.out.println("22. Get students with custom no. of borrowed books");
			System.out.println("23. Call procedure: Borrow book");
			System.out.println("24. Call procedure: Return book");
			System.out.println("25. Call procedure: Get overdue fine");
			System.out.println("26. Create all tables");
			System.out.println("27. Exit");
			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				service.displayStudentRecords();
				break;
			case 2:
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter email: ");
				String email = scanner.nextLine();
				Student stu = new Student();
				stu.setName(name);
				stu.setEmail(email);
				service.registerStudent(stu);
				break;
			case 3:
				System.out.print("Enter student ID: ");
				int sid1 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter new name: ");
				String newName = scanner.nextLine();
				service.updateStudentName(sid1, newName);
				break;
			case 4:
				System.out.print("Enter student ID: ");
				int sid2 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter new email: ");
				String newEmail = scanner.nextLine();
				service.updateStudentEmail(sid2, newEmail);
				break;
			case 5:
				System.out.print("Enter student ID: ");
				int sid3 = scanner.nextInt();
				service.deleteStudent(sid3);
				break;
			case 6:
				service.displayAllBooks();
				break;
			case 7:
				System.out.print("Enter title: ");
				String title = scanner.nextLine();
				System.out.print("Enter author: ");
				String author = scanner.nextLine();
				System.out.print("Enter total copies: ");
				int totalCopies = scanner.nextInt();
				Book bk1 = new Book();
				bk1.setTitle(title);
				bk1.setAuthor(author);
				bk1.setTotalCopies(totalCopies);
				service.addBook(bk1);
				break;
			case 8:
				System.out.print("How many books to add? ");
				int count = scanner.nextInt();
				scanner.nextLine();
				List<Book> books = new ArrayList<Book>();
				for (int i = 0; i < count; i++) {
					System.out.print("Title: ");
					String t = scanner.nextLine();
					System.out.print("Author: ");
					String a = scanner.nextLine();
					System.out.print("Total copies: ");
					int c = scanner.nextInt();
					scanner.nextLine();
					Book bk2 = new Book();
					bk2.setTitle(t);
					bk2.setAuthor(a);
					bk2.setTotalCopies(c);
					books.add(bk2);
				}
				service.addBooks(books);
				break;
			case 9:
				System.out.print("Enter book ID: ");
				int bid1 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("New title: ");
				String nt = scanner.nextLine();
				service.updateBookTitle(bid1, nt);
				break;
			case 10: 
				System.out.print("Enter book ID: ");
				int bid2 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("New author: ");
				String na = scanner.nextLine();
				service.updateBookAuthor(bid2, na);
				break;
			case 11: 
				System.out.print("Enter book ID: ");
				int bid3 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("New total copies: ");
				int nc = scanner.nextInt();
				service.updateBookTotalCopies(bid3, nc);
				break;
			case 12:
				System.out.print("Enter book ID to delete: ");
				int bid4 = scanner.nextInt();
				service.deleteBook(bid4);
				break;
			case 13:
				service.updateAllBooks();
				break;
			case 14:
				System.out.print("Enter book ID: ");
				int bid5 = scanner.nextInt();
				service.checkBookAvailability(bid5);
				break;
			case 15:
				System.out.print("Enter book title to search: ");
				String sTitle = scanner.nextLine();
				Book foundBook = service.searchBookByTitle(sTitle);
				if (foundBook != null) {
					System.out.println("Book found: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
				} else {
					System.out.println("Book not found.");
				}
				break;
			case 16:
				System.out.print("Enter student ID: ");
				int bsid = scanner.nextInt();
				System.out.print("Enter book ID: ");
				int bbid = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter issue date (yyyy-mm-dd): ");
				Date issueDate = Date.valueOf(scanner.nextLine());
				BorrowedBook bbk1 = new BorrowedBook();
				bbk1.setStudentId(bsid);
				bbk1.setBookId(bbid);
				bbk1.setBorrowDate(issueDate);
				service.borrowBook(bbk1);
				break;
			case 17:
				System.out.print("Enter student ID: ");
				int rsid = scanner.nextInt();
				System.out.print("Enter book ID: ");
				int rbid = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter return date (yyyy-mm-dd): ");
				Date returnDate = Date.valueOf(scanner.nextLine());
				service.returnBook(rsid, rbid, returnDate);
				break;
			case 18:
				service.showBorrowedBooks();
				break;
			case 19:
				service.showBorrowedBookswithStudentName();
				break;
			case 20:
				System.out.print("Enter no. of overdue days: ");
				int overdueDays = scanner.nextInt();
				service.showOverdueBooks(overdueDays);
				break;
			case 21:
				System.out.print("Enter student ID: ");
				int sid4 = scanner.nextInt();
				service.displayNoOfBooksBorrowedByStudent(sid4);
				break;
			case 22:
				System.out.print("Enter borrowing limit: ");
				int limit = scanner.nextInt();
				List<String> students = service.getStudentsWithCustomBorrowedBooks(limit);
				students.forEach(System.out::println);
				break;
			case 23:
				System.out.print("Enter student ID: ");
				int psid1 = scanner.nextInt();
				System.out.print("Enter book ID: ");
				int pbid1 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter issue date (yyyy-mm-dd): ");
				Date pIssue = Date.valueOf(scanner.nextLine());
				BorrowedBook bbk2 = new BorrowedBook();
				bbk2.setStudentId(psid1);
				bbk2.setBookId(pbid1);
				bbk2.setBorrowDate(pIssue);
				service.callBorrowBookProcedure(bbk2);
				break;
			case 24:
				System.out.print("Enter student ID: ");
				int psid2 = scanner.nextInt();
				System.out.print("Enter book ID: ");
				int pbid2 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter return date (yyyy-mm-dd): ");
				Date pReturn = Date.valueOf(scanner.nextLine());
				BorrowedBook bbk3 = new BorrowedBook();
				bbk3.setStudentId(psid2);
				bbk3.setBookId(pbid2);
				bbk3.setBorrowDate(pReturn);
				service.callReturnBookProcedure(bbk3);
				break;
			case 25:
				System.out.print("Enter student ID: ");
				int psid3 = scanner.nextInt();
				System.out.print("Enter book ID: ");
				int pbid3 = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter return date (yyyy-mm-dd): ");
				Date pfineDate = Date.valueOf(scanner.nextLine());
				BorrowedBook bbk4 = new BorrowedBook();
				bbk4.setStudentId(psid3);
				bbk4.setBookId(pbid3);
				bbk4.setBorrowDate(pfineDate);
				int fine = service.callGetOverdueFineProcedure(bbk4);
				System.out.println("Overdue fine: ₹" + fine);
				break;
			case 26:
				service.createStudentsTable();
				service.createBooksTable();
				service.createBorrowedBooksTable();
				System.out.println("All tables created.");
				break;
			case 27:
				System.out.println("Exiting. Goodbye!");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice.");
			}
		} while (choice != 27);

		scanner.close();

	}
}
