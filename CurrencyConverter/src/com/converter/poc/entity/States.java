package com.converter.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//This Entity Represent List of States in database with it's respective Country COde
//it is used only to fetch states from database and display to the user

@Entity
@Table(name="STATES")
public class States {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STATES_ID")
	private int stateId;
	
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	
	
	@Column(name="COUNTRY_CODE")
	private String country;
	
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	
	
}
