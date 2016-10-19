package com.smg.service;

import com.smg.model.VerificationModel;

public interface AccountService {

	public VerificationModel getCustomerFullNameAndBirthday(String customer_id);

}
