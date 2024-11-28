package test.hibernateCRUD;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	int id;
	
	String name;
	
	Users(int id , String name){
		this.id = id;
		this.name = name;
	}
}
