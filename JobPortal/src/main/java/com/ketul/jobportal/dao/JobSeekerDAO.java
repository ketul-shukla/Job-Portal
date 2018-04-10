package com.ketul.jobportal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ketul.jobportal.pojo.Company;
import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;

public class JobSeekerDAO extends DAO {
	
	public List<JobApplication> getMyApplications(int userID) throws Exception{
		try{
		Query query= getSession().createQuery("from JobApplication where userID = :userID and status = :status");
		query.setInteger("userID", userID);
		query.setString("status", "applied");
		List<JobApplication> jobApplication = query.list();
		System.out.println(jobApplication.size());
		return jobApplication;
	}
		
	catch(HibernateException e){
		System.out.println(e.getMessage());
		 rollback();
		 return null;
	 }
	}
	
	public List<JobApplication> getMyCalls(int userID) throws Exception{
		try{
		Query query= getSession().createQuery("from JobApplication where userID = :userID and status = :status");
		query.setInteger("userID", userID);
		query.setString("status", "selected");
		List<JobApplication> jobApplication = query.list();
		System.out.println(jobApplication.size());
		return jobApplication;
	}
		
	catch(HibernateException e){
		System.out.println(e.getMessage());
		 rollback();
		 return null;
	 }
	}
	
	public List<Job> getInternships() throws Exception{
		try{
			Query query = getSession().createQuery("from Job where jobType = :jobType and status = :status");
			query.setString("jobType", "Internship");
			query.setString("status", "hiring");
			List<Job> job = query.list();
			return job;
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			 rollback();
			 return null;
		 }
		
	}
	
	public List<Job> getfulltime() throws Exception{
		try{
			Query query = getSession().createQuery("from Job where jobType = :jobType and status= :status");
			query.setString("jobType", "Full-time");
			query.setString("status", "hiring");
			List<Job> job = query.list();
			return job;
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			 rollback();
			 return null;
		 }
		
	}
	
	public List<Job> getcompanyjobs(String search) throws Exception{
		try{
			Query query = getSession().createQuery("from Company where companyName = :companyName");
			query.setString("companyName", search);
			Company company = (Company) query.uniqueResult();
			int companyID = company.getUserID();
			Query newquery = getSession().createQuery("from Job where company_userID = :companyID and status = :status");
			newquery.setInteger("companyID", companyID);
			newquery.setString("status", "hiring");
			List<Job> job = newquery.list();
			return job;
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			 rollback();
			 return null;
		 }
	}
}
