package com.smg.service;

import java.math.BigDecimal;

import com.smg.model.AtmModel;
import com.smg.model.RegistrationModel;

public interface AtmService {

	public void validateLogin(AtmModel atm);
	public void validateRegistration(RegistrationModel register, BigDecimal balance);
	public void validateEnrollment(long primary_account_no, int primary_account_jai, long joint_account_no, int joint_account_jai);
	public void setAtmError(String error);
	public String getAtmError();
	public long getAtmVerificationCode();
	public String generateLink_Id();

}
