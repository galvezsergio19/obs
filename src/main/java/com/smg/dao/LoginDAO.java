package com.smg.dao;

import com.smg.model.UserModel;

public interface LoginDAO {

	public String checkGetUsername(UserModel user);
	public void updateLastLogin(UserModel user);
	
}
