package com.example.hibernate_revision;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.hibernate_revision.models.User;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate App started");

		// Hibernate is a Java-based Object-Relational Mapping (ORM) framework.
		// It allows Java developers to map Java objects (classes) to relational
		// database tables and vice versa
		// — making it much easier to interact with databases using objects instead of
		// SQL queries.
		// In Hibernate, a single java object means a single row of that entity

		// 1.Configure
		// Configuration.configure() : Loads DB and Hibernate settings from
		// hibernate.cfg.xml
		// addAnnotatedClass() : It maps @Entity annotated class to db table
		Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);

		// 2. Build SessionFactory
		// SessionFactory : Creates sessions; heavy object; used only once
		// SessionFactory is like a connection pool where there are many connection
		// instances
		SessionFactory sessionFactory = cfg.buildSessionFactory();

		// 3. Build Session
		// Session : Like a single connection to DB; used to perform operations
		// we opened a session by openSession()
		// The primary purpose of the Session is to offer create, read, and delete
		// operations for instances of mapped entity classes.
		// Session object contains methods for db crud operations
		Session session = sessionFactory.openSession();

		// ❌ Does not work
		// User u1 = new User();
		// u1.setId(1);
		// u1.setName("Sushobhan");
		// session.persist(u1);

		// 4. Begin a Transaction
		// Transaction : Starts and commits/rolls back a DB operation
		// We can create multiple transaction from a single session
		// because there can be multiple transactions in a single session
		Transaction tx1 = session.beginTransaction();

		// 5. Perform Operations
		// This is a single java object means a single record of user entity in db
		// persist() adds a record in the entity
		// It throws an jakarta.persistence.EntityExistsException exception if you already have a record of the same id in db

		// ✔️ Works
		// The below code works and through we can conclude that
		// We need to begin a transaction even if we need to do a single operation in a
		// session
		User u1 = new User();
		u1.setId(1);
		u1.setName("Sushobhan");
		session.persist(u1);

		// 6. Commit Transaction
		// commit() method commits the transaction
		tx1.commit();

		Transaction tx2 = session.beginTransaction();
		User u2 = new User(2, "Parnika");
		session.persist(u2);
		tx2.commit();

		Transaction tx3 = session.beginTransaction();

		u1.setName("Sushobhan Limbole");

		// session.update(u1);
		// update() method not available in hibernate 7 as it throws error if entity
		// with same ID is already in session
		// so we use merge()
		// update the existing row in the DB with ID = 1, or
		// insert it if it doesn't exist (depending on DB and Hibernate config).

		// Hibernate doesn't allow two instances of the same entity (same primary key)
		// to exist in the same Session.

		// Let's Say :

		// You already loaded a User with ID = 1 into the session:

		// User user1 = session.get(User.class, 1); // Hibernate tracks this
		// Now you create a new object with the same ID manually:
		//
		// User user2 = new User();
		// user2.setId(1);
		// user2.setName("New Name");
		// Then you try to update it:
		//
		// session.update(user2); // ❌ ERROR: NonUniqueObjectException
		// 🎯 Because Hibernate says:
		//
		// "Hey! I already have an object with ID 1 in the session (user1). You’re
		// giving me another one (user2) with the same ID. I don’t know which one is
		// correct!"
		//
		// ✅ How to Solve It?
		// 🔹 Option 1: Use merge() instead of update()
		//
		// session.merge(user2); // ✅ SAFE!
		// Hibernate will:
		//
		// Compare the two,
		//
		// Merge changes into the one it already tracks (or create a new managed copy),
		//
		// No error!
		//
		// 🔹 Option 2: Detach the first object or clear the session
		//
		// session.evict(user1); // Removes it from session
		// session.update(user2); // Now it's safe
		//
		// Or:
		//
		// session.clear(); // Removes all tracked entities
		// session.update(user2);

		// Action Safe? Why
		// session.update(user2) ❌ Fails if another object with same ID is in session
		// session.merge(user2) ✅ Merges changes safely, even if ID already exists

		// So, now we have to use merge
		// merge() method returns an managed instance
		User updatedUser = session.merge(u1);
		System.out.println(updatedUser.getId());
		System.out.println(updatedUser.getName());

		// To get a record from db
		// Method							Returns null 	Loads immediately?	Hibernate 6+	Notes
		//									if not found?	
		// session.get(User.class, id)		✅ Yes			✅ Yes				✅ Supported		Most common
		// session.find(User.class, id)		✅ Yes			✅ Yes				✅ Preferred		JPA-friendly
		// session.byId().load(id)			✅ Yes			✅ Yes				✅ Supported		Chaining-style
		// session.load(User.class, id)		❌ No (throws 	❌ Lazy				❌ Removed		Avoid in new code
		//									exception)	

		session.persist(new User(3, "b"));
		
		// find() will return null if it does not find a record with the given id 
		User user = session.find(User.class, 3); // Load the entity
		
		// remove() will throw an exception java.lang.IllegalArgumentException: attempt to create delete event with null entity,
		// if we try to pass a null object to remove as argument
		session.remove(user); // Delete from DB

		// ✅ This will delete the row where id = 1 from the User table.

		tx3.commit();
		
		Transaction tx4 = session.beginTransaction();
		User u3 = session.find(User.class, 1);
		u3.setName("Sushobhan Sunil Limbole");
		u3.setEmail("abc@aa");
		session.merge(u3);
		
		User u5 = new User();
		u5.setId(10);
		u5.setName("Abc");
		u5.setEmail("abcd");
		session.merge(u5);
		
		// If you try to merge a records which is not present in your table then hibernate inserts it into the table
		// but there's a catch, it only insert it if we don't give @GeneratedValue annotation else it will throw an 
		// exception jakarta.persistence.OptimisticLockException: Row was already updated or deleted by another transaction for entity
		// but what happens when we give @GeneratedValue(strategy = GenerationType.IDENTITY) 
		// and try to merge a record which is not presnt in the table,
		// This confuses Hibernate, because:
		// You're saying, “This object exists in the DB (ID = 15)”
		// But the DB has no such row
		// And Hibernate can’t insert either (because you're giving an ID, and it's supposed to auto-generate it)
		// Result: OptimisticLockException or PersistentObjectException
		tx4.commit();
		

		// 7. Close Session
		// close() method closes a session
		session.close();

		// In this app we can do all the CRUD operations in a single transaction but we
		// did it for try and error

		// Session Object Lifecycle States
		// object states help define how an entity interacts with the database via the
		// Session. There are three main states:

		// 🌱 1. Transient State
		// The object exists in memory only—it is not associated with any Hibernate
		// Session and not saved to the database.

		// It does not have a primary key assigned by the database (unless manually
		// set).

		// Example:

		// User user = new User();
		// user.setName("Sushobhan");
		// // At this point, user is transient
		// 🔥 Key Characteristics:
		// Not associated with a session

		// Not saved in DB

		// Will be garbage collected if not referenced

		// 📌 2. Persistent State
		// The object is associated with an active Session and mapped to a database row.

		// Changes made to the object will be automatically detected and saved when the
		// session is flushed or committed.

		// Example:

		// Session session = sessionFactory.openSession();
		// Transaction tx = session.beginTransaction();

		// User user = new User();
		// user.setName("Sushobhan");
		// session.save(user); // Now it's persistent

		// tx.commit();
		// session.close();
		// 🔑 Key Characteristics:
		// Bound to a session

		// Synced with the DB

		// Hibernate tracks changes (dirty checking)

		// 📴 3. Detached State
		// The object was persistent but the session is now closed or the object is
		// evicted.

		// It is not associated with any session, but still holds a database identity
		// (primary key).

		// Changes made to the object will NOT be persisted until it's re-attached.

		// Example:

		// Session session1 = sessionFactory.openSession();
		// User user = session1.get(User.class, 1);
		// session1.close(); // user is now detached

		// user.setName("Updated Name"); // change won't reflect in DB

		// Session session2 = sessionFactory.openSession();
		// session2.update(user); // reattaching to session2
		// session2.getTransaction().commit();
		// ⚠️ Key Characteristics:
		// Has a primary key (from DB)

		// Not tracked by Hibernate

		// Needs update() or merge() to become persistent again

		// 🔄 Lifecycle Summary

		// new User()
		// 		↓
		// [Transient]
		// 		↓ session.save()
		// [Persistent]
		// 		↓ session.close() OR session.evict()
		// [Detached]
		// 		↓ session.update()/merge()
		// [Persistent again]
	}
}
