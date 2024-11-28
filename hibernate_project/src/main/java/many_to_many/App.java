package many_to_many;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class App {
	
	public static void main(String[] args) {
		
		Configuration cfg  = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		System.out.println(s);
		
		 Books b = new Books();
		 b.setBookName("Chhava");
		 
		 Books b1 = new Books();
		 b1.setBookName("Good Omens");
		 
		 Authors a = new Authors();
		 a.setAuthorName("Shivaji Sawant");
		 
		 Authors a2 = new Authors();
		 a2.setAuthorName("Neil Gaiman");
		 
		 Authors a3 = new Authors();
		 a3.setAuthorName("Terry Pratchett");
		
		 List<Books> bookSawant = new ArrayList<Books>();
		 bookSawant.add(b);
		 
		 List<Books> inputBooks = new ArrayList<Books>();
		 inputBooks.add(b1);
		 
		 List<Authors> inputAuthors = new ArrayList<Authors>();
		 inputAuthors.add(a);
		 
		 List<Authors> inputAuthors1 = new ArrayList<Authors>();
		 inputAuthors1.add(a2);
		 inputAuthors1.add(a3);
		 
		 
		 
		 b.setAuthors(inputAuthors);
		 
		 b1.setAuthors(inputAuthors1);
		 
		 a.setBooks(bookSawant);
		 
		 a2.setBooks(inputBooks);
		 
		 a3.setBooks(inputBooks);
		 
		Session session = s.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			
//			session.save(a);
//			session.save(a2);
//			session.save(a3);
//			session.save(b);
//			session.save(b1);
			
			Books showBooks = session.load(Books.class, 1);
			Books showBooks1 = session.load(Books.class, 2);
			
			Authors showAuthors = session.load(Authors.class,1);
			Authors showAuthors1 = session.load(Authors.class,2);
			Authors showAuthors2 = session.load(Authors.class,3);
			
			
			System.out.println("\nbook id = "+showBooks.getBookId());
			System.out.println("book name = "+showBooks.getBookName());
			showBooks.getAuthors().forEach((data) -> {
				System.out.println("author name = "+data.getAuthorName());
			});
			
			System.out.println("\nbook id = "+showBooks1.getBookId());
			System.out.println("book name = "+showBooks1.getBookName());
			showBooks1.getAuthors().forEach((data) -> {
				System.out.println("author name = "+data.getAuthorName());
			});
			
			System.out.println("\nauthor id = "+showAuthors.getAuthorId());
			System.out.println("author name = "+showAuthors.getAuthorName());
			showAuthors.getBooks().forEach((data) -> {
				System.out.println("book name = "+data.getBookName());
			});
			
			System.out.println("\nauthor id = "+showAuthors1.getAuthorId());
			System.out.println("author name = "+showAuthors1.getAuthorName());
			showAuthors1.getBooks().forEach((data) -> {
				System.out.println("book name = "+data.getBookName());
			});
			
			System.out.println("\nauthor id = "+showAuthors2.getAuthorId());
			System.out.println("author name = "+showAuthors2.getAuthorName());
			showAuthors2.getBooks().forEach((data) -> {
				System.out.println("book name = "+data.getBookName());
			});
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		t.commit();
	}
}

