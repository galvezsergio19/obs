package com.smg.dao;

import java.util.ArrayList;

import com.smg.model.CustomerModel;

public interface ProfileDAO {

	public ArrayList<String> checkConstant(String field_name);
	public CustomerModel checkCustomer(String customer_id);
	public void updateCustomerRecord(CustomerModel customer);
	
}
