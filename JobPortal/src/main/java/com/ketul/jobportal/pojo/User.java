package com.ketul.jobportal.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="login_details")
@Inheritance(strategy=InheritanceType.JOINED)
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userID")
	private int userID;
	
	@Column(name="emailID")
	private String emailID;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(){}
	
//	public User(String emailID, String password) {
//		this.emailID = emailID;
//		this.password = password;
//	}

	
	public String getEmailID() {
		return emailID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
