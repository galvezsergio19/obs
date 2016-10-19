package com.smg.service.impl;

import java.util.ArrayList;

import com.smg.dao.BalanceDAO;
import com.smg.dao.impl.BalanceDAOImpl;
import com.smg.model.BalanceModel;
import com.smg.model.TransactionModel;
import com.smg.service.BalanceService;

public class BalanceServiceImpl implements BalanceService {
	
	private BalanceDAO balanceDao = null;
	private String error_Code = "";
	ArrayList<String> balance = null;
	
	public ArrayList<BalanceModel> displayBalance(String customer_id) {
		
		BalanceModel balance = new BalanceModel();
		ArrayList<BalanceModel> accountsBalance = new ArrayList<BalanceModel>();
		ArrayList<String> accounts = new ArrayList<String>();
		balanceDao = new BalanceDAOImpl();
		
		// Load all accounts
		
		accounts = balanceDao.checkAllAccounts(customer_id);
		for (String account:accounts) {
			balance = balanceDao.checkBalance(customer_id, Long.parseLong(account));
			accountsBalance.add(balance);
		}
		
		return accountsBalance;
	}
	
	public ArrayList<TransactionModel> displayTransaction(String customer_id, long account_no) {
		
		ArrayList<TransactionModel> accountsTransaction = new ArrayList<TransactionModel>();
		balanceDao = new BalanceDAOImpl();
		
		// Load all accounts
		accountsTransaction = balanceDao.checkTransaction(customer_id, account_no);
		return accountsTransaction;
	}
	
	public void setBalanceError(String error) {
		this.error_Code = error;
	}
	
	public String getBalanceError() {
		return error_Code;
	}
	
	
}
