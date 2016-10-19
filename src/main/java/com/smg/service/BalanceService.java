package com.smg.service;

import java.util.ArrayList;

import com.smg.model.BalanceModel;
import com.smg.model.TransactionModel;

public interface BalanceService {

	public ArrayList<BalanceModel> displayBalance(String customer_id);
	public ArrayList<TransactionModel> displayTransaction(String customer_id, long account_no);
	public void setBalanceError(String error);
	public String getBalanceError();

}
