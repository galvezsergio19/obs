package com.smg.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smg.dao.LoginDAO;
import com.smg.model.UserModel;
import com.smg.utility.DBConnection;
import com.smg.utility.Property;

public class LoginDAOImpl implements LoginDAO {

	public String checkGetUsername(UserModel user) {
		
		String customer_id = "";
		try {
					
			PreparedStatement statement = DBConnection.getConnection(
							Property.getQuery("check_customerid_from_login"));

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				customer_id = rs.getString("customer_id");
			}
			
			rs.close();
			statement.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return customer_id;
	}
	
	
	public void updateLastLogin(UserModel user) {
		
		try {
			
			PreparedStatement statement = DBConnection.getConnection(Property.getQuery("update_last_login"));
			statement.setString(1, user.getCustomer_Id());
			statement.setString(2, user.getUsername());
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			
	}
	
	
}
