package com.ketul.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ketul.jobportal.dao.JobSeekerDAO;
import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;
import com.ketul.jobportal.pojo.User;

@Controller
public class JobSeekerController {
	
	@Autowired
	@Qualifier("jobSeekerDao")
	JobSeekerDAO jobSeekerDao;
	
	@RequestMapping(value="/view/myapplications",method=RequestMethod.GET)
	protected ModelAndView myApplications(HttpServletRequest request) throws Exception{
		try{
			User user = (User) request.getSession().getAttribute("user");
			List<JobApplication> jobApplication = jobSeekerDao.getMyApplications(user.getUserID());
			if(jobApplication.isEmpty()){
				return new ModelAndView("error","errorMessage","You haven't applied any where");
			}
			else
			{
				return new ModelAndView("myapplications-view","jobApplication",jobApplication);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value="/view/mycalls",method=RequestMethod.GET)
	protected ModelAndView myCalls(HttpServletRequest request) throws Exception{
		try{
			User user = (User) request.getSession().getAttribute("user");
			List<JobApplication> jobApplication = jobSeekerDao.getMyCalls(user.getUserID());
			if(jobApplication.isEmpty()){
				return new ModelAndView("error","errorMessage","You haven't got any calls till now");
			}
			else
			{
			return new ModelAndView("mycalls-view","jobApplication",jobApplication);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/view/internships" , method = RequestMethod.GET)
	protected ModelAndView internships() throws Exception{
		try{
			List<Job> jobs = jobSeekerDao.getInternships();
			if(jobs.isEmpty()){
				return new ModelAndView("error","errorMessage","There are no internship openings for now");
			}
			else{
				return new ModelAndView("internships-view","jobs",jobs);
				
			}
				
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/view/fulltime" , method = RequestMethod.GET)
	protected ModelAndView fulltime() throws Exception{
		try{
			List<Job> jobs = jobSeekerDao.getfulltime();
			if(jobs.isEmpty()){
				return new ModelAndView("error","errorMessage","There are no full time openings for now");
			}
			else{
				return new ModelAndView("fulltime-view","jobs",jobs);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value="view/companyjobs", method = RequestMethod.GET)
	protected ModelAndView searchPage() throws Exception{
		return new ModelAndView("search-company");
	}
	
	@RequestMapping(value="view/companyjobs", method=RequestMethod.POST)
	protected ModelAndView companyJobs(HttpServletRequest request) throws Exception{
		try{
			String search = request.getParameter("companyName");
			List<Job> jobs = jobSeekerDao.getcompanyjobs(search);
			if(jobs.isEmpty()){
				return new ModelAndView("error","errorMessage","Hiring process is closed at this company for now. Please try again!");
			}
			else{
				return new ModelAndView("companyjob-view","jobs",jobs);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
}
