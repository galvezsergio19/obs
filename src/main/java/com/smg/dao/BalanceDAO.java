package com.smg.dao;

import java.util.ArrayList;

import com.smg.model.BalanceModel;
import com.smg.model.TransactionModel;

public interface BalanceDAO {

	public ArrayList<String> checkAllAccounts(String customer_id);
	public BalanceModel checkBalance(String customer_id, long account_no);
	public ArrayList<TransactionModel> checkTransaction(String customer_id, long account_no);
}
