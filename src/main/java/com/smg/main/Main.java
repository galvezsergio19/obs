package com.smg.main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import com.smg.model.RegistrationModel;
import com.smg.model.UserModel;
import com.smg.model.VerificationModel;
import com.smg.service.RegisterService;
import com.smg.service.UserService;
import com.smg.service.VerifyService;
import com.smg.service.impl.RegisterServiceImpl;
import com.smg.service.impl.UserServiceImpl;
import com.smg.service.impl.VerifyServiceImpl;
import com.smg.utility.Property;
import com.smg.utility.RegexCheck;

public class Main {

	private  static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
	
		//loginScreen();
		//String continues = scan.nextLine();
		//verifyScreen();
		//userScreen();
		RegexCheck regex = new RegexCheck();
		
		java.util.Scanner scan = new java.util.Scanner(System.in);
		String input = "";
		boolean quotes = regex.isInputQuote(input);
		boolean charac = regex.regexCheck(input);
		while ((charac==false) || (quotes==false)) {
			input = scan.nextLine();
			charac = regex.regexCheck(input);
			quotes = regex.isInputQuote(input);
			System.out.println(regex.regexCheck(input) + " " + regex.isInputQuote(input));
		}
		scan.close();
	}
	
	
	// First Screen
	public static void loginScreen() throws SQLException {
		
		System.out.println("Account Number: ");
		long accountNumber = Long.parseLong(scan.nextLine());
		System.out.println("JAI: ");
		int jai = Integer.parseInt(scan.nextLine());
		System.out.println("Full Name: ");
		String full_name = scan.nextLine();
		System.out.println("Birth Date (YYYY-MM-DD): ");
		Date birthday = Date.valueOf(scan.nextLine());
		
		// Set the Customer Registration Model and Service
		RegistrationModel register = new RegistrationModel(accountNumber, jai, full_name, birthday);
		RegisterService registerService = new RegisterServiceImpl();
		
		// Process the validation
		registerService.validateRegistration(register);
		if (registerService.getRegistrationError()!="") {
			displayError(registerService.getRegistrationError());
		} else {
			System.out.println("Verify the code below in ATM to proceed with Registration.");
		}
		scan.close();
	}
	
	
	public static void verifyScreen() {
		
		try {
		
			System.out.println("Preferred Account Name: ");
			String preferredName = scan.nextLine();
			System.out.println("Account Number: ");
			long accountNumber = Long.parseLong(scan.nextLine());
			System.out.println("Joint Account Indicator: ");
			int jai = Integer.parseInt(scan.nextLine());
			System.out.println("Full Name: ");
			String full_name = scan.nextLine();
			System.out.println("Birth Date (YYYY-MM-DD): ");
			Date birthday = Date.valueOf(scan.nextLine());
			System.out.println("New verification code from ATM: ");
			long atm_verification_code = Long.parseLong(scan.nextLine());
			
			// Set the Customer Registration Model and Service
			// Set the Customer Registration Model and Service
			VerifyService verifyService = new VerifyServiceImpl();
			VerificationModel verify = 
					new VerificationModel(preferredName, accountNumber, jai, full_name, birthday, atm_verification_code);
			
			// Process the validation
			verifyService.validateVerification(verify);
			if (verifyService.getVerificationError()!="") {
				displayError(verifyService.getVerificationError());
			} else {
				System.out.println("Verification successful. You can now proceed with Login account creation");
			}
			scan.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// First Screen
		public static void userScreen() throws SQLException {
			
			System.out.println("User Name: ");
			String username = scan.nextLine();
			System.out.println("Password: ");
			String password = scan.nextLine();
			System.out.println("Repeat Password: ");
			String repeat_password = scan.nextLine();
			
			// Set the Customer Registration Model and Service
			UserModel user = new UserModel("", username, password, repeat_password);
			UserService userService = new UserServiceImpl();
			
			// Process the validation
			userService.validateUser(user);
			if (userService.getUserError()!="") {
				displayError(userService.getUserError());
			} else {
				System.out.println("Verify the code below in ATM to proceed with Registration.");
			}
			scan.close();
		}
	
	
	public static void displayError(String error_Code) throws SQLException {
		System.out.println(Property.getError(error_Code));
	}

}
