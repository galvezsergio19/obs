package com.smg.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smg.model.FundModel;
import com.smg.service.FundService;
import com.smg.service.impl.FundServiceImpl;
import com.smg.utility.Property;

public class FundServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	private FundModel fund = null;
	private long account_no;
	private long account_no_to;
	private BigDecimal amount;

    public FundServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			FundService fundService = new FundServiceImpl();
			
			if (customer_id != "null") {
				// Set Attributes
				request.setAttribute("isButtonEnabled", ""); 
				request.setAttribute("Account_Number_Options", fundService.customersAccount(customer_id));
				request.setAttribute("Account_Number", "");
				request.setAttribute("status", "");
				
				if (account_no!=0 && account_no_to!=0 && amount.compareTo(new BigDecimal("0"))!=-1) {
					account_no = Long.parseLong(request.getParameter("account_no_from"));
					account_no_to = Long.parseLong(request.getParameter("account_no_to"));
					amount = new BigDecimal(request.getParameter("amount"));
					fund = new FundModel(account_no, account_no_to, amount, 0);
					request.setAttribute("FundModel", fund);
				}
				
				dispatcher = request.getRequestDispatcher(Property.getPage("Fund"));
			} else {
				
				response.setHeader("Refresh", "0; URL=login");
			}
			
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String submit = request.getParameter("Submit");
			FundService fundService = new FundServiceImpl();
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			dispatcher = request.getRequestDispatcher(Property.getPage("Fund"));
			
			if (customer_id != "null") {
			
				// Get input from WebPage
				long account_no = Long.parseLong(request.getParameter("account_no_from"));
				String account_no_to_string = request.getParameter("account_no_to");
				if (!account_no_to_string.equals("") && !account_no_to_string.equals(null)) {
					account_no_to = Long.parseLong(request.getParameter("account_no_to"));
				}
				String amount_string = request.getParameter("amount");
				if (!amount_string.equals("") && !amount_string.equals(null)) {
					amount = new BigDecimal(request.getParameter("amount"));
				}
				
				
				if (submit.equals("Send TAC")) {
					
					fundService.generateTAC(account_no);
					
					// Set Attributes
					request.setAttribute("isButtonEnabled", "disabled"); // Send TAC Button
					request.setAttribute("isFieldRequired", "required");
					request.setAttribute("Account_Number_Options", fundService.customersAccount(customer_id));
					request.setAttribute("Account_Number", account_no);
					request.setAttribute("account_no_to", account_no_to);
					request.setAttribute("amount", amount);
					request.setAttribute("status", "");
					
					fund = new FundModel(account_no, account_no_to, amount, 0);
					
				} else {
					
					
					long tac = Long.parseLong(request.getParameter("tac"));
					
					// Set the Model
					fund = new FundModel(account_no, account_no_to, amount, tac);
					
					// Validate the Inputs
					fundService.validateFundTransfer(fund);
					
					// Set Attributes
					request.setAttribute("Account_Number_Options", fundService.customersAccount(customer_id));
					request.setAttribute("Account_Number", account_no);
					
					// Error exists 
					if (fundService.getFundError()!="") {
						request.setAttribute("status", "fail");
						request.setAttribute("displayMessage", Property.getError(fundService.getFundError()));
						
					// Successful Process
					} else {
						request.setAttribute("status", "success");
						request.setAttribute("isFieldSetEnabled", "disabled");
						request.setAttribute("displayMessage", "Fund Transfer Successful.");
					}
					
				}
				
				request.setAttribute("FundModel", fund);
				
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
