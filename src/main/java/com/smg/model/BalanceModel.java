package com.smg.model;

import java.sql.Timestamp;

public class BalanceModel {

	private long account_no; 
	private String preffered_name;
	private String balance;
	private Timestamp last_transaction_date;
	
	/**** CONSTRUCTOR ****/
	
	public BalanceModel(){}
	public BalanceModel(long account_no, String preffered_name, String balance, Timestamp last_transaction_date) {
		this.account_no = account_no;
		this.last_transaction_date = last_transaction_date;
		this.preffered_name = preffered_name;
		this.balance = balance;
	}
	public long getAccount_no() {
		return account_no;
	}
	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}
	public String getPreffered_name() {
		return preffered_name;
	}
	public void setPreffered_name(String preffered_name) {
		this.preffered_name = preffered_name;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public Timestamp getLast_transaction_date() {
		return last_transaction_date;
	}
	public void setLast_transaction_date(Timestamp last_transaction_date) {
		this.last_transaction_date = last_transaction_date;
	}
	
	
}
