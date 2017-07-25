package com.converter.poc.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.converter.poc.dao.StateDAO;
import com.converter.poc.entity.Country;
import com.converter.poc.entity.States;

@Service
public class SatesServiceImpl implements StatesService {

	@Autowired
	private StateDAO stateDAO;
	
	//fetch the list of states for the respective country code
	@Override
	@Transactional
	public Set<States> findByCountryName(String countryName) {

		logger.info(String.format(START_LOGGER_INFO+"findByCountryName: SatesServiceImpl.class"));

		Set<States> stateSet = new HashSet<>();
		stateSet.addAll(stateDAO.findByCountryName(countryName));

		logger.info(String.format(END_LOGGER_INFO+"findByCountryName: SatesServiceImpl.class"));
		return stateSet;
	}

	//fetch a list of all the countries to display to the user
	@Override
	@Transactional
	public Map<String, String> findAllCountry() {
	
		logger.info(String.format(START_LOGGER_INFO+"findAllCountry: SatesServiceImpl.class"));
		
		Map<String , String> countryMap = new HashMap<>();
		List<Country> countryList = stateDAO.findAllCountry();
		for (Country country : countryList) {
			countryMap.put(country.getCountryCode(), country.getCountryName());
		}
	
		logger.info(String.format(END_LOGGER_INFO+"findAllCountry: SatesServiceImpl.class"));
		return countryMap;
	}

	
	
	
}
