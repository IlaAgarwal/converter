package com.converter.poc.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.converter.poc.dao.StateDAO;
import com.converter.poc.dao.UserDAO;
import com.converter.poc.entity.User;
import com.converter.poc.entity.UserRoles;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private StateDAO stateDAO;
	
	
	//Check if the user already exists.If not, hash the password and register the user else return with false value and display the error message
	@Override
	@Transactional
	public boolean registerUser(User user) {
		logger.info(String.format(START_LOGGER_INFO+"registerUser: UserServiceImpl class"));
		
		if(findExistingEmail(user.getEmail())==true)
		{	
			return false;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String countryName =stateDAO.getCountryName(user.getUserAddress().getCountryCode());
		user.getUserAddress().setCountryName(countryName);
		user.setRole(new HashSet<UserRoles>(userDao.findByRole("ROLE_USER")));
		user.getUserAddress().setUser(user);
		userDao.registerUser(user);

		logger.info(String.format(END_LOGGER_INFO+"registerUser: UserServiceImpl class"));
		return true;
	}


	//get the  user based on  email(username)
	@Override
	@Transactional
	public User findByEmail(String email) {

		logger.info(String.format(START_LOGGER_INFO+"findByEmail: UserServiceImpl.java") );
		
		return userDao.findByEmail(email);
		
	}


	//called by the method-registerUser() to check if the user already exists 
	@Override
	@Transactional
	public boolean findExistingEmail(String email) {

		logger.info(String.format(START_LOGGER_INFO+"findExistingEmail: UserServiceImpl.java") );
		
		List<User> user = userDao.findExistingEmail(email);
	
		logger.info(String.format(END_LOGGER_INFO+"findExistingEmail: UserServiceImpl.java") );
		if(user.size()>0)
			return true;
		else 
			return false;
	}


}
	

