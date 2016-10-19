package com.smg.dao;

import com.smg.model.UserModel;

public interface UserDAO {

	public boolean checkExistingUserName(UserModel user);
	public void insertUserAccount(UserModel user);
	
}
