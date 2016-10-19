package com.smg.service;

import java.util.ArrayList;

import com.smg.model.CustomerModel;

public interface ProfileService {

	public ArrayList<String> displayConstant(String field_name);
	public CustomerModel displayCustomerProfile(String customer_id);
	public void updateCustomerRecord(CustomerModel customer);
	public void setProfileError(String error);
	public String getProfileError();
	
}
