/**
 * 
 */
package com.converter.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.type.BigDecimalType;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

@Entity
@Table(name="exchange_rate")
public class ExchangeRate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EXCHANGE_RATE_ID")
	private int exchangeRateId;
	
	@Column(name="CURRENCY_TYPE")
	private String currencyType;
	
	@Column(name="EXCHANGE_RATE_AMOUNT")
	private Double exchangeAmount;
	
	@ManyToOne
	@JoinColumn(name="CURRENCY_HISTORY_ID")
	private History history;

	/**
	 * @return the exchangeRateId
	 */
	public int getExchangeRateId() {
		return exchangeRateId;
	}

	/**
	 * @param exchangeRateId the exchangeRateId to set
	 */
	public void setExchangeRateId(int exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}

	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * @return the exchangeAmount
	 */
	public Double getExchangeAmount() {
		return exchangeAmount;
	}

	/**
	 * @param exchangeAmount the exchangeAmount to set
	 */
	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	/**
	 * @return the history
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(History history) {
		this.history = history;
	}
}
