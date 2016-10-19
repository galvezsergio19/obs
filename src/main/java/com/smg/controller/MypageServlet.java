package com.smg.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smg.model.UserModel;
import com.smg.service.DashboardService;
import com.smg.service.impl.DashboardServiceImpl;
import com.smg.utility.Property;

public class MypageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;

    public MypageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// Get Value From Model
			HttpSession session = request.getSession(false);
			DashboardService dashboardService = new DashboardServiceImpl();
			UserModel user = (UserModel) session.getAttribute("UserModel");
			
			String customer_id = String.valueOf(user.getCustomer_Id());
			String username = String.valueOf(user.getUsername());
			String full_name = "";
			// Get Certain from Database and Display to Dash board
			String Last_log = dashboardService.showLastLogin(customer_id, username);
			full_name = String.valueOf(dashboardService.showFullName(customer_id));
			request.setAttribute("Last_Login", Last_log);
			request.setAttribute("Full_name", full_name);
			dispatcher = request.getRequestDispatcher(Property.getPage("Mypage"));
			
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			// Logged out
			try {
				response.setHeader("Refresh", "0; URL=login");
				dispatcher = request.getRequestDispatcher(Property.getPage("Mypage"));
				dispatcher.forward(request, response);
			} catch (SQLException e1) {} 
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
