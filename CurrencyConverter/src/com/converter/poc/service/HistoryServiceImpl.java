package com.converter.poc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.converter.poc.dao.HistoryDAO;
import com.converter.poc.entity.ExchangeRate;
import com.converter.poc.entity.History;
import com.converter.poc.entity.JsonObject;


@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryDAO historyDAO;
	
	@Override
	@Transactional
	public List<History> getUserHistory(int id) 
	{
	return	historyDAO.getHistory(id);
	}
	
	//save the data that is recently queried by the user
	@Override
	@Transactional
	public void saveConversionHistory(History history, int id) {

		logger.info(String.format(START_LOGGER_INFO+"saveConversionHistory:HistoryServiceImpl.class"));

		for(ExchangeRate exchageRate: history.getExchangeRateSet())
		{
			history.addExchnageRate(exchageRate);
		}
		historyDAO.saveConversionHistory(history, id);

		logger.info(String.format(END_LOGGER_INFO+"saveConversionHistory:HistoryServiceImpl.class"));
	}
	
	//SAve the Api JsonObject to History Entity
	public History saveJsonObjectToHistory(JsonObject jsonObject)
	{
      	logger.info(String.format(START_LOGGER_INFO + "saveJsonObjectToHistory:HistoryServiceImpl.class"));

		
		History history = new History();
        history.setQueryDate(jsonObject.getDate());
       	history.setExchangeRateSet(saveQuotesToExchangeRate(jsonObject));
  
       	logger.info(String.format(END_LOGGER_INFO + "saveJsonObjectToHistory:HistoryServiceImpl.class"));
		
       	return history;
		
		
	}
	
	//Create a list of currencies available
	public List<String> createListForCurrencyType()
	{
   		logger.info(String.format(START_LOGGER_INFO + "createListForCurrencyType:HistoryServiceImpl.class"));
   	 
		
		List<String> currencyTypeList = new ArrayList<>();
        currencyTypeList.add("USD");
        currencyTypeList.add("EUR");
        currencyTypeList.add("GBP");
        currencyTypeList.add("AED");
        currencyTypeList.add("INR");

        logger.info(String.format(END_LOGGER_INFO + "createListForCurrencyType:HistoryServiceImpl.class"));
        return currencyTypeList;
		}
	
	
	//called from the method saveJsonObjectToHistory- to save the exchange rate type and amount from a ap to Exchange Rate object
	public Set<ExchangeRate> saveQuotesToExchangeRate(JsonObject jsonObject)
	{
		logger.info(String.format(START_LOGGER_INFO + "saveQuotesToHistory:HistoryServiceImpl.class"));

		Set<ExchangeRate> exchangeRateSet = new HashSet<>();
		List<String> currencyTypeList = createListForCurrencyType();
		
		jsonObject.getQuotes().forEach((type,amount)->{
			if(currencyTypeList!=null && currencyTypeList.contains(type.substring(3)))
				{
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setCurrencyType(type.substring(3));
				exchangeRate.setExchangeAmount(amount);
				exchangeRateSet.add(exchangeRate);
				}
			});
		logger.info(String.format(END_LOGGER_INFO + "saveQuotesToHistory:HistoryServiceImpl.class"));
		return exchangeRateSet;
		}
	
	//call the APi using RestTemplate and save the data to JsonObject 
	public JsonObject callCurrencyLayerAPI(Date queryDate)
	{
		logger.info(String.format(START_LOGGER_INFO + "callCurrencyLayerAPI:HistoryServiceImpl.class"));

		RestTemplate restTemplate = new RestTemplate();
		String AccessKey = "e0c18714cf29308ce7cfdfae806901c5";
		
		String url = "http://apilayer.net/api/";
		
		if(queryDate!=null && !(DateUtils.isSameDay(queryDate, new Date())))
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(queryDate);
			url = url+"historical?access_key="+AccessKey+"& date=" + date ;
		}
		else
		{
		url = url+"live?access_key="+AccessKey+"&currencies=USD,EUR,GBP,INR,AED";
	}
		JsonObject jsonObject = restTemplate.getForObject(url, JsonObject.class);

		logger.info(String.format(END_LOGGER_INFO + "callCurrencyLayerAPI:HistoryServiceImpl.class"));
		return jsonObject;
	}
	
	
}
