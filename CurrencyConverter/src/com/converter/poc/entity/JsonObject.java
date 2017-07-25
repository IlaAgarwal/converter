package com.converter.poc.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

public class JsonObject {
	
	
	private String success;
	private APIError error;
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * @return the error
	 */
	public APIError getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(APIError error) {
		this.error = error;
	}
	private Map<String, Double> quotes;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date date =new Date();
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the quotes
	 */
	public Map<String, Double> getQuotes() {
		return quotes;
	}
	/**
	 * @param quotes the quotes to set
	 */
	public void setQuotes(Map<String, Double> quotes) {
		this.quotes = quotes;
	}
	
}
