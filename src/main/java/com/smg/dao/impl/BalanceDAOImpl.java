package com.smg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.smg.dao.BalanceDAO;
import com.smg.model.BalanceModel;
import com.smg.model.TransactionModel;
import com.smg.utility.Property;
import com.smg.utility.RegexCheck;

public class BalanceDAOImpl implements BalanceDAO {
	
	RegexCheck regex = null;
	
	public ArrayList<String> checkAllAccounts(String customer_id) {
		
		ArrayList<String> accounts = new ArrayList<String>();
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_distinct_account"));
			statement.setString(1, customer_id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				accounts.add(String.valueOf(rs.getLong("account_no")));
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return accounts;
			
	}

	public BalanceModel checkBalance(String customer_id, long account_no) {
		
		BalanceModel balance = new BalanceModel();
		regex = new RegexCheck();
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_balance_per_customer"));
			statement.setString(1, customer_id);
			statement.setLong(2, account_no);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				balance.setAccount_no(rs.getLong("account_no"));
				balance.setPreffered_name(rs.getString("preffered_name"));
				balance.setBalance(regex.convertBigDecimalString(rs.getBigDecimal("balance")));
				balance.setLast_transaction_date(rs.getTimestamp("transaction_date"));
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return balance;
			
	}
	
	public ArrayList<TransactionModel> checkTransaction(String customer_id, long account_no) {
		
		ArrayList<TransactionModel> transactionRecords = new ArrayList<TransactionModel>();
		regex = new RegexCheck();
		
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_transaction_per_customer"));
			statement.setString(1, customer_id);
			statement.setLong(2, account_no);
			//statement.setString(3, "S");
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				TransactionModel transact = new TransactionModel();
				transact.setTransaction_date(rs.getTimestamp("transaction_date"));
				transact.setTransation_type(rs.getString("transation_type"));
				BigDecimal balance_before = rs.getBigDecimal("balance_before");
				if (balance_before.compareTo(new BigDecimal("0"))!=0) {
					transact.setBalance_before(regex.convertBigDecimalString(balance_before));
				}
				BigDecimal balance_after = rs.getBigDecimal("balance_after");
				if (balance_before.compareTo(new BigDecimal("0"))!=0) {
					transact.setBalance_after(regex.convertBigDecimalString(balance_after));
				}
				transact.setReceiver_account_no(rs.getLong("receiver_account_no"));
				transact.setSender_account_no(rs.getLong("sender_account_no"));
				transact.setShop_name(rs.getString("shop_name"));
				transact.setAtm_ref_id(rs.getString("atm_ref_id"));
				transact.setTransaction_id((rs.getLong("transaction_id")));
				transactionRecords.add(transact);
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return transactionRecords;
			
	}
	
	
	
}
