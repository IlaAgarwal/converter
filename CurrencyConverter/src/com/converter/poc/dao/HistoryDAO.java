package com.converter.poc.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.converter.poc.entity.History;



public interface HistoryDAO {

	
	static final Logger logger = LoggerFactory.getLogger(HistoryDAO.class);	
	static final String START_LOGGER_INFO = "Start---->loggerInfo:";
	static final String END_LOGGER_INFO = "End---->loggerInfo:";
	
	//number of past queries to display to the user
	static final int MAX_ROWS = 10;
	
	public List<History> getHistory(int id);
	public void saveConversionHistory(History history, int id);
}
