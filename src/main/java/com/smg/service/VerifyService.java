package com.smg.service;

import com.smg.model.VerificationModel;

public interface VerifyService {

	public void validateVerification(VerificationModel verify);
	public void setVerificationError(String error);
	public String getVerificationError();
	public void setCustomer_Id(String customer_id);
	public String getCustomer_Id();
	
}
