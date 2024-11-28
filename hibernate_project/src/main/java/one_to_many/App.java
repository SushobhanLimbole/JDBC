package one_to_many;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class App {
	
	public static void main(String[] args) {
		
		Configuration cfg  = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		System.out.println(s);
		
		List<Courses> inputCourses = new ArrayList<Courses>();
		
		Courses c1 = new Courses();
		c1.setCoursename("Python");
		
		Courses c2 = new Courses();
		c2.setCoursename("Java");
		
		inputCourses.add(c1);
		inputCourses.add(c2);
		
		Student obj1 = new Student();
		obj1.setStudentName("Sushobhan");
		obj1.setCourses(inputCourses);
		
		Student obj2 = new Student();
		obj2.setStudentName("Pavan");
		obj2.setCourses(inputCourses);
		
		
		Session session = s.openSession();
		Transaction t = session.beginTransaction();
		
//		try {
//			
//			Student show = session.load(Student.class, 1);
//			System.out.println(show.getStudentId());
//			System.out.println(show.getStudentName());
//			
//			Student show1 = session.load(Student.class, 2);
//			System.out.println(show1.getStudentId());
//			System.out.println(show1.getStudentName());
//			
//		} catch(Exception e) {
//			System.out.println(e);
//		}
		
		session.save(c1);
//		session.save(c2);
		session.save(obj1);
//		session.save(obj2);
		t.commit();
	}
}

