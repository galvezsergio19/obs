package com.smg.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.RegisterDAO;
import com.smg.utility.DBConnection;
import com.smg.utility.Property;

public class RegisterDAOImpl implements RegisterDAO {

	private boolean isExists = false;
	
	public boolean checkAccountNoIfExist(long accountNumber, int jai) {
		
		isExists = false;
		try {
			
			PreparedStatement statement = DBConnection.getConnection(Property.getQuery("check_account_number"));
			statement.setLong(1, accountNumber);
			statement.setInt(2, jai);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				isExists = true;
			}
			
			rs.close();
			statement.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isExists;
	}
	
	public boolean checkCustomerIdIfExist(String customer_Id) {
		
		isExists = false;
		try {
					
			PreparedStatement statement = DBConnection.getConnection(Property.getQuery("check_customer_id"));
			statement.setString(1, customer_Id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				isExists = true;
			}
			
			rs.close();
			statement.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isExists;
	}
	
	public boolean checkLinkIdIfExist(String link_Id) {
		
		isExists = false;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_link_id"));
			statement.setString(1, link_Id);
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
	
	
	
	public boolean checkCustomerLinkIfExist(long accountNumber) {
		
		isExists = false;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_customer_link"));
			statement.setLong(1, accountNumber);
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
	
	public boolean checkExistingAccountStatus(long accountNumber) {
		
		isExists = false;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_existing_account_status"));
			statement.setLong(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				isExists = false;
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
	
	
	
	public void insertCustomerLink(String link_id, String customer_Id, long account_no) {
		
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_reg_customer_link"));
			statement.setString(1, link_id);
			statement.setString(2, customer_Id);
			statement.setLong(3, account_no);
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void insertCustomer(String customer_Id, String full_name, Date birthday) {
		
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_reg_customer"));
			statement.setString(1, customer_Id);
			statement.setString(2, full_name);
			statement.setDate(3, birthday);
			statement.executeUpdate();
			
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void InsertVerificationCode(String link_id, long atm_verification, long web_verification) {
		
		try {
			
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("insert_verification"));
			statement.setString(1, link_id);	
			statement.setLong(2, atm_verification);
			statement.setLong(3, web_verification);
			statement.setString(4, "N");	// Verification Status
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
