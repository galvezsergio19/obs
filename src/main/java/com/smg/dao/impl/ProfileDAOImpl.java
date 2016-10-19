package com.smg.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.smg.dao.ProfileDAO;
import com.smg.model.CustomerModel;
import com.smg.utility.Property;

public class ProfileDAOImpl implements ProfileDAO {

	
	public ArrayList<String> checkConstant(String field_name) {
		
		ArrayList<String> field_value = new ArrayList<String>();
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_salutation"));
			statement.setString(1, field_name);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				field_value.add(rs.getString("field_value"));
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return field_value;
	}
	
	public CustomerModel checkCustomer(String customer_id) {
		
		CustomerModel customer = new CustomerModel();
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("check_customer_profile"));
			statement.setString(1, customer_id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				customer.setFull_name(rs.getString("full_name"));
				customer.setBirthday(rs.getDate("birthday"));
				customer.setSalutation(rs.getString("salutation"));
				customer.setNational_id(rs.getString("national_id"));
				customer.setMarital_stat(rs.getString("marital_stat"));
				customer.setGender(rs.getString("gender"));
				customer.setEmail(rs.getString("email"));
				customer.setRace(rs.getString("race"));
				customer.setPermanent_address(rs.getString("permanent_address"));
				customer.setPostal_code(rs.getInt("postal_code"));
				customer.setTel_no(rs.getString("tel_no"));
				customer.setMob_no(rs.getString("mob_no"));
				customer.setPromo_materials(rs.getString("promo_materials"));
				customer.setDisclosure_info(rs.getString("disclosure_info"));
				customer.setOther_salutation((rs.getString("other_salutation")));
				customer.setOther_race((rs.getString("other_race")));
			}
			
			rs.close();
			statement.close();
			con.close();
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return customer;
	}

	
	public void updateCustomerRecord(CustomerModel customer) {
		
		try {
					
			// Open Connection
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(Property.getConfig("url"), 
														Property.getConfig("username"), 
														Property.getConfig("password"));
			
			//UPDATE customer SET full_name=?, salutation=?, natioanal_id=?, marital_stat=?, gender=?, email=?, 
			//race=?, permanent_address=?, postal_code=?, tel_no=?, mob_no=?, promo_materials=?, disclosure_info=? 
			//WHERE customer_id=? 
			
			PreparedStatement statement = con.prepareStatement(Property.getQuery("update_customer_record"));
			statement.setString(1, customer.getFull_name());
			statement.setString(2, customer.getSalutation());
			statement.setString(3, customer.getNational_id());
			statement.setString(4, customer.getMarital_stat());
			statement.setString(5, customer.getGender());
			statement.setString(6, customer.getEmail());
			statement.setString(7, customer.getRace());
			statement.setString(8, customer.getPermanent_address());
			statement.setInt(9, customer.getPostal_code());
			statement.setString(10, customer.getTel_no());
			statement.setString(11, customer.getMob_no());
			statement.setString(12, customer.getPromo_materials());
			statement.setString(13, customer.getDisclosure_info());
			statement.setString(14, customer.getOther_salutation());
			statement.setString(15, customer.getOther_race());
			statement.setString(16, customer.getCustomer_id());
			
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
