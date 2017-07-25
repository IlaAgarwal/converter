package com.converter.poc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.converter.poc.entity.User;

public interface UserService {

	static final Logger logger = LoggerFactory.getLogger(UserService.class);	
	static final String START_LOGGER_INFO = "Start---->loggerInfo:";
	static final String END_LOGGER_INFO = "End---->loggerInfo:";	
	
	boolean registerUser(User user);
	 User findByEmail(String email);
	boolean findExistingEmail(String email);
	
}
