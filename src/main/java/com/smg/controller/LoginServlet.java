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
import com.smg.service.LoginService;
import com.smg.service.impl.LoginServiceImpl;
import com.smg.utility.Property;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			dispatcher = request.getRequestDispatcher(Property.getPage("Login"));
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// Get Data from WebPage
			session = request.getSession(true);
			session.setAttribute("customer_id", "");
			String username = "U".concat(request.getParameter("username"));
			String password = request.getParameter("password");
			
			// Set the Customer Registration Model and Service
			UserModel user = new UserModel("", username, password, "");
			LoginService loginService = new LoginServiceImpl();
			
			// Process the validation
			loginService.validateLogin(user);
			
			// Error exists 
			if (loginService.getLoginError()!="") {
				request.setAttribute("isError", true);
				request.setAttribute("displayMessage", Property.getError(loginService.getLoginError()));
				
			// Successful Process
			} else {
				session.setAttribute("customer_id", loginService.getCustomer_id());
				session.setAttribute("UserModel",  user);
				response.setHeader("Refresh", "0; URL=mypage"); // Sends redirect
			}
			
			dispatcher = request.getRequestDispatcher(Property.getPage("Login"));
			dispatcher.forward(request, response);
			
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
