package details.project1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		
		System.out.println(s);
		
//		Employees obj = new Employees();
//		obj.setEmpId(1);
//		obj.setEmpName("Pavan");
//		obj.setEmpSalary(15000);
//		
//		System.out.println(obj.getEmpId());
//		System.out.println(obj.getEmpName());
//		System.out.println(obj.getEmpSalary());
		
		Users obj1 = new Users();
//		obj1.setUserId(1);
		obj1.setUserName("Sushobhan");
		obj1.setUserAddress("Sadar Bazar,Satara");
		obj1.setUserPhoneNo(50);
		
		System.out.println(obj1.getUserId());
		System.out.println(obj1.getUserName());
		System.out.println(obj1.getUserAddress());
		System.out.println(obj1.getUserPhoneNo());
		
		Users obj2 = new Users();
//		obj2.setUserId(2);
		obj2.setUserName("Pavan");
		obj2.setUserAddress("Visava Naka,Satara");
		obj2.setUserPhoneNo(60);
		
		System.out.println(obj2.getUserId());
		System.out.println(obj2.getUserName());
		System.out.println(obj2.getUserAddress());
		System.out.println(obj2.getUserPhoneNo());
		
		Session session = s.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			
//			Users u = session.get(Users.class, 5);
			Users u = session.load(Users.class, 5);
			System.out.println(u.getUserName());
			
		} catch(Exception e) {
			System.out.println("User not found");
//			System.out.println(e);
		}
		
//		session.save(obj);
//		session.save(obj1);
//		session.save(obj2);
		
		t.commit();
	}
}
