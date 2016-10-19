package com.smg.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.service.SmsService;
import com.smg.service.impl.SmsServiceImpl;
import com.smg.utility.Property;

public class SmsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;

    public SmsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			dispatcher = request.getRequestDispatcher(Property.getPage("Sms"));
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Get Data from WebPage
			long account_no = Long.parseLong(request.getParameter("account_no"));
			
			// Set the Customer Registration Model and Service
			SmsService smsService = new SmsServiceImpl();
			smsService.displayTAC(account_no);
			dispatcher = request.getRequestDispatcher(Property.getPage("Sms"));
			
			// Error exists 
			if (!smsService.getSmsError().equals("")) {
				request.setAttribute("displayMessage", Property.getError(smsService.getSmsError()));
				
			// Successful Process
			} else {
				request.setAttribute("sms_tac", String.valueOf(smsService.getTAC()));
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
