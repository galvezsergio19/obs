package com.smg.model;

import java.sql.Timestamp;

public class TransactionModel {

	private long transaction_id; 
	private Timestamp transaction_date;
	private String transation_type;
	private String balance_before;
	private String balance_after;
	private long receiver_account_no;
	private long sender_account_no;
	private String shop_name;
	private String atm_ref_id;
	
	/**** CONSTRUCTOR ****/
	
	public TransactionModel(){}
	public TransactionModel(long transaction_id, Timestamp transaction_date, String transation_type, String balance_before, 
			String balance_after, long receiver_account_no, long sender_account_no, String shop_name, String atm_ref_id) {
		this.transaction_id = transaction_id;
		this.transaction_date = transaction_date;
		this.transation_type = transation_type;
		this.balance_before = balance_before;
		this.balance_after = balance_after;
		this.receiver_account_no = receiver_account_no;
		this.sender_account_no = sender_account_no;
		this.shop_name = shop_name;
		this.atm_ref_id = atm_ref_id;
	}

	/**** SETTER ****/
	
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public void setTransaction_date(Timestamp transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	public void setTransation_type(String transation_type) {
		this.transation_type = transation_type;
	}
	
	public void setBalance_before(String balance_before) {
		this.balance_before = balance_before;
	}

	public void setBalance_after(String balance_after) {
		this.balance_after = balance_after;
	}

	public void setReceiver_account_no(long receiver_account_no) {
		this.receiver_account_no = receiver_account_no;
	}

	public void setSender_account_no(long sender_account_no) {
		this.sender_account_no = sender_account_no;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public void setAtm_ref_id(String atm_ref_id) {
		this.atm_ref_id = atm_ref_id;
	}
	

	/**** GETTER ****/
	
	public long getTransaction_id() {
		return transaction_id;
	}
	
	public Timestamp getTransaction_date() {
		return transaction_date;
	}

	public String getTransation_type() {
		return transation_type;
	}
	
	public String getBalance_before() {
		return balance_before;
	}
	
	public String getBalance_after() {
		return balance_after;
	}
	
	public long getReceiver_account_no() {
		return receiver_account_no;
	}
	
	public long getSender_account_no() {
		return sender_account_no;
	}
	
	public String getShop_name() {
		return shop_name;
	}
	
	public String getAtm_ref_id() {
		return atm_ref_id;
	}

}
