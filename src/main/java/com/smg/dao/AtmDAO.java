package com.smg.dao;

import java.math.BigDecimal;

import com.smg.model.AtmModel;

public interface AtmDAO {

	public boolean checkAccountNo(AtmModel atm);
	public long checkAtmVerification(AtmModel atm);
	public void insertAccount(long account_no, int jai, BigDecimal balance);
	public String checkCustomerLinkforAccount(long accountNumber);
	
}
