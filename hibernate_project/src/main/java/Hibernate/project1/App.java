package Hibernate.project1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
//		System.out.println(s);
//		Employee emp1 =new Employee();
//		emp1.setEmpId(1);
//		emp1.setEmpName("varad");
//		emp1.setEmpSalary(3000);
//		int emp_id=emp1.getEmpId();
//		System.out.println("Emp id:"+emp_id);

//		String emp_name=emp1.getEmpName();
//		System.out.println(emp_name);
//		
//		double emp_salary=emp1.getEmpSalary();
//		System.out.println(emp_salary);

//		User user1=new User();
//		User user2=new User();
		// user1.setUserId(1);
//		user1.setUserName("varad");
//		user1.setUserAddress("satara maharashtra");
//		user1.setUserNo(909020550);
		// user2.setUserId(2);
//		user2.setUserName("sahil");
//		user2.setUserAddress("koregaon maharashtra");
//		user2.setUserNo(1022254365);
		Session session = s.openSession();
		Transaction t = session.beginTransaction();

		// Displaying data using Get method in hibernate //

//		Employee e = session.get(Employee.class, 2);// is used to get data form database;
//		System.out.println(e);
//		
//		if (e != null) {
//			System.out.println("Employee id : " + e.getEmpId());
//			System.out.println("Employee name : " + e.getEmpName());
//			System.out.println("Employee salary : " + e.getEmpSalary());
//		} else {
//			System.out.println("User not found");
//		}

		// Displaying data using Get method in hibernate//

		// Displaying data using Load method in hibernate//

		try {
			Employee emp = session.load(Employee.class, 2);// is used to load data form database;
			System.out.println(emp);// LOAD method do not return anything such as null so if else do not affect instead give try catch;
			System.out.println(emp.getEmpId());
			System.out.println(emp.getEmpName());
			System.out.println(emp.getEmpSalary());

		} catch (Exception e) {
			System.out.println("Object not found");
		}

		// Displaying data using Load method in hibernate//
//		session.save(emp1);	
//		session.save(user2);
//		session.save(emp1);
		t.commit();
	}
}
