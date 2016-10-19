package com.smg.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.AccountDAO;
import com.smg.model.VerificationModel;
import com.smg.utility.DBConnection;
import com.smg.utility.Property;

public class AccountDAOImpl implements AccountDAO {

	public VerificationModel checkCustomerinfo(String customer_id) {
		
		VerificationModel verify = new VerificationModel();
		try {
			
			PreparedStatement statement = DBConnection.getConnection(Property.getQuery("check_customer_id"));
			statement.setString(1, customer_id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				verify.setFull_name(rs.getString("full_name"));
				verify.setBirthday(rs.getDate("birthday"));
			}
			
			rs.close();
			statement.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return verify;
	}
	
	
}
