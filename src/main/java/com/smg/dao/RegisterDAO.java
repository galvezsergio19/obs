package com.smg.dao;

import java.sql.Date;

public interface RegisterDAO {
	
	public boolean checkAccountNoIfExist(long accountNumber, int jai);
	public boolean checkCustomerIdIfExist(String customer_Id);
	public boolean checkCustomerLinkIfExist(long accountNumber);
	public boolean checkExistingAccountStatus(long accountNumber);
	public boolean checkLinkIdIfExist(String link_Id);
	public void insertCustomerLink(String link_id, String customer_Id, long account_no);
	public void insertCustomer(String customer_Id, String full_name, Date birthday);
	public void InsertVerificationCode(String link_id, long atm_verification, long web_verification);

}
