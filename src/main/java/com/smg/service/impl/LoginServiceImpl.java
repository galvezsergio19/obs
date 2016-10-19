package com.smg.service.impl;

import com.smg.dao.LoginDAO;
import com.smg.dao.impl.LoginDAOImpl;
import com.smg.model.UserModel;
import com.smg.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private LoginDAO loginDao = new LoginDAOImpl();
	private String error_Code = "";
	private String customer_id = "";
	
	public void validateLogin(UserModel user) {
		
		// Customer ID existing
		setCustomer_id(loginDao.checkGetUsername(user));
		user.setCustomer_Id(getCustomer_id());
		if (!customer_id.equals("")) {
			loginDao.updateLastLogin(user);
		} else {
			// Fail! Please double check your Login credentials.
			setLoginError("L01");
		}
		
	}
	
	public void setLoginError(String error) {
		this.error_Code = error;
	}
	
	public String getLoginError() {
		return error_Code;
	}
	
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getCustomer_id() {
		return customer_id;
	}
	
	
}
