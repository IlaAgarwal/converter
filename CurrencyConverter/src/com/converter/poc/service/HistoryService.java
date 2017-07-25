package com.converter.poc.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.converter.poc.dao.UserDAO;
import com.converter.poc.entity.History;
import com.converter.poc.entity.JsonObject;

public interface HistoryService {

	static final Logger logger = LoggerFactory.getLogger(HistoryService.class);	
	static final String START_LOGGER_INFO = "Start---->loggerInfo:";
	static final String END_LOGGER_INFO = "End---->loggerInfo:";	
	
	
	List<History> getUserHistory(int id);
	void saveConversionHistory(History history,  int id);
	JsonObject callCurrencyLayerAPI(Date date);
	History saveJsonObjectToHistory(JsonObject jsonObject);
}
