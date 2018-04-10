package com.ketul.jobportal.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ketul.jobportal.pojo.User;

public class UserDAO extends DAO {

	public UserDAO(){}
	
	public User getUser(String emailID, String password){
		try{
			begin();
			Query query = getSession().createQuery("from User where emailID = :emailID and password = :password");
			query.setString("emailID", emailID);
			query.setString("password", password);
			User user = (User) query.uniqueResult();
			commit();
			close();
			return user;
		}
		catch (HibernateException e) {
			rollback();
			return null;
		}
	}
	
}
