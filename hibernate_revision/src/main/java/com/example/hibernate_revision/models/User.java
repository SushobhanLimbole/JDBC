package com.example.hibernate_revision.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// @Entity is an annotation
// Declares that the annotated class is an entity. The annotated entityclass must: 
// •be a non-final top-level class or static inner class, 
// •have a public or protected constructor with noparameters, and 
// •have no final methods or persistent instance variables.
// every '@Entity' class must declare or inherit at least one '@Id' or '@EmbeddedId' property



@Entity
// @Table(name = "table_name")	Maps the entity to a specific table name
@Table(name = "users")
public class User {

// 	@Id	Marks a field as the primary key
	@Id
//	@GeneratedValue(strategy = ...)	Auto-generates the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
//	@Column(name = "column_name", nullable = false, length = 100)	Maps a field to a column and configures properties
	@Column(nullable = false)
	private String email;
	
//	@Transient	Excludes a field from being saved in DB
//	So, "example"  column will not get created in our entity
	@Transient
	private String example;
	
//	@Basic(fetch = FetchType.LAZY)	Controls loading behavior of a column
	
//	Attribute			Values				Meaning
//	fetch				FetchType.LAZY		Loads field only when accessed
//						FetchType.EAGER		Loads field immediately with entity
//	optional			true (default)		Field can be null
//						false				Field is required (not null)
						
//	@Lob	For large objects (text/blob)
	
	public User() {}
	
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
