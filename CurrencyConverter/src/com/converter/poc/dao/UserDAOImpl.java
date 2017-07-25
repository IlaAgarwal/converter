package com.converter.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.converter.poc.entity.User;
import com.converter.poc.entity.UserRoles;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFacotry;
	
	
	//Save the userdetails in user, user_address, user_role tables
	@Override
	public void registerUser(User user) 
		{
			logger.info(String.format(START_LOGGER_INFO+"registerUser:UserDAOImpl.class"));
			
			Session currentSession = sessionFacotry.getCurrentSession();
			currentSession.save(user);
			
			logger.info(String.format(END_LOGGER_INFO+"registerUser:UserDAOImpl.class"));
		}

	
	// get the user based on email(i.e. username)
	@Override
	 public User findByEmail(String email)
	 {
		 logger.info(String.format(START_LOGGER_INFO+"findByEmail:UserDAOImpl.class"));
		
		 Session currentSession = sessionFacotry.getCurrentSession();
		 Query<User> query = currentSession.createQuery("from User where email=:email", User.class);
		 query.setParameter("email", email);
		 List<User> user = query.getResultList();
		
		 logger.info(String.format(END_LOGGER_INFO+"findByEmail:UserDAOImpl.class"));
		if (user.size() > 0)
			{
			return user.get(0);
			} 
			else 
			{
				return null;
			}
	 }

	//get the list of roles in order to map the correct role for the user who has registered

	@Override
	public List<UserRoles> findByRole(String role) {
			
		logger.info(String.format(START_LOGGER_INFO+"findByRoles:UserDAOImpl.class"));
		 
		Session currentSession = sessionFacotry.getCurrentSession();
		 Query<UserRoles> query = currentSession.createQuery("from UserRoles where role=:roleName", UserRoles.class);
		 query.setParameter("roleName", role);
		
		 logger.info(String.format(END_LOGGER_INFO+"findByRoles:UserDAOImpl.class"));
		
		 return query.getResultList();		 
	}


	//get the email id to check if the email used for registeration already exists
	@Override
	public List<User> findExistingEmail(String email) {

		logger.info(String.format(START_LOGGER_INFO+"findExistingEmail:UserDAOImpl.class"));

		Session currentSession = sessionFacotry.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where email=:email", User.class);
		query.setParameter("email", email);
		List<User> user = query.getResultList();
	
		logger.info(String.format(END_LOGGER_INFO+"findExistingEmail:UserDAOImpl.class"));

			if(user.size()>0)
				return user;
			else 
				return new ArrayList<>(); 
	}

}
