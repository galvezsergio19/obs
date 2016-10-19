package com.smg.service;

import java.util.ArrayList;

import com.smg.model.FundModel;

public interface FundService {

	public ArrayList<String> customersAccount(String customer_id);
	public long generateTAC(long account_no);
	public void validateFundTransfer(FundModel fund);
	public void setFundError(String error);
	public String getFundError();
}
