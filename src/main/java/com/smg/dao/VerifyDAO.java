package com.smg.dao;

import java.sql.Timestamp;

import com.smg.model.VerificationModel;

public interface VerifyDAO {

	public long retireveVerification_No(VerificationModel verify);
	public Timestamp retireveReg_date(VerificationModel verify);
	public void updateVerificationStatus(VerificationModel verify);
	public boolean checkVerificationStatus(VerificationModel verify);
	public void updatePrefferedName(VerificationModel verify);
	public String getCustomer_Id(VerificationModel verify);
	
}
