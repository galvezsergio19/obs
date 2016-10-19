package com.smg.service.impl;

import com.smg.dao.AtmDAO;
import com.smg.dao.SmsDAO;
import com.smg.dao.impl.AtmDAOImpl;
import com.smg.dao.impl.SmsDAOImpl;
import com.smg.model.AtmModel;
import com.smg.service.SmsService;

public class SmsServiceImpl implements SmsService {
	
	private AtmDAO atmDao = new AtmDAOImpl();
	private SmsDAO smsDao = new SmsDAOImpl();
	private String error_Code = "";
	private long tac = 0;
	
	public void displayTAC(long account_no) {
		
		AtmModel atm = new AtmModel();
		atm.setAccount_no(account_no);
		
		// Checks if Account No existing
		if (atmDao.checkAccountNo(atm)) {
			// Get TAC
			if (smsDao.checkTac(atm)!=0) {
				tac = smsDao.checkTac(atm);
			} else {
				// No TAC Found for the Account Number.
				setSmsError("S01");
			}
		} else {
			// Account Number does not exists.
			setSmsError("A01");
		}
	}
	
	public void setSmsError(String error) {
		this.error_Code = error;
	}
	
	public String getSmsError() {
		return error_Code;
	}
	
	public long getTAC() {
		return tac;
	}
	
	
}
