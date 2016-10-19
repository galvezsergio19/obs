package com.smg.model;

public class AtmModel {

	private long account_no; 
	private long web_verify;
	
	/**** CONSTRUCTOR ****/
	
	public AtmModel(){}
	public AtmModel(long account_no, long web_verify) {
		this.account_no = account_no;
		this.web_verify = web_verify;
	}
	
	/**** SETTER ****/
	
	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}
	
	public void setWeb_verify(long web_verify) {
		this.web_verify = web_verify;
	}
	
	/**** GETTER ****/
	
	public long getAccount_no() {
		return account_no;
	}
	
	public long getWeb_verify() {
		return web_verify;
	}
	
}
