package com.smg.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.MypageDAO;
import com.smg.utility.Property;

public class MypageDAOImpl implements MypageDAO {

	public String checkLastLogin(String customer_id, String username) {
		
		String last_login = "";
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_last_login"));
			statement.setString(1, customer_id);
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				last_login = rs.getString("last_log");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return last_login;
	}
	
	public String checkFullName(String customer_id) {
		
		String full_name = "";
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_customer_fullname"));
			statement.setString(1, customer_id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				full_name = rs.getString("full_name");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return full_name;
	}
	
}
