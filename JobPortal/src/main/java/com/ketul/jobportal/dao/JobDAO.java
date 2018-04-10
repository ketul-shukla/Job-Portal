package com.ketul.jobportal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;

public class JobDAO extends DAO {
	
 public List<Job> list() throws Exception{
    	try {
            begin();
            Query query = getSession().createQuery("from Job where status = :status");
            query.setString("status", "hiring");
            List<Job> jobs = query.list();
            commit();
            return jobs;
        } catch (HibernateException e) {
            rollback();
            return null;
        }
 }
 
 public List<Job> jobList(int companyID) throws Exception{
	 try{
		 Query query=getSession().createQuery("from Job where companyID = :companyID and status = :status");
		 query.setInteger("companyID", companyID);
		 query.setString("status", "hiring");
		 List<Job> jobs = query.list();
		 return jobs;
	 }
	 catch(HibernateException e){
		 rollback();
		 return null;
	 }
 }
 
 public Job get(int jobID){
	 try{
		 Query query = getSession().createQuery("from Job where jobID = :jobID");
		 query.setInteger("jobID", jobID);
		 Job job = (Job) query.uniqueResult();
		 return job;
	 }
	 catch(HibernateException e){
		 rollback();
		 return null;
	 }
 }
 
 public int updateJob(int jobApplicationID){
	 try{
		 Query query=getSession().createQuery("from JobApplication where jobApplicationID = :jobApplicationID");
		 query.setInteger("jobApplicationID", jobApplicationID);
		 JobApplication jobApplication = (JobApplication) query.uniqueResult();
		 Job job = jobApplication.getJob();
		 int jobID = job.getJobID();
		 Query newQuery=getSession().createQuery("update Job set status = :status where jobID = :jobID");
		 newQuery.setInteger("jobID", jobID);
		 newQuery.setString("status","hired" );
		 int modification = newQuery.executeUpdate();
		 return modification;
	 }
	 catch(HibernateException e){
		 System.out.println(e.getMessage());
		 rollback();
		 return 0;
	 }
 }

}
