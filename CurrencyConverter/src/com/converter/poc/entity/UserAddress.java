
package com.converter.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;


@Entity
@Table(name="user_address")
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ADDRESS_ID")
	private int addressId;
	
	@Column(name="CITY")
	private String city;

	@Size(max=5, min=5,message="Enter 5 digit code")
	@Column(name="ZIPCODE")
	private String zipCode;
	
	@Column(name="STREET_LINE1")
	private String streetLine1;
	
	@Column(name="STREET_LINE2")
	private String streetLine2;
	
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	@Transient
	private String countryCode;
	
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

	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;

	
	/**
	 * @return the addressId
	 */
	public int getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the streetLine1
	 */
	public String getStreetLine1() {
		return streetLine1;
	}

	/**
	 * @param streetLine1 the streetLine1 to set
	 */
	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}

	/**
	 * @return the streetLine2
	 */
	public String getStreetLine2() {
		return streetLine2;
	}

	/**
	 * @param streetLine2 the streetLine2 to set
	 */
	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}

	

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
