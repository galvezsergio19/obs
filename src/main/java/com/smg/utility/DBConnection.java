package com.smg.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	
	public static PreparedStatement getConnection(String sql) {
		
		PreparedStatement statement = null;
		
		// Open Connection
		try {
			
			Class.forName(Property.getConfig("driver"));
			Connection con = DriverManager.getConnection(
							Property.getConfig("url"), 
							Property.getConfig("username"), 
							Property.getConfig("password"));
			statement = con.prepareStatement(sql);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
}
