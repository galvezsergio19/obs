package com.smg.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smg.model.TransactionModel;
import com.smg.service.BalanceService;
import com.smg.service.impl.BalanceServiceImpl;
import com.smg.utility.Property;

public class TransactionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	private BalanceService balanceService = null;
	private ArrayList<TransactionModel> accountsTransaction = new ArrayList<TransactionModel>();
	
    public TransactionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			balanceService = new BalanceServiceImpl();
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			String account_string = String.valueOf(request.getParameter("account_no"));
			dispatcher = request.getRequestDispatcher(Property.getPage("Transaction"));
			
			if (customer_id != "null") {
				
				// Redirects to Balance Page if no Account No pressed
				if (account_string != "null") {
					long account_no = Long.parseLong(account_string);
					accountsTransaction = balanceService.displayTransaction(customer_id, account_no);
					
					request.setAttribute("account_no", account_no);
					request.setAttribute("accountsTransaction", accountsTransaction);
					
				} else {
					
					response.setHeader("Refresh", "0; URL=balance");
					
				}
				
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
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			long account_no = Long.parseLong(request.getParameter("account_no"));
			session.setAttribute("account_no", account_no);
			balanceService = new BalanceServiceImpl();
			
			if (customer_id != "null") {
				
				// Redirects to Balance Page if no Account No pressed
				if (account_no != 0) {
					accountsTransaction = balanceService.displayTransaction(customer_id, account_no);
					request.setAttribute("accountsTransaction", accountsTransaction);
					dispatcher = request.getRequestDispatcher(Property.getPage("Transaction"));
				} else {
					dispatcher = request.getRequestDispatcher(Property.getPage("Balance"));
				}
				
			} else {
				
				dispatcher = request.getRequestDispatcher(Property.getPage("Login"));
				
			}
			
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
