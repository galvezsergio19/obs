package com.smg.service.impl;

import java.util.ArrayList;

import com.smg.dao.ProfileDAO;
import com.smg.dao.impl.ProfileDAOImpl;
import com.smg.model.CustomerModel;
import com.smg.service.ProfileService;
import com.smg.utility.RegexCheck;

public class ProfileServiceImpl implements ProfileService {
	
	private ProfileDAO profileDao = new ProfileDAOImpl();
	private RegexCheck regex = new RegexCheck();
	private String error_Code;
	
	public ArrayList<String> displayConstant(String field_name) {
		ArrayList<String> field_value = new ArrayList<String>();
		field_value = profileDao.checkConstant(field_name);
		return field_value;
	}
	
	public CustomerModel displayCustomerProfile(String customer_id) {
		CustomerModel customer = new CustomerModel();
		customer = profileDao.checkCustomer(customer_id);
		return customer;
	}
	
	public void updateCustomerRecord(CustomerModel customer) {
		// Validations
		if (regex.isEmailValid(customer.getEmail())) {
			profileDao.updateCustomerRecord(customer);
		} else {
			setProfileError("P01");
		}
	}
	
	
	public void setProfileError(String error) {
		this.error_Code = error;
	}
	
	public String getProfileError() {
		return error_Code;
	}
	
	
}
