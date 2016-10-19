package com.smg.service;

public interface SmsService {

	public void displayTAC(long account_no);
	public void setSmsError(String error);
	public String getSmsError();
	public long getTAC();
	
}
