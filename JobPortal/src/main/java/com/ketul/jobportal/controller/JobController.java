package com.ketul.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ketul.jobportal.dao.DAO;
import com.ketul.jobportal.dao.JobApplicationDAO;
import com.ketul.jobportal.dao.JobDAO;
import com.ketul.jobportal.pojo.Company;
import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;
import com.ketul.jobportal.pojo.Person;
import com.ketul.jobportal.pojo.User;

@Controller
public class JobController {
	
	@Autowired
	@Qualifier("jobDao")
	JobDAO jobDao;
	
	@Autowired
	@Qualifier("jobApplicationDao")
	JobApplicationDAO jobApplicationDao;
	
	@RequestMapping(value="/job/post", method=RequestMethod.GET)
	protected ModelAndView jobPost() throws Exception{
		return new ModelAndView("post-job", "job", new Job());
	}
	
	@RequestMapping(value="/job/post", method=RequestMethod.POST)
	protected ModelAndView jobAdd(HttpServletRequest request, @ModelAttribute("job") Job job, BindingResult result) throws Exception{
		try{
			DAO.begin();
			job.setJobTitle(request.getParameter("jobTitle"));
			job.setJobType(request.getParameter("jobType"));
			job.setJobField(request.getParameter("jobField"));
			job.setContactEmailID(request.getParameter("contactEmailID"));
			job.setDescription(request.getParameter("jobDescription"));
			job.setStatus("hiring");
			job.setCompany((Company) request.getSession().getAttribute("user"));
			DAO.getSession().save(job);
			DAO.commit();
			return new ModelAndView("post-success", "job", job);
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/job/view", method = RequestMethod.GET)
	protected ModelAndView viewJob(HttpServletRequest request) throws Exception {
		try {			
			List<Job> jobs = jobDao.list();
			if(jobs.isEmpty()){
				return new ModelAndView("error","errorMessage","Sorry! There are no jobs to display.");
			}else{
				return new ModelAndView("job-list", "jobs", jobs);
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/job/filled", method= RequestMethod.GET)
	protected ModelAndView updateJobData(HttpServletRequest request) throws Exception{
		try{
			JobApplication jobApplication = jobApplicationDao.getHired(Integer.parseInt(request.getParameter("job")));
			try {
				Email email = new SimpleEmail();
		        email.setHostName("smtp.googlemail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
		        email.setSmtpPort(587);
		        email.setAuthenticator(new DefaultAuthenticator("portaljobs95@gmail.com", "jobportal95"));
		        email.setSSLOnConnect(true);
		        email.setFrom("portaljobs95@gmail.com");//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
		        email.setSubject("Update on your Application");
		        email.setMsg("Congratulations! you have been hired for"+ jobApplication.getJob().getJobTitle() + "position at" + jobApplication.getJob().getCompany().getCompanyName());
		        email.addTo(jobApplication.getPerson().getEmailID());//Will come from the database
		        email.send();
		    } catch (Exception e) {
		        System.out.println("Email Exception" + e.getMessage());
		    }
			int updateJobApplication = jobApplicationDao.updateJobApplication(Integer.parseInt(request.getParameter("job")));
			int updateJob = jobDao.updateJob(Integer.parseInt(request.getParameter("job")));
			User user = (User) request.getSession().getAttribute("user");
			int userID = user.getUserID();
			List<Job> jobs= jobDao.jobList(userID);
			if(jobs.isEmpty()){
				return new ModelAndView("error","errorMessage","All the job hirings are completed");
			}
			else{
				return new ModelAndView("applications-view","jobs", jobs);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
}
