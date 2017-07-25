package com.converter.poc.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name="user")
public class User {
	
	public User() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int userId;
	

	@Column(name="FIRST_NAME")
	private String firstName;
	

	@Column(name="LAST_NAME")
	private String lastName;
	

	@Column(name="EMAIL")
	private String email;

	
	@Size(min=8 , message="the password should be atleast 8")
	@Column(name="PASSWORD")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	private int enabled = 1;
	
	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@OneToMany (mappedBy="user")
	@Cascade({CascadeType.ALL})
	private Set<History> currencyHistory = new HashSet<History>();
	
	@OneToOne(mappedBy="user")
	@Cascade({CascadeType.ALL})
	private UserAddress userAddress;
	

	 @ManyToMany
	 @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRoles>  role = new HashSet<UserRoles>();
	    
	

	/**
	 * @return the role
	 */
	public Set<UserRoles> getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Set<UserRoles> role) {
		this.role = role;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passsword
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param passsword the passsword to set
	 */
	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}

	/**
	 * @return the currencyHistory
	 */
	public Set<History> getCurrencyHistory() {
		return currencyHistory;
	}

	/**
	 * @param currencyHistory the currencyHistory to set
	 */
	public void setCurrencyHistory(Set<History> currencyHistory) {
		this.currencyHistory = currencyHistory;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the userAddress
	 */
	public UserAddress getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	public void addHistory(History history) {
		history.setUser(this);
		currencyHistory.add(history);
		}
	
	public void addUserRole(UserRoles userRole) {
		this.role.add(userRole);
		userRole.getUsers().add(this);
		}

}
