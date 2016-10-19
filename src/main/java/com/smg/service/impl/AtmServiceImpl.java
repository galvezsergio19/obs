package com.smg.service.impl;

import java.math.BigDecimal;

import com.smg.dao.AtmDAO;
import com.smg.dao.RegisterDAO;
import com.smg.dao.impl.AtmDAOImpl;
import com.smg.dao.impl.RegisterDAOImpl;
import com.smg.model.AtmModel;
import com.smg.model.RegistrationModel;
import com.smg.service.AtmService;
import com.smg.utility.Rand;

public class AtmServiceImpl implements AtmService {
	
	private AtmDAO atmDao = null;
	private RegisterDAO registerDao = null;
	private String error_Code = "";
	private long atm_verification_code = 0;
	private Rand random = new Rand();
	
	public void validateLogin(AtmModel atm) {
		
		atmDao = new AtmDAOImpl();
		
		// Checks if Account No existing
		/*if (!atmDao.checkAccountNo(atm)) {*/
			// Checks if verification Matched
			if (atmDao.checkAtmVerification(atm)!=0) {
				// Successful / Display Verification Code
				atm_verification_code = atmDao.checkAtmVerification(atm); 
			} else {
				// Verification does not Match.
				setAtmError("A02"); 
			}
		/*} else {
			// Account Number does not exists.
			setAtmError("A01");
		}*/
		
	}
	
	public void validateRegistration(RegistrationModel register, BigDecimal balance) {
		
		// Re-use the existing checking of Account no and jai
		registerDao = new RegisterDAOImpl();
		atmDao = new AtmDAOImpl();
		
		// Checks if Account No if not existing
		if (!registerDao.checkAccountNoIfExist(register.getAccount_no(), register.getJai())) {
			// Checks if valid balance, must be 500 or above
			if (balance.compareTo(new BigDecimal("500"))!=-1) {
				// Successful / Insert into account table
				atmDao.insertAccount(register.getAccount_no(), register.getJai(), balance);
			} else {
				// Initial Balance not valid for processing.
				setAtmError("A03"); 
			}
		} else {
			// Account Number exists. Please check your credentials.
			setAtmError("A04");
		}
		
	}
	
	
	public void validateEnrollment(long primary_account_no, int primary_account_jai, long joint_account_no, int joint_account_jai) {
		
		// Re-use the existing checking of Account no and jai
		registerDao = new RegisterDAOImpl();
		atmDao = new AtmDAOImpl();
		
		// Check if Primary Card No Exists
		if (registerDao.checkAccountNoIfExist(primary_account_no, primary_account_jai)) {
			// Check if Joint Account No Exists
			if (registerDao.checkAccountNoIfExist(joint_account_no, joint_account_jai)) {
				// Get the customer_id of the primary card
				String customer_id = atmDao.checkCustomerLinkforAccount(primary_account_no);
				// Check if Card have connections already
				if (atmDao.checkCustomerLinkforAccount(joint_account_no).equals("") || 
					atmDao.checkCustomerLinkforAccount(joint_account_no).equals(null)) {
					
					// Insert Account Record with Customer ID
					String link_id = generateLink_Id();
					// Insert to Customer Link Table
					registerDao.insertCustomerLink(link_id, customer_id, joint_account_no);
					// Generate Verification Code
					long atm_verification = random.genRandomDigits(10);
					long web_verification = random.genRandomDigits(10);
					
					while (atm_verification==web_verification) {
						atm_verification = random.genRandomDigits(10);
						web_verification = random.genRandomDigits(10);
					}	
					
					registerDao.InsertVerificationCode(link_id, atm_verification, web_verification);
					atm_verification_code = web_verification;
					
				} else {
					setAtmError("A05");
				}
			} else {
				// Joint Account Number does not exists
				setAtmError("A06");
			}
		} else {
			// Account Number does not exists
			setAtmError("A01");
		}
		
	}
	
	
	public void setAtmError(String error) {
		this.error_Code = error;
	}
	
	public String getAtmError() {
		return error_Code;
	}
	
	public long getAtmVerificationCode() {
		return atm_verification_code;
	}
	
	public String generateLink_Id() {
		
		// Generates until Link ID not existing
		String Link_Id = "L" + String.valueOf(random.genRandomDigits(7));
		while (registerDao.checkLinkIdIfExist(Link_Id)) {
			Link_Id = "C" + String.valueOf(random.genRandomDigits(7));
		}
		return Link_Id;
	}
	
	
}
