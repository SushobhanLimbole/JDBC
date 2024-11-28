package UsingEmbededAnnotation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Stud_name;
	private Certificate cr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Stud_name;
	}

	public void setName(String Stud_name) {
		this.Stud_name = Stud_name;
	}

	public Certificate getCr() {
		return cr;
	}

	public void setCr(Certificate cr) {
		this.cr = cr;
	}

}
