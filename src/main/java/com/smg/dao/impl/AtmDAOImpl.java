package com.smg.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.AtmDAO;
import com.smg.model.AtmModel;
import com.smg.utility.Property;

public class AtmDAOImpl implements AtmDAO {

	public boolean checkAccountNo(AtmModel atm) {
		
		boolean isExists = false;
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_existing_account_status"));
			statement.setLong(1, atm.getAccount_no());
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
	
	
	public long checkAtmVerification(AtmModel atm) {
		
		long atm_verification = 0;
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_web_verification"));
			statement.setLong(1, atm.getAccount_no());
			statement.setLong(2, atm.getWeb_verify());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				atm_verification = rs.getLong("atm_verify");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return atm_verification;
	}
	
	
	public void insertAccount(long account_no, int jai, BigDecimal balance) {
		
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_account"));
			statement.setLong(1, account_no);
			statement.setInt(2, jai);
			statement.setBigDecimal(3, balance);
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public String checkCustomerLinkforAccount(long accountNumber) {
		
		String customer_id = "";
		try {
					
			// Open Connection
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_customer_link"));
			statement.setLong(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				customer_id = rs.getString("customer_id");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return customer_id;
	}
	
	
}
