package com.converter.poc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.converter.poc.entity.Country;
import com.converter.poc.entity.States;

@Repository
public class StateDAOImpl implements StateDAO {

	@Autowired
	private SessionFactory sessionFacotry;
	
	//Get the respective State list from database base on the country code
	@Override
	public List<States> findByCountryName(String countryCode) {
		
		logger.info(String.format(START_LOGGER_INFO+"findByCountryName:StateDAOImpl.class"));

		Session currentSession = sessionFacotry.getCurrentSession();
		Query<States> query = currentSession.createQuery("from States s where country=:countryCode", States.class);
		query.setParameter("countryCode", countryCode);
		List<States> states = query.getResultList();
		
		logger.info(String.format(END_LOGGER_INFO+"findByCountryName:StateDAOImpl.class"));
		return states;
	}

	//Get a List of all teh countries from database-used to display drop down
	@Override
	public List<Country> findAllCountry() {
		logger.info(String.format(START_LOGGER_INFO+"findAllCountry:StateDAOImpl.class"));

		Session currentSession = sessionFacotry.getCurrentSession();
		Query<Country> query = currentSession.createQuery("from Country",Country.class); 
		
		logger.info(String.format(END_LOGGER_INFO+"findAllCountry:StateDAOImpl.class"));

		return query.list();
	}
	
	//get the country name from database for the respective country code

	@Override
	public String getCountryName(String countryCode) {
		
		logger.info(String.format(START_LOGGER_INFO+"getCountryName:StateDAOImpl.class"));
		
		Session currentSession = sessionFacotry.getCurrentSession();
		Country query = (Country) currentSession.createQuery("from Country where countryCode=:countryCode").setParameter("countryCode", countryCode).uniqueResult();
		String CountryName = query.getCountryName();
		
		logger.info(String.format(END_LOGGER_INFO+"getCountryName:StateDAOImpl.class"));

		return CountryName;
	}

	
}
