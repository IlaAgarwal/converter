package com.converter.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//This Entity Represent a Country database with a list of countries.
//it is used only to fetch countries from database and display to the user

@Entity
@Table(name="countries")
public class Country {
	

	
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	@Id
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	

}
