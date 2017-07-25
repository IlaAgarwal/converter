package com.converter.poc.service;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.converter.poc.entity.States;

public interface StatesService {

	static final Logger logger = LoggerFactory.getLogger(StatesService.class);	
	static final String START_LOGGER_INFO = "Start---->loggerInfo:";
	static final String END_LOGGER_INFO = "End---->loggerInfo:";	
	
	Set<States> findByCountryName(String countryName);

	Map<String, String> findAllCountry();
}
