package details.project1;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "user")
	private String userName;
	private String userAddress;
	
	@Transient
	private int userPhoneNo;
	
	void setUserId(int userId) {
		this.userId = userId;
	}
	
	void setUserName(String userName) {
		this.userName = userName;
	}
	
	void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	void setUserPhoneNo(int userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
 	}
	
	int getUserId() {
		return userId;
	}
	
	String getUserName() {
		return userName;
	}
	
	String getUserAddress() {
		return userAddress;
	}
	
	int getUserPhoneNo() {
		return userPhoneNo;
	}
	
	
}
