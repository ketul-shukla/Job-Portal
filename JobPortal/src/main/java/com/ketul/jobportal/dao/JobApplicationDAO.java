package com.ketul.jobportal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;

public class JobApplicationDAO extends DAO{

	public List<JobApplication> getApplicants(int jobID) throws Exception{
		try{
			 Query query=getSession().createQuery("from JobApplication where jobID = :jobID and status = :status");
			 query.setInteger("jobID", jobID);
			 query.setString("status", "applied");
			 List<JobApplication> jobApplicants = query.list();
			 return jobApplicants;
		}
		catch(HibernateException e){
			 rollback();
			 return null;
		 }
	}
	
	public JobApplication checkApplication(int jobID, int userID) throws Exception{
		try{
			 Query query=getSession().createQuery("from JobApplication where jobID = :jobID and userID = :userID");
			 query.setInteger("jobID", jobID);
			 query.setInteger("userID", userID);
			 JobApplication jobApplication = (JobApplication) query.uniqueResult();
			 return jobApplication;
		}
		catch(HibernateException e){
			 rollback();
			 return null;
		 }
	}
	
	public int updateJobApplication(int jobApplicationID) throws Exception{
		try{
			Query query=getSession().createQuery("update JobApplication set status = :status where jobApplicationID = :jobApplicationID");
			query.setInteger("jobApplicationID", jobApplicationID);
			query.setString("status", "selected");
			int modification= query.executeUpdate();
			return modification;
		}
		catch(HibernateException e){
			 rollback();
			 System.out.println(e.getMessage());
			 return 0;
		 }
	}
	
	public JobApplication getHired(int jobApplicationID) throws Exception{
		 try{
			 Query query=getSession().createQuery("from JobApplication where jobApplicationID = :jobApplicationID");
			 query.setInteger("jobApplicationID", jobApplicationID);
			 JobApplication jobApplication = (JobApplication) query.uniqueResult();
			 return jobApplication; 
		 }
		 catch(HibernateException e){
			 rollback();
			 return null;
		 }
	}
	
	public List<JobApplication> getSelectedApplicants(int userID) throws Exception{
		try{
			System.out.println("3");
			Query jobQuery = getSession().createQuery("from Job where company_userID= :userID and status = :status");
		    jobQuery.setInteger("userID", userID );
		    jobQuery.setString("status", "hired");
		    List<Job> job = jobQuery.list();
		    System.out.println("4");
		    System.out.println(job.size());
		    
		    List<JobApplication> jobApplication = null;
		    for(int i=0; i<=job.size();i++){
		    	int jobID=job.get(i).getJobID();
		    	System.out.println(jobID);
		    	Query selectedApplicantQuery=getSession().createQuery("from JobApplication where jobID = :jobID and status = :status");
		    	selectedApplicantQuery.setInteger("jobID", jobID);
		    	selectedApplicantQuery.setString("status", "selected");
//		    	JobApplication jobApplicationObj=(JobApplication) selectedApplicantQuery.uniqueResult();
//		    	System.out.println(jobApplicationObj.getPerson().getEmailID());
		    	jobApplication.add((JobApplication) selectedApplicantQuery.uniqueResult());
		    	System.out.println(jobApplication.get(i).getJobApplicationID());
		    }
		    System.out.println("5");
		    return jobApplication;
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			 rollback();
			 return null;
		 }
	}
}
