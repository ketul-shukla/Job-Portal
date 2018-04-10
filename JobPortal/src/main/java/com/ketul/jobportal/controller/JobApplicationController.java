package com.ketul.jobportal.controller;

import java.io.File;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ketul.jobportal.dao.DAO;
import com.ketul.jobportal.dao.JobApplicationDAO;
import com.ketul.jobportal.dao.JobDAO;
import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;
import com.ketul.jobportal.pojo.Person;
import com.ketul.jobportal.pojo.User;

@Controller
public class JobApplicationController {
	
	@Autowired
	@Qualifier("jobDao")
	JobDAO jobDao;
	
	@Autowired
	@Qualifier("jobApplicationDao")
	JobApplicationDAO jobApplicationDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/job/apply", method = RequestMethod.GET)
	protected ModelAndView applypage(HttpServletRequest request) throws Exception{
		ModelAndView mv= new ModelAndView();
		Job job = jobDao.get(Integer.parseInt(request.getParameter("job")));
		User user = (User) request.getSession().getAttribute("user");
		int userID = user.getUserID();
		JobApplication jobApplication = jobApplicationDao.checkApplication(Integer.parseInt(request.getParameter("job")),userID);
		if(jobApplication == null){
			mv.addObject("job", job);
			mv.addObject("jobApplication", new JobApplication());
			mv.setViewName("job-apply");
		}
		else{
			mv.addObject("errorMessage", "You have already applied for this job. Please apply to other job.");
			mv.setViewName("error");
		}
		return mv;
	}
	
	@RequestMapping(value="/job/apply",method=RequestMethod.POST)
	protected ModelAndView applyJob(HttpServletRequest request, @ModelAttribute("jobApplication") JobApplication jobApplication, BindingResult result) throws Exception{
		try{
			DAO.begin();
			Job job = jobDao.get(Integer.parseInt(request.getParameter("job")));
			jobApplication.setJob(job);
			Person person = (Person) request.getSession().getAttribute("user");
			jobApplication.setPerson(person);
			
			File localFile;
			CommonsMultipartFile photoInMemory=jobApplication.getUpload();
			String name = photoInMemory.getOriginalFilename();
			localFile =new File("C:\\Users\\ketul\\Desktop\\Resume",name);
			photoInMemory.transferTo(localFile);
			
			jobApplication.setResume(localFile.getPath());
			jobApplication.setStatus("applied");
			DAO.getSession().merge(jobApplication);
			DAO.commit();
			try {
				Email email = new SimpleEmail();
		        email.setHostName("smtp.googlemail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
		        email.setSmtpPort(587);
		        email.setAuthenticator(new DefaultAuthenticator("portaljobs95@gmail.com", "jobportal95"));
		        email.setSSLOnConnect(true);
		        email.setFrom("portaljobs95@gmail.com");//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
		        email.setSubject("Application Success");
		        email.setMsg("Congratulations! you have successfully applied for"+ job.getJobTitle() + "position at" + job.getCompany().getCompanyName());
		        email.addTo(person.getEmailID());//Will come from the database
		        email.send();
		    } catch (Exception e) {
		        System.out.println("Email Exception" + e.getMessage());
		    }
			return new ModelAndView("apply-success");
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
		
	}
}
