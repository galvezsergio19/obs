package com.smg.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.model.VerificationModel;
import com.smg.service.VerifyService;
import com.smg.service.impl.VerifyServiceImpl;
import com.smg.utility.Property;

public class VerificationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private VerificationModel verify = null;

	public VerificationServlet() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			verify = new VerificationModel();
			request.setAttribute("status", "initial");
			request.setAttribute("isFieldSetEnabled", "");
			request.setAttribute("VerificationModel", verify);
			dispatcher = request.getRequestDispatcher(Property.getPage("Verify"));
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
			String preffered_name = request.getParameter("preffered_name");
			long account_no = Long.parseLong(request.getParameter("account_no"));
			int jai = Integer.parseInt(request.getParameter("jai"));
			String full_name = request.getParameter("full_name");
			Date birthday = Date.valueOf(request.getParameter("birthday"));
			long verification_code = Long.parseLong(request.getParameter("verification_code"));
			
			// Set the Customer Registration Model and Service
			verify = new VerificationModel(preffered_name, account_no, jai, full_name, birthday, verification_code);
			VerifyService verifyService = new VerifyServiceImpl();
			
			// Process the validation of Service
			verifyService.validateVerification(verify);
			request.setAttribute("VerificationModel", verify);
			
			// Error exists 
			if (verifyService.getVerificationError()!="") {
				request.setAttribute("status", "fail");
				request.setAttribute("displayMessage", Property.getError(verifyService.getVerificationError()));
				dispatcher = request.getRequestDispatcher(Property.getPage("Verify"));
				dispatcher.forward(request, response);
				
			// Successful Process
			} else {
				request.setAttribute("isFieldSetEnabled", "disabled");
				request.setAttribute("status", "success");
				request.setAttribute("displayMessage", "Verification successful. You'll be redirected to Login Account Creation Page after 10 seconds.");
				request.setAttribute("customer_id", verifyService.getCustomer_Id());
				
				// Send a redirect 
				response.setHeader("Refresh", "10; URL=user");
				dispatcher = request.getRequestDispatcher(Property.getPage("Verify"));
				dispatcher.forward(request, response);
			}
			
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
