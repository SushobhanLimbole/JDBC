package OneToMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		Session session = s.openSession();

		List<Course> course = new ArrayList<Course>();

		Course course3 = new Course();
		course3.setCourse_name("Flutter");
		Course course4 = new Course();
		course4.setCourse_name("Java");

		course.add(course3);
		course.add(course4);

		Student se1 = new Student();
		se1.setStud_name("Sushobhan");
		se1.setCourse(course);

		// Displaying data using Get method in hibernate //

		Student e = session.get(Student.class, 1);// is used to get data form database;
		System.out.println(e);

		if (e != null) {
			System.out.println("Student id : " + e.getStud_id());
			System.out.println("Student name : " + e.getStud_name());
			e.getCourse().forEach((temp) -> {
				System.out.println("Course name : " + temp.getCourse_name());
			});
//			System.out.println("College id : " + e.getCourse().getLast().getCourse_id());
//			System.out.println("College name : " + e.getCourse().getLast().getCourse_name());
		} else {
			System.out.println("User not found");
		}

		// Displaying data using Get method in hibernate//

		Transaction t = session.beginTransaction();
		 session.save(course3);
		 session.save(course4);
		 session.save(se1);
		t.commit();
	}
}
