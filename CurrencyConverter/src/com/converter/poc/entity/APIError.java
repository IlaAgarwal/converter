package com.converter.poc.entity;


//It is a plain POJO, used to display error to the users in case API doesnot succeed

public class APIError {
	
	private int Code;
	private String info;
	/**
	 * @return the code
	 */
	public int getCode() {
		return Code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		Code = code;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}
