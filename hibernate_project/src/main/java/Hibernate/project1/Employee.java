package Hibernate.project1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Employee {
	@Id 
	private int emp_id;
	private String emp_name;
	private double emp_salary;

	public void setEmpId(int emp_id) {
		this.emp_id = emp_id;
	}

	public void setEmpName(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setEmpSalary(double emp_salary) {
		this.emp_salary = emp_salary;
	}
	
	int getEmpId() {
		return emp_id;
	}
	String getEmpName() {
		return emp_name;
	}
	double getEmpSalary() {
		return emp_salary;
	}
}
