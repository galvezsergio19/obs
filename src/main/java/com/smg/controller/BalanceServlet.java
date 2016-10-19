package com.smg.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smg.model.BalanceModel;
import com.smg.service.BalanceService;
import com.smg.service.impl.BalanceServiceImpl;
import com.smg.utility.Property;

public class BalanceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	
	private BalanceService balanceService = null;
	private ArrayList<BalanceModel> accountsBalance = null;

    public BalanceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			accountsBalance = new ArrayList<BalanceModel>();
			balanceService = new BalanceServiceImpl();
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			dispatcher = request.getRequestDispatcher(Property.getPage("Balance"));
			
			if (customer_id != "null") {
				
				Timestamp todayTimeStamp = new Timestamp(System.currentTimeMillis());
				request.setAttribute("today", todayTimeStamp);
				accountsBalance = balanceService.displayBalance(customer_id);
				
				request.setAttribute("accountsBalance", accountsBalance);
				
			} else {
				
				// Logged Out
				response.setHeader("Refresh", "0; URL=login");
				
			}
			
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			long account_no = Long.parseLong(request.getParameter("account_no"));
			balanceService = new BalanceServiceImpl();
			
			session = request.getSession();
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			
			if (customer_id != "null") {
				
				request.setAttribute("accountsBalance", accountsBalance);
				request.setAttribute("account_no", account_no);
				dispatcher = request.getRequestDispatcher(Property.getPage("Transaction"));
				
			} else {
				
				// Logged Out
				response.setHeader("Refresh", "0; URL=login");
			}
			
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
