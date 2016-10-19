package com.smg.model;

import java.sql.Date;

public class VerificationModel {

	private String preferredName; 
	private long account_no; 
	private int jai;
	private String full_name;
	private Date birthday;
	private long atm_verification_code;
	
	/**** CONSTRUCTOR ****/
	
	public VerificationModel(){}
	public VerificationModel(String preferredName, long account_no, int jai, String full_name, Date birthday, long atm_verification_code) {
		this.preferredName = preferredName;
		this.account_no = account_no;
		this.jai = jai;
		this.full_name = full_name;
		this.birthday = birthday;
		this.atm_verification_code = atm_verification_code;
	}
	
	/**** SETTER ****/
	
	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	
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
	
	public void setAtm_verification_code(long atm_verification_code) {
		this.atm_verification_code = atm_verification_code;
	}
	
	/**** GETTER ****/
	
	public String getPreferredName() {
		return preferredName;
	}
	
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
	
	public long getAtm_verification_code() {
		return atm_verification_code;
	}
	
}
