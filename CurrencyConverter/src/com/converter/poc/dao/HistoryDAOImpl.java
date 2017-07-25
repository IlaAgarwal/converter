package com.converter.poc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.converter.poc.entity.History;
import com.converter.poc.entity.User;

@Repository
public class HistoryDAOImpl implements HistoryDAO {

	@Autowired
	private SessionFactory sessionFacotry;
	
	
	//Fetch the past 10 records user has queried
	@Override
	public List<History> getHistory(int id) {
	
		logger.info(String.format(START_LOGGER_INFO+"getHistory:HistoryDAOImpl.class"));

		
		Session currentSession = sessionFacotry.getCurrentSession();
		
		Query<History> query = currentSession.createQuery("from History where user.userId=:id order by timeStamp desc", History.class);
		query.setParameter("id", id);
		query.setMaxResults(MAX_ROWS);
		
		List<History> userHistory = query.getResultList();
		
		logger.info(String.format(END_LOGGER_INFO+"getHistory:HistoryDAOImpl.class"));

		return userHistory;
	}

	//Save theh USer Queried Record in the database
	@Override
	public void saveConversionHistory(History history, int id) {
		
		logger.info(String.format(START_LOGGER_INFO+"saveConversionHistory:HistoryDAOImpl.class"));

		
		Session currentSession = sessionFacotry.getCurrentSession();
		User user = currentSession.load(User.class, id);
		user.addHistory(history);
		System.out.println("EXCHANGERATE SIZE:  "+history.getExchangeRateSet().size());
		currentSession.save(history); 
		
		logger.info(String.format(END_LOGGER_INFO+"saveConversionHistory:HistoryDAOImpl.class"));

	}
	
	

}
