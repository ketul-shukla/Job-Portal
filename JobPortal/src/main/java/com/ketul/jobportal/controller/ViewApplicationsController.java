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

import com.ketul.jobportal.dao.JobApplicationDAO;
import com.ketul.jobportal.dao.JobDAO;
import com.ketul.jobportal.pojo.Job;
import com.ketul.jobportal.pojo.JobApplication;
import com.ketul.jobportal.pojo.User;

@Controller
public class ViewApplicationsController {
	
	@Autowired
	@Qualifier("jobDao")
	JobDAO jobDao;
	
	@Autowired
	@Qualifier("jobApplicationDao")
	JobApplicationDAO jobApplicationDao;

	@RequestMapping(value = "/applications/view", method = RequestMethod.GET)
	protected ModelAndView viewApplications(HttpServletRequest request)throws Exception {
		try{
			User user = (User) request.getSession().getAttribute("user");
			int userID = user.getUserID();
			List<Job> jobs= jobDao.jobList(userID);
			if(jobs.isEmpty()){
				return new ModelAndView("error","errorMessage","All the job hirings are completed");
			}
			else
			{
				return new ModelAndView("applications-view","jobs", jobs);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/applicants/view",method = RequestMethod.GET)
	protected ModelAndView viewApplicants(HttpServletRequest request) throws Exception{
		try{
			List<JobApplication> jobApplication=jobApplicationDao.getApplicants(Integer.parseInt(request.getParameter("job")));
			if(jobApplication.isEmpty()){
				return new ModelAndView("error", "errorMessage","We don't have any applications for now");
			}
			else{
				return new ModelAndView("applicants-view","jobApplication",jobApplication);
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/applicants/selected", method = RequestMethod.GET)
	protected ModelAndView selectedApplicants(HttpServletRequest request) throws Exception{
		try{
			User user = (User) request.getSession().getAttribute("user");
			int userID = user.getUserID();
			System.out.println("1");
			List<JobApplication> jobApplication=(List<JobApplication>) jobApplicationDao.getSelectedApplicants(userID);
			System.out.println("2");
			if(jobApplication == null){
			return new ModelAndView("error", "errorMessage", "Sorry you don't have any hired applicants right now");	
			}
			else{
			return new ModelAndView("hired-applicants", "jobApplication", jobApplication );
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
}
