package com.smg.dao;

import com.smg.model.VerificationModel;

public interface AccountDAO {

	public VerificationModel checkCustomerinfo(String customer_id);
	
}
