package com.smg.service.impl;

import com.smg.dao.AccountDAO;
import com.smg.dao.impl.AccountDAOImpl;
import com.smg.model.VerificationModel;
import com.smg.service.AccountService;

public class AccountServiceImpl implements AccountService {
	
	public VerificationModel getCustomerFullNameAndBirthday(String customer_id) {
		VerificationModel verify = new VerificationModel();
		AccountDAO accountDao = new AccountDAOImpl();
		verify = accountDao.checkCustomerinfo(customer_id);
		return verify;
	}
	
	
	
}
