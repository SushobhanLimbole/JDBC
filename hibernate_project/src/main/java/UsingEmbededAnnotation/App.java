package UsingEmbededAnnotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		Session session = s.openSession();
		Certificate cr = new Certificate();
		cr.setCrName("JFS");
		cr.setDuration(6);
		Student se= new Student();
		se.setName("Varad");
		se.setCr(cr);
		// Displaying data using Get method in hibernate //

		Student e = session.get(Student.class, 1);// is used to get data form database;
		System.out.println(e);
		
		if (e != null) {
			System.out.println("Student id : " + e.getId());
			System.out.println("Student name : " + e.getName());
			System.out.println("Student Certificate : " + cr.getCrName());
			System.out.println("Student duration : " + cr.getDuration());
		} else {
			System.out.println("User not found");
		}

		// Displaying data using Get method in hibernate//

		Transaction t = session.beginTransaction();
		//session.save(se);
		t.commit();
	}
}
