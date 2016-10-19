package com.smg.model;

public class LoginModel {

	private String username; 
	private String password;
	private String customer_Id;
	private String repeat_Password;
	
	/**** CONSTRUCTOR ****/
	
	public LoginModel(){}
	public LoginModel(String customer_Id, String username, String password, String repeat_Password) {
		this.customer_Id = customer_Id;
		this.username = username;
		this.password = password;
		this.repeat_Password = repeat_Password;
	}
	
	/**** SETTER ****/
	
	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRepeat_Password(String repeat_Password) {
		this.repeat_Password = repeat_Password;
	}
	
	/**** GETTER ****/
	
	public String getCustomer_Id() {
		return customer_Id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRepeat_Password() {
		return repeat_Password;
	}
	
}
