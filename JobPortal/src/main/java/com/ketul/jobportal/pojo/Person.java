package com.ketul.jobportal.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "User_registration")
@PrimaryKeyJoinColumn(name ="userID")
public class Person extends User{
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="country")
	private String country;
	
	@Column(name = "state")
	private String state;
	
	@Column(name="zipcode")
	private int zipCode;
	
	@Column(name="careerLevel")
	private String currentCareerLevel;
	
	@Column(name="education")
	private String educationLevel;
	
	@OneToMany(mappedBy="person")	
	private Set<JobApplication> jobApplication = new HashSet<JobApplication>();
	
	public Set<JobApplication> getJobApplication() {
		return jobApplication;
	}
	public void setJobApplication(Set<JobApplication> jobApplication) {
		this.jobApplication = jobApplication;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCurrentCareerLevel() {
		return currentCareerLevel;
	}
	public void setCurrentCareerLevel(String currentCareerLevel) {
		this.currentCareerLevel = currentCareerLevel;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	
	
}
