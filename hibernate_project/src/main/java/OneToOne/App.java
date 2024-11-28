package OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import UsingEmbededAnnotation.Certificate;
import OneToOne.Student;

public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		Session session = s.openSession();
		College college = new College();
		college.setCollege_name("DGCC");
		Student se = new Student();
		se.setStud_name("Varad");
		se.setCollege(college);
		// Displaying data using Get method in hibernate //

		Student e = session.get(Student.class, 1);// is used to get data form database;
		System.out.println(e);

		if (e != null) {
			System.out.println("Student id : " + e.getStud_id());
			System.out.println("Student name : " + e.getStud_name());
			System.out.println("College id : " + e.getCollege().getCollege_id());
			System.out.println("College name : " + e.getCollege().getCollege_name());
		} else {
			System.out.println("User not found");
		}

		// Displaying data using Get method in hibernate//

		Transaction t = session.beginTransaction();
		// session.save(college);
		// session.save(se);
		t.commit();
	}
}
