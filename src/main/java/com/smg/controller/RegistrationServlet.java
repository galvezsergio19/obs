package com.smg.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.model.RegistrationModel;
import com.smg.service.RegisterService;
import com.smg.service.impl.RegisterServiceImpl;
import com.smg.utility.Property;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private RegistrationModel register = null; 

	public RegistrationServlet() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			register = new RegistrationModel();
			request.setAttribute("RegistrationModel", register);
			
			// Set page to redirect
			dispatcher = request.getRequestDispatcher(Property.getPage("Register"));
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			// Get Data from WebPage
			long account_no = Long.parseLong(request.getParameter("account_no"));
			int jai = Integer.parseInt(request.getParameter("jai"));
			String full_name = request.getParameter("full_name");
			Date birthday = Date.valueOf(request.getParameter("birthday"));
			
			// Page to redirect
			dispatcher = request.getRequestDispatcher(Property.getPage("Register"));;
			
			// Set the Customer Registration Model and Service
			register = new RegistrationModel(account_no, jai, full_name, birthday);
			RegisterService registerService = new RegisterServiceImpl();
			
			// Process the validation of Service
			registerService.validateRegistration(register);
			request.setAttribute("RegistrationModel", register);
			
			// Error exists 
			if (registerService.getRegistrationError()!="") {
				request.setAttribute("status", "fail");
				request.setAttribute("displayMessage", Property.getError(registerService.getRegistrationError()));
				
			// Successful Process
			} else {
				request.setAttribute("status", "success");
				request.setAttribute("isFieldSetEnabled", "disabled");
				request.setAttribute("verification_code", registerService.getVerification_code());
				request.setAttribute("displayMessage", "Verify the code in ATM to proceed with Registration.");
			}
			
			dispatcher.forward(request, response);
					
		} catch (IOException e) {
			e.printStackTrace();                                                                                                      
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
