package com.smg.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.smg.model.FundModel;
import com.smg.model.TransactionModel;

public interface FundDAO {

	public ArrayList<String> checkAccountNumberbyCustomer(String customer_id);
	public void InsertTAC(long account_no, long tac);
	public boolean checkAccountNoIfExist(long accountNumber, long tac);
	public BigDecimal checkCustomerBalance(long accountNumber);
	public void updateTransfer(FundModel fund);
	public void updateBalance(BigDecimal updatedbalance, long account_no);
	public void insertTransaction(TransactionModel transact);
	
}
