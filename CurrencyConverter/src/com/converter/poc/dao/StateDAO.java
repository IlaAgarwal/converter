package com.converter.poc.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.converter.poc.entity.Country;
import com.converter.poc.entity.States;

public interface StateDAO {

	
	static final Logger logger = LoggerFactory.getLogger(StateDAO.class);	
	static final String START_LOGGER_INFO = "Start---->loggerInfo:";
	static final String END_LOGGER_INFO = "End---->loggerInfo:";
	
	List<States> findByCountryName(String countryCode);

	List<Country> findAllCountry();
	
	String getCountryName(String countryCode);
}
