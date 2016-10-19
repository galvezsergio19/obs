package com.smg.service;

import com.smg.model.UserModel;

public interface UserService {

	public void validateUser(UserModel user);
	public void setUserError(String error);
	public String getUserError();
	
}
