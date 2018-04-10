package com.ketul.jobportal.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="job_application")
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jobApplicationID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userID")
	private Person person;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="jobID")
	private Job job;
	
	@Column(name="resume")
	private String resume;
	
	@Column(name="status")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	private CommonsMultipartFile upload;

	public CommonsMultipartFile getUpload() {
		return upload;
	}

	public void setUpload(CommonsMultipartFile upload) {
		this.upload = upload;
	}

	public int getJobApplicationID() {
		return jobApplicationID;
	}

	public void setJobApplicationID(int jobApplicationID) {
		this.jobApplicationID = jobApplicationID;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
}
