package com.smg.service.impl;

import com.smg.dao.MypageDAO;
import com.smg.dao.impl.MypageDAOImpl;
import com.smg.service.DashboardService;

public class DashboardServiceImpl implements DashboardService {

	private MypageDAO dashboardDao = new MypageDAOImpl();
	
	public String showLastLogin(String customer_id, String username) {
		String lastLogin = "";
		lastLogin = dashboardDao.checkLastLogin(customer_id, username);
		return lastLogin;
	}
	
	public String showFullName(String customer_id) {
		String full_name = "";
		full_name = dashboardDao.checkFullName(customer_id);
		return full_name;
	}
	
}
