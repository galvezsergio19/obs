package com.smg.service.impl;

import java.sql.Timestamp;

import com.smg.dao.VerifyDAO;
import com.smg.dao.impl.VerifyDAOImpl;
import com.smg.model.VerificationModel;
import com.smg.service.VerifyService;

public class VerifyServiceImpl implements VerifyService {

	private String error_Code = "";
	private String customer_id = "";
	
	public void validateVerification(VerificationModel verify) {
		
		VerifyDAO verifyDao = new VerifyDAOImpl();
		// Check verify table for the verification code of ATM
		if (verifyDao.retireveVerification_No(verify)!=0) {
			
			Timestamp todayTimeStamp = new Timestamp(System.currentTimeMillis());
			Timestamp regTimeStamp = verifyDao.retireveReg_date(verify);   
			long diffHours = (regTimeStamp.getTime()-todayTimeStamp.getTime()) / (60 * 60 * 1000);
			
			// check if registration not elapsed 24 hours
			if (diffHours<24) {  
			// check if verification code match
				if (verifyDao.retireveVerification_No(verify)==verify.getAtm_verification_code()) {
					// check status if not yet verified
					if (!verifyDao.checkVerificationStatus(verify)) {
						verifyDao.updatePrefferedName(verify);
						verifyDao.updateVerificationStatus(verify);
						setCustomer_Id(verifyDao.getCustomer_Id(verify));
					} else {
						setVerificationError("V04"); // Account already verified. Proceed to login.
					}
				}  else {
					setVerificationError("V03"); // Verification Code does not match. Please check your verification code.
				}
			} else {
				setVerificationError("V02"); // Verification Code has expired.
			}
		} else {
			setVerificationError("V01"); // Fail! Please double check your credentials.
		}
		
	}
	
	public void setVerificationError(String error) {
		this.error_Code = error;
	}
	
	public String getVerificationError() {
		return error_Code;
	}
	
	public void setCustomer_Id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getCustomer_Id() {
		return customer_id;
	}
	
}
