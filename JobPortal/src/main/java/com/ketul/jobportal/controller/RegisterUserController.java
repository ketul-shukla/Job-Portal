package com.ketul.jobportal.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ketul.jobportal.dao.DAO;
import com.ketul.jobportal.dao.UserDAO;
import com.ketul.jobportal.pojo.Company;
import com.ketul.jobportal.pojo.Person;
import com.ketul.jobportal.pojo.User;

@Controller	
@RequestMapping(value = "user/*")
public class RegisterUserController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	protected String loginUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		try{
			User user = userDao.getUser(request.getParameter("emailID"), request.getParameter("password"));
			if(user == null){
				session.setAttribute("errorMessage", "UserName or Password does not exist");
				return "error";
			}
			session.setAttribute("user", user);
			return "home";
		}
		catch(HibernateException e){
			session.setAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerNewUser() throws Exception{
		return new ModelAndView("register-user", "person" , new Person());
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView register(HttpServletRequest request,  @ModelAttribute("person") Person person, BindingResult result) throws Exception {
		try{
			User user = userDao.getUser(request.getParameter("emailID"), request.getParameter("password"));
			if(user == null)
			{
				DAO.begin();
				person.setEmailID(request.getParameter("emailID"));
				person.setPassword(request.getParameter("password"));
				person.setRole("employee");
				person.setFirstName(request.getParameter("firstName"));
				person.setLastName(request.getParameter("lastName"));
				person.setCountry(request.getParameter("country"));
				person.setState(request.getParameter("state"));
				person.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
				person.setCurrentCareerLevel(request.getParameter("careerLevel"));
				person.setEducationLevel(request.getParameter("educationLevel"));
				DAO.getSession().save(person);
				DAO.commit();
			
				request.getSession().setAttribute("user", person);
				return new ModelAndView("home", "user", person);
			}
			else{
				return new ModelAndView("error","errorMessage","Seems you are already registered with "+ user.getEmailID() +" email id. Please try using a different id." );
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/user/company", method = RequestMethod.GET)
	protected ModelAndView registerNewCompany() throws Exception{
		return new ModelAndView("register-company","company", new Company());
	}
	
	@RequestMapping(value = "/user/company", method = RequestMethod.POST)
	protected ModelAndView registerCompany(HttpServletRequest request,  @ModelAttribute("company") Company company, BindingResult result) throws Exception{
		try{
			User user = userDao.getUser(request.getParameter("emailID"), request.getParameter("password"));
			if(user == null)
			{
				DAO.begin();
				company.setCompanyName(request.getParameter("companyName"));
				company.setStreet(request.getParameter("street"));
				company.setCity(request.getParameter("city"));
				company.setState(request.getParameter("state"));
				company.setCountry(request.getParameter("country"));
				company.setRole("company");
				company.setUrl(request.getParameter("companyUrl"));
				company.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
				company.setEmailID(request.getParameter("emailID"));
				company.setPassword(request.getParameter("password"));
				DAO.getSession().save(company);
				DAO.commit();
				
				request.getSession().setAttribute("user", company);;
				return new ModelAndView("home", "user", company);
			}
			else{
				return new ModelAndView("error","errorMessage","Seems you are already registered with "+ user.getEmailID() +" email id. Please try using a different id." );
			}
		}
		catch(HibernateException e){
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	protected ModelAndView goToUserHome(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		return new ModelAndView("home","user",user);
	}
	
	@RequestMapping(value="/user/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect: /jobportal/index.jsp";
	}
}
