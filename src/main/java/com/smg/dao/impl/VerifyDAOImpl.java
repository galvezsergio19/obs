package com.smg.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.smg.dao.VerifyDAO;
import com.smg.model.VerificationModel;
import com.smg.utility.Property;

public class VerifyDAOImpl implements VerifyDAO {

	public long retireveVerification_No(VerificationModel verify) {
		
		long verication_code = 0;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_verification_match"));
			statement.setLong(1, verify.getAccount_no());
			statement.setInt(2, verify.getJai());
			statement.setString(3, verify.getFull_name());
			statement.setDate(4, verify.getBirthday());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				verication_code = rs.getLong("atm_verify");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return verication_code;
			
	}
	
	public Timestamp retireveReg_date(VerificationModel verify) {
		
		Timestamp reg_Date = null;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_reg_date"));
			statement.setLong(1, verify.getAccount_no());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				reg_Date = rs.getTimestamp("reg_date");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return reg_Date;
	}
	
	
	public boolean checkVerificationStatus(VerificationModel verify) {
		
		boolean isVerified = false;
		String verification_stat = "";
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_verification_stat"));
			statement.setLong(1, verify.getAccount_no());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				verification_stat = rs.getString("verify_status");
			}
			
			if (verification_stat!="" && verification_stat=="Y") {
				isVerified = true;
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isVerified;	
	}
	
	public void updateVerificationStatus(VerificationModel verify) {
		
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("update_verification_stat"));
			statement.setString(1, "Y");
			statement.setLong(2, verify.getAccount_no());
			statement.setLong(3, verify.getAtm_verification_code());
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			
	}
	
	
	public void updatePrefferedName(VerificationModel verify) {
		
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("update_acct_preffered_name"));
			statement.setString(1, verify.getPreferredName());
			statement.setLong(2, verify.getAccount_no());
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			
	}
	
	
	public String getCustomer_Id(VerificationModel verify) {
		
		String customer_id = "";
		try {
			
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_customer_id_for_account"));
			statement.setLong(1, verify.getAccount_no());
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
