package com.smg.model;

import java.math.BigDecimal;

public class FundModel {

	private long account_no_from; 
	private long account_no_to;
	private BigDecimal amount;
	private long tac;
	
	/**** CONSTRUCTOR ****/
	
	public FundModel(){}
	public FundModel(long account_no_from, long account_no_to, BigDecimal amount, long tac) {
		this.account_no_from = account_no_from;
		this.account_no_to = account_no_to;
		this.amount = amount;
		this.tac = tac;
	}
	
	/**** SETTER ****/
	
	public void setAccount_no_from(long account_no_from) {
		this.account_no_from = account_no_from;
	}
	
	public void setAccount_no_to(long account_no_to) {
		this.account_no_to = account_no_to;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public void setTac(long tac) {
		this.tac = tac;
	}
	
	/**** GETTER ****/
	
	public long getAccount_no_from() {
		return account_no_from;
	}
	
	public long getAccount_no_to() {
		return account_no_to;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public long getTac() {
		return tac;
	}
	
}
