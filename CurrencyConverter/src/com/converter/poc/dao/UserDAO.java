package com.converter.poc.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.converter.poc.entity.User;
import com.converter.poc.entity.UserRoles;

public interface UserDAO {
	
	static final Logger logger = LoggerFactory.getLogger(UserDAO.class);	
	static final String START_LOGGER_INFO = "Start---->loggerInfo:";
	static final String END_LOGGER_INFO = "End---->loggerInfo:";
	
	void registerUser(User user);
	User findByEmail(String email);
	List<UserRoles>  findByRole(String role);
	List<User> findExistingEmail(String email);
	
}
