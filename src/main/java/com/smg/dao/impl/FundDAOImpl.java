package com.smg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.smg.dao.FundDAO;
import com.smg.model.FundModel;
import com.smg.model.TransactionModel;
import com.smg.utility.Property;

public class FundDAOImpl implements FundDAO {

	public ArrayList<String> checkAccountNumberbyCustomer(String customer_id) {
		
		ArrayList<String> account = new ArrayList<String>();
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_account_of_customer"));
			statement.setString(1, customer_id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				account.add(String.valueOf(rs.getLong("account_no")));
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return account;
	}
	
	public void InsertTAC(long account_no, long tac) {
		
		try {
			
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_tac"));
			statement.setLong(1, account_no);
			statement.setLong(2, tac);
			statement.setString(3, "N");
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	public boolean checkAccountNoIfExist(long accountNumber, long tac) {
		
		boolean isExists = false;
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_tac"));
			statement.setLong(1, accountNumber);
			statement.setLong(2, tac);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				isExists = true;
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isExists;
	}
	
	public void updateTransfer(long account_no_to, BigDecimal amount, String transfer_status) {
		
		try {
			
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_tac"));
			statement.setLong(1, account_no_to);
			statement.setBigDecimal(2, amount);
			statement.setString(3, "N");
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	} 
	
	
	public BigDecimal checkCustomerBalance(long accountNumber) {

		BigDecimal customerBalance = new BigDecimal("0");
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_balance"));
			statement.setLong(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				customerBalance = rs.getBigDecimal("balance");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return customerBalance;
	}
	
	
	public void updateTransfer(FundModel fund) {
		
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("update_transfer"));
			statement.setLong(1, fund.getAccount_no_to());
			statement.setString(2, "Y");
			statement.setBigDecimal(3, fund.getAmount());
			statement.setLong(4, fund.getAccount_no_from());
			statement.setLong(5, fund.getTac());
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			
	}
	
	
	public void updateBalance(BigDecimal updatedbalance, long account_no) {
		
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("update_balance"));
			statement.setBigDecimal(1, updatedbalance);
			statement.setLong(2, account_no);
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			
	}
	
	public void insertTransaction(TransactionModel transact) {
		
		try {
			
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_transaction"));
			statement.setLong(1, transact.getTransaction_id());
			statement.setString(2, transact.getTransation_type());
			statement.setBigDecimal(3, new BigDecimal(transact.getBalance_before()));
			statement.setBigDecimal(4, new BigDecimal(transact.getBalance_after()));
			statement.setLong(5, transact.getReceiver_account_no());
			statement.setLong(6, transact.getSender_account_no());
			statement.setString(7, transact.getShop_name());
			statement.setString(8, transact.getAtm_ref_id());
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	} 
	
	
}
