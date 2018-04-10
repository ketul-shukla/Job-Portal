package com.ketul.jobportal.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="job_details")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="jobID")
	private int jobID;
	
	@Column(name="jobTitle")
	private String jobTitle;
	
	@Column(name="jobType")
	private String jobType;
	
	@Column(name="jobField")
	private String jobField;
	
	@Column(name="contactEmailID")
	private String contactEmailID;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
//	@JoinColumn(name="companyID")
	private Company company;
	
	@OneToMany(mappedBy="job")	
	private Set<JobApplication> jobApplication = new HashSet<JobApplication>();
	
	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<JobApplication> getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(Set<JobApplication> jobApplication) {
		this.jobApplication = jobApplication;
	}

	public String getJobField() {
		return jobField;
	}

	public void setJobField(String jobField) {
		this.jobField = jobField;
	}

	public String getContactEmailID() {
		return contactEmailID;
	}

	public void setContactEmailID(String contactEmailID) {
		this.contactEmailID = contactEmailID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
