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
import com.smg.service.UserService;
import com.smg.service.impl.UserServiceImpl;
import com.smg.utility.Property;

public class UserAthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			dispatcher = request.getRequestDispatcher(Property.getPage("User"));
			
			if (customer_id==null) {
				
				request.setAttribute("customer_id", customer_id);
				request.setAttribute("status", "initial");
				request.setAttribute("isFieldSetEnabled", "");
			
			} else {
				
				// Logged out
				response.setHeader("Refresh", "0; URL=verify");
			}
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (NullPointerException e) {
			
			// Logged out
			try {
				response.setHeader("Refresh", "0; URL=verify");
				dispatcher = request.getRequestDispatcher(Property.getPage("User"));
				dispatcher.forward(request, response);
			} catch (SQLException e1) {
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			// Get Data from WebPage
			session = request.getSession(true);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			String username = "U" + request.getParameter("username");
			String password = request.getParameter("password");
			String repeat_password = request.getParameter("repeat_password");
			
			// Set the Customer Registration Model and Service
			UserModel user = new UserModel(customer_id, username, password, repeat_password);
			UserService userService = new UserServiceImpl();
			
			// Process the validation
			userService.validateUser(user);
			dispatcher = request.getRequestDispatcher(Property.getPage("User"));
			
			// Error exists 
			if (userService.getUserError()!="") {
				request.setAttribute("status", "fail");
				request.setAttribute("displayMessage", Property.getError(userService.getUserError()));
				
			// Successful Process
			} else {
				request.setAttribute("isFieldSetEnabled", "disabled");
				request.setAttribute("status", "success");
				request.setAttribute("displayMessage", "Congratulations! Welcome to Online Banking. Page will be redirected after 10 seconds.");
				
				// Send a redirect 
				response.setHeader("Refresh", "10; URL=login");
				
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
