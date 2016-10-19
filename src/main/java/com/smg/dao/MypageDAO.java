package com.smg.dao;

public interface MypageDAO {

	public String checkLastLogin(String customer_id, String username);
	public String checkFullName(String customer_id);
	
}
