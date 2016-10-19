package com.smg.service;

import com.smg.model.UserModel;

public interface LoginService {

	public void validateLogin(UserModel user);
	public void setLoginError(String error);
	public String getLoginError();
	public void setCustomer_id(String customer_id);
	public String getCustomer_id();
	
}
