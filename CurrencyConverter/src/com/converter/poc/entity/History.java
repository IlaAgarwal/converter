package com.converter.poc.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name="currency_history")
public class History {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CURRENCY_HISTORY_ID")
	
	private int historyId;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name="QUERY_TIMESTAMP")
	private Date queryDate = new Date();
	
	@Column(name="EXCHNAGE_RATE_TIMESTAMP")
	private Date timeStamp=new Date();
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@OneToMany (mappedBy="history",fetch=FetchType.EAGER)
	@OrderBy("currencyType")
	@Cascade({CascadeType.ALL})
	private Set<ExchangeRate> exchangeRateSet = new LinkedHashSet<ExchangeRate>();

	/**
	 * @return the historyId
	 */
	public int getHistoryId() {
		return historyId;
	}

	/**
	 * @param historyId the historyId to set
	 */
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}


	/**
	 * @return the queryDate
	 */
	public Date getQueryDate() {
		return queryDate;
	}

	/**
	 * @param queryDate the queryDate to set
	 */
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
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
	
	
	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the exchangeRates
	 */
	public Set<ExchangeRate> getExchangeRateSet() {
		return exchangeRateSet;
	}

	/**
	 * @param exchangeRates the exchangeRates to set
	 */
	public void setExchangeRateSet(Set<ExchangeRate> exchangeRateSet) {
		this.exchangeRateSet = exchangeRateSet;
	}

	public void addExchnageRate(ExchangeRate exchangeRate) {
		exchangeRate.setHistory(this);
		exchangeRateSet.add(exchangeRate);
		}

}
