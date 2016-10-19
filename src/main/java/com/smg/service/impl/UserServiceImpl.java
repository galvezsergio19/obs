package com.smg.service.impl;

import com.smg.dao.UserDAO;
import com.smg.dao.impl.UserDAOImpl;
import com.smg.model.UserModel;
import com.smg.service.UserService;
import com.smg.utility.RegexCheck;

public class UserServiceImpl implements UserService {

	private String error_Code = "";
	private RegexCheck regex = null;
	
	public void validateUser(UserModel user) {
		
		UserDAO userDao = new UserDAOImpl();
		regex = new RegexCheck();
		
		// Check validity of User Name
		if (!regex.isInputWhiteSpace(user.getUsername()) && 
			!regex.isInputSpecialCharacter(user.getUsername())) {
			// Returns 1 if userName already exists
			if (!userDao.checkExistingUserName(user)) {
				// Validate the Password
				if (regex.regexCheck(user.getPassword())&&regex.isInputQuote(user.getPassword())) {
					// Check if repeat password is same
					if (user.getPassword().equals(user.getRepeat_Password())) {
						userDao.insertUserAccount(user);
					} else {
						// Password does not match. Please check your password.
						setUserError("U03"); 
					}
				} else {
					// Password Error! Must contain upper case, lower case, digits and at least two special character except quotes.
					setUserError("U02"); 
				}
			} else {
				// User Account already exists. Please Enter a valid date.
				setUserError("U01"); 
			}
		} else {
			// User Name must not contain Whitespace and Special Characters.
			setUserError("U04"); 
		}
	}
	
	public void setUserError(String error) {
		this.error_Code = error;
	}
	
	public String getUserError() {
		return error_Code;
	}
	
}
