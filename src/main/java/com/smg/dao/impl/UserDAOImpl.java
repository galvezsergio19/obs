package com.smg.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.UserDAO;
import com.smg.model.UserModel;
import com.smg.utility.Property;

public class UserDAOImpl implements UserDAO {

	public boolean checkExistingUserName(UserModel user) {
		
		boolean isExists = false;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_user_name"));
			statement.setString(1, user.getUsername());
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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
	
	
	public void insertUserAccount(UserModel user) {
		
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("isert_user_account"));
			statement.setString(1, user.getCustomer_Id());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
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
