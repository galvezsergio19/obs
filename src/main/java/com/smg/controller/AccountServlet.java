package com.smg.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smg.model.VerificationModel;
import com.smg.service.AccountService;
import com.smg.service.VerifyService;
import com.smg.service.impl.AccountServiceImpl;
import com.smg.service.impl.VerifyServiceImpl;
import com.smg.utility.Property;

public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	private VerificationModel verify = null;

    public AccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			dispatcher = request.getRequestDispatcher(Property.getPage("Account"));
			
			if (customer_id != "null") {
				
				
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
			
			session = request.getSession();
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			dispatcher = request.getRequestDispatcher(Property.getPage("Account"));
			
			if (customer_id != "null") {
				
				// Get Data from WebPage
				String preffered_name = request.getParameter("preffered_name");
				long account_no = Long.parseLong(request.getParameter("account_no"));
				int jai = Integer.parseInt(request.getParameter("jai"));
				long verification_code = Long.parseLong(request.getParameter("verification_code"));
				
				// Retrieve full name and birthday by customer_id
				AccountService accountService = new AccountServiceImpl();
				verify = accountService.getCustomerFullNameAndBirthday(customer_id);
				
				// Set the Customer Registration Model and Service
				verify.setPreferredName(preffered_name);
				verify.setAccount_no(account_no);
				verify.setjai(jai);
				verify.setAtm_verification_code(verification_code);
				
				// Process the validation of Service
				VerifyService verifyService = new VerifyServiceImpl();
				verifyService.validateVerification(verify);
				request.setAttribute("VerificationModel", verify);
				
				// Error exists 
				if (verifyService.getVerificationError()!="") {
					request.setAttribute("status", "fail");
					request.setAttribute("displayMessage", Property.getError(verifyService.getVerificationError()));
					
				// Successful Process
				} else {
					request.setAttribute("status", "success");
					request.setAttribute("displayMessage", "Verification successful. You can now use your Joint Account.");
					
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

}
