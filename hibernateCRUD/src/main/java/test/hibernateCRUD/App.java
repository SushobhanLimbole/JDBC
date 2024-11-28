package test.hibernateCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
        
        Configuration cfg  = new Configuration();
		SessionFactory s = cfg.configure().buildSessionFactory();
		System.out.println(s);
		
		Users u1 = new Users(1 , "Sushobhan");
		Users u2 = new Users(2 , "Varad");
		
		Session session = s.openSession();
		Transaction t = session.beginTransaction();
		
		session.save(u1);
		session.save(u2);
		t.commit();
    }
}
