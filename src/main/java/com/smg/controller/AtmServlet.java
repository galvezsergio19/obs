package com.smg.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.model.AtmModel;
import com.smg.model.RegistrationModel;
import com.smg.service.AtmService;
import com.smg.service.impl.AtmServiceImpl;
import com.smg.utility.Property;

public class AtmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private AtmService atmService = null;

    public AtmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			dispatcher = request.getRequestDispatcher(Property.getPage("Atm"));
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// Check which form was submitted
			String form_name = request.getParameter("form-name");
			long account_no = 0;
			dispatcher = request.getRequestDispatcher(Property.getPage("Atm"));
			
			if ( form_name.equals("Verify")) {
				
				// Get Data from WebPage
				account_no = Long.parseLong(request.getParameter("account_no"));
				long web_verify = Long.parseLong(request.getParameter("web_verify"));
				
				// Set the Customer Registration Model
				atmService = new AtmServiceImpl();
				AtmModel atm = new AtmModel(account_no, web_verify);
				
				// Process the validation
				atmService.validateLogin(atm);
				
				// Error exists 
				if (atmService.getAtmError()!="") {
					request.setAttribute("displayMessage", Property.getError(atmService.getAtmError()));
					
				// Successful Process
				} else {
					request.setAttribute("atm_verification", String.valueOf(atmService.getAtmVerificationCode()));
				}
			
			} else if (form_name.equals("Register")) {
				
				// Get Data from WebPage
				account_no = Long.parseLong(request.getParameter("account_no"));
				int jai = Integer.parseInt(request.getParameter("jai"));
				BigDecimal balance = new BigDecimal(request.getParameter("balance"));
				
				// Set the Customer Registration Model
				atmService = new AtmServiceImpl();
				RegistrationModel register = new RegistrationModel(account_no, jai, null, null);
				
				// Process the validation
				atmService.validateRegistration(register, balance);
				
				// Error exists 
				if (atmService.getAtmError()!="") {
					request.setAttribute("status", "fail");
					request.setAttribute("displayMessageReg", Property.getError(atmService.getAtmError()));
					
				// Successful Process
				} else {
					request.setAttribute("status", "success");
					request.setAttribute("displayMessageReg", "Account Registration Successful! You can proceed to Customer Registration online.");
				}
				
			}
			
			else if (form_name.equals("Enrollment")) {
				
				// Get Data from WebPage
				long primary_account_no = Long.parseLong(request.getParameter("primary_account_no"));
				long joint_account_no = Long.parseLong(request.getParameter("joint_account_no"));
				int primary_account_jai = Integer.parseInt(request.getParameter("primary_account_jai"));
				int joint_account_jai = Integer.parseInt(request.getParameter("joint_account_jai"));
				
				// Set the Customer Registration Model
				atmService = new AtmServiceImpl();
				
				// Process the validation
				atmService.validateEnrollment(primary_account_no, primary_account_jai, joint_account_no, joint_account_jai);
				
				// Error exists 
				if (atmService.getAtmError()!="") {
					request.setAttribute("status", "fail");
					request.setAttribute("displayMessageEnrol", Property.getError(atmService.getAtmError()));
					
				// Successful Process
				} else {
					request.setAttribute("status", "success");
					request.setAttribute("enrol_verification", String.valueOf(atmService.getAtmVerificationCode()));
					request.setAttribute("displayMessageEnrol", "Account Joint Successful! You can proceed to Account Enrollment Verification online.");
				}
				
			}
				
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();                                                                                                                  	
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
