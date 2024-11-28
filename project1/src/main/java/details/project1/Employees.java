package details.project1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employees {
	
	@Id
	private int empId;
	private String empName;
	private double empSalary;
	
	void setEmpId(int empId) {
		this.empId = empId;
	}
	
	void setEmpName(String empName) {
		this.empName = empName;
	}
	
	void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	
	int getEmpId() {
		return empId;
	}
	
	String getEmpName() {
		return empName;
	}
	
	double getEmpSalary() {
		return empSalary;
	}
}
