package com.smg.model;

import java.sql.Date;

public class RegistrationModel {

	private long account_no; 
	private int jai;
	private String full_name;
	private Date birthday;
	
	/**** CONSTRUCTOR ****/
	
	public RegistrationModel(){}
	public RegistrationModel(long account_no, int jai, String full_name, Date birthday) {
		this.account_no = account_no;
		this.jai = jai;
		this.full_name = full_name;
		this.birthday = birthday;
	}
	
	/**** SETTER ****/
	
	public void setAccount_no(long account_no) {
		this.account_no = account_no;
	}
	
	public void setjai(int jai) {
		this.jai = jai;
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	/**** GETTER ****/
	
	public long getAccount_no() {
		return account_no;
	}
	
	public int getJai() {
		return jai;
	}
	
	public String getFull_name() {
		return full_name;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
}
