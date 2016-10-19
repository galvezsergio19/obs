package com.smg.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.SmsDAO;
import com.smg.model.AtmModel;
import com.smg.utility.Property;

public class SmsDAOImpl implements SmsDAO {

	
	public long checkTac(AtmModel atm) {
		
		long auth_code = 0;
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_sms_tac"));
			statement.setLong(1, atm.getAccount_no());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				auth_code = rs.getLong("auth_code");
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return auth_code;
	}

	
}
