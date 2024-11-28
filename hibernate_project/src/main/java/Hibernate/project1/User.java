package Hibernate.project1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name="User_Table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;
	@Column(name = "user")
	private String UserName;
	private String UserAddress;
	@Transient
	private int userNo;

	public void setUserId(int UserId) {
		this.UserId = UserId;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public void setUserAddress(String UserAddress) {
		this.UserAddress = UserAddress;
	}
	
	public void setUserNo(int userNo) {
		this.userNo=userNo;
		
	}
 
}
