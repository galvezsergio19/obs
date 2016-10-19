package com.smg.service;

import com.smg.model.RegistrationModel;

public interface RegisterService {
	
	public void validateRegistration(RegistrationModel register);
	public void generateCustomerRecord(RegistrationModel register);
	public String generateCustomer_Id();
	public String generateLink_Id();
	public void setRegistrationError(String error);
	public String getRegistrationError();
	public void setVerificationCode(long verification_code);
	public long getVerification_code();
	
}
