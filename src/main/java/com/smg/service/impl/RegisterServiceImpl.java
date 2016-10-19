package com.smg.service.impl;

import java.sql.Timestamp;

import com.smg.dao.RegisterDAO;
import com.smg.dao.VerifyDAO;
import com.smg.dao.impl.RegisterDAOImpl;
import com.smg.dao.impl.VerifyDAOImpl;
import com.smg.model.RegistrationModel;
import com.smg.model.VerificationModel;
import com.smg.service.RegisterService;
import com.smg.utility.Rand;

public class RegisterServiceImpl implements RegisterService {

	private RegisterDAO registerDao = new RegisterDAOImpl();
	private VerifyDAO verifyDao = new VerifyDAOImpl();
	private Rand rand = new Rand();
	private String error_Code = "";
	private long verification_code;
	
	public void validateRegistration(RegistrationModel register) {
		
		// Checks if ATM Account Number exists
		if (registerDao.checkAccountNoIfExist(register.getAccount_no(), register.getJai())) {
			// Checks if account if not Existing and not Verified 
			if (!registerDao.checkCustomerLinkIfExist(register.getAccount_no())) {
					generateCustomerRecord(register);
					
			} else {
				
				// Account already registered.
				VerificationModel verify = new VerificationModel();
				verify.setAccount_no(register.getAccount_no());
				
				Timestamp todayTimeStamp = new Timestamp(System.currentTimeMillis());
				Timestamp regTimeStamp = verifyDao.retireveReg_date(verify); 
				long diffHours = (regTimeStamp.getTime()-todayTimeStamp.getTime()) / (60 * 60 * 1000);
				
				if (diffHours>24) {
					generateCustomerRecord(register);
				} else {
					// Account already registered. Please verify your registration
					setRegistrationError("R02");  
				}
			}
		} else {
			// Customer not allowed to access online service of OBS!
			setRegistrationError("R01");  
		}
	}
	
	public void generateCustomerRecord(RegistrationModel register) {
		
		// Generate Customer ID
		String customer_Id = generateCustomer_Id();
		String link_id = generateLink_Id();
		// Insert to Customer Table
		registerDao.insertCustomer(customer_Id, register.getFull_name(), register.getBirthday());
		// Insert to Customer Link Table
		registerDao.insertCustomerLink(link_id, customer_Id, register.getAccount_no());
		// Generate Verification Code
		long atm_verification = rand.genRandomDigits(10);
		long web_verification = rand.genRandomDigits(10);
		
		while (atm_verification==web_verification) {
			atm_verification = rand.genRandomDigits(10);
			web_verification = rand.genRandomDigits(10);
		}	
		registerDao.InsertVerificationCode(link_id, atm_verification, web_verification);
		setVerificationCode(atm_verification);
		
	}
	
	public String generateCustomer_Id() {
		
		// Generates until Customer ID not existing
		String customer_Id = "C" + String.valueOf(rand.genRandomDigits(7));
		while (registerDao.checkCustomerIdIfExist(customer_Id)) {
			customer_Id = "C" + String.valueOf(rand.genRandomDigits(7));
		}
		return customer_Id;
	}
	
	public String generateLink_Id() {
		
		// Generates until Link ID not existing
		String Link_Id = "L" + String.valueOf(rand.genRandomDigits(7));
		while (registerDao.checkLinkIdIfExist(Link_Id)) {
			Link_Id = "C" + String.valueOf(rand.genRandomDigits(7));
		}
		return Link_Id;
	}
	
	public void setRegistrationError(String error) {
		this.error_Code = error;
	}
	
	public String getRegistrationError() {
		return error_Code;
	}

	public void setVerificationCode(long verification_code) {
		this.verification_code = verification_code;
	}
	
	public long getVerification_code() {
		return verification_code;
	}
	
	
}
