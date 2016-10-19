package com.smg.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smg.model.CustomerModel;
import com.smg.service.ProfileService;
import com.smg.service.impl.ProfileServiceImpl;
import com.smg.utility.Property;
import com.smg.utility.Rand;

public class ProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	private Rand random = new Rand();
	private ProfileService profileService = new ProfileServiceImpl();
	private CustomerModel customer = new CustomerModel();

    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			session = request.getSession(false);
			String customer_id = String.valueOf(session.getAttribute("customer_id"));
			
			if (customer_id != "null") {
				
				customer.setCustomer_id(customer_id);
				customer = profileService.displayCustomerProfile(customer_id);
				request.setAttribute("CustomerModel", customer);
				
				// Set all constant values when no values are found in db
				request.setAttribute("salutation", profileService.displayConstant("Salutation"));
				request.setAttribute("marital_stat", profileService.displayConstant("Marital Status"));
				request.setAttribute("gender", profileService.displayConstant("Gender"));
				request.setAttribute("race", profileService.displayConstant("Race"));
				
				request.setAttribute("isView", true);
				dispatcher = request.getRequestDispatcher(Property.getPage("Profile"));
				
			} else {
				
				// Logged out
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
			dispatcher = request.getRequestDispatcher(Property.getPage("Profile"));
			String Submit = request.getParameter("Submit");
			
			if (customer_id != "null") {
			
				customer.setCustomer_id(customer_id);
				customer = profileService.displayCustomerProfile(customer_id);
				request.setAttribute("CustomerModel", customer);

				// Set all constant values when no values are found in db
				request.setAttribute("salutation", profileService.displayConstant("Salutation"));
				request.setAttribute("marital_stat", profileService.displayConstant("Marital Status"));
				request.setAttribute("gender", profileService.displayConstant("Gender"));
				request.setAttribute("race", profileService.displayConstant("Race"));
				
				// Buttons Pressed  
				
				if (Submit.equals("View")) {
					request.setAttribute("isView", true);
					
				} else if(Submit.equals("Edit")) {
					request.setAttribute("isView", false);
					
				} else if(Submit.equals("Update")) {
					
					// Get all inputs from webpage
					String salutation = request.getParameter("salutation");
					String other_salutation = request.getParameter("other_salutation");
					String full_name = request.getParameter("full_name");
					String marital_stat = request.getParameter("marital_stat");
					String gender = request.getParameter("gender");
					String email = request.getParameter("email");
					String race = request.getParameter("race");
					String other_race = request.getParameter("other_race");
					String permanent_address = request.getParameter("permanent_address");
					int postal_code = Integer.parseInt(request.getParameter("postal_code"));
					String tel_no = request.getParameter("tel_no");
					String mob_no = request.getParameter("mob_no");
					String promo_materials = request.getParameter("promo_materials");
					String disclosure_info = request.getParameter("disclosure_info");
					
					// Generate National ID
					String national_id = 	String.valueOf(customer.getBirthday()) + 
											String.valueOf(postal_code) +
											(gender=="Male"?"01":"02") +
											String.valueOf(random.genRandomDigits(15));
					
					// Set all values to Model
					customer.setCustomer_id(customer_id);
					customer.setFull_name(full_name);
					customer.setSalutation(salutation);
					customer.setNational_id(national_id);
					customer.setMarital_stat(marital_stat);
					customer.setGender(gender);
					customer.setEmail(email);
					customer.setRace(race);
					customer.setPermanent_address(permanent_address);
					customer.setPostal_code(postal_code);
					customer.setTel_no(tel_no);
					customer.setMob_no(mob_no);
					customer.setPromo_materials(promo_materials);
					customer.setDisclosure_info(disclosure_info);
					customer.setOther_race(other_race);
					customer.setOther_salutation(other_salutation);
					
					customer = (CustomerModel) request.getAttribute("CustomerModel");
					profileService.updateCustomerRecord(customer);
					
					// Error exists 
					if (profileService.getProfileError()!="") {
						request.setAttribute("isView", false);
						request.setAttribute("status", "fail");
						request.setAttribute("displayMessage", Property.getError(profileService.getProfileError()));
						
					// Successful Process
					} else {
						request.setAttribute("isView", true);
						request.setAttribute("status", "success");
					}
					
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
