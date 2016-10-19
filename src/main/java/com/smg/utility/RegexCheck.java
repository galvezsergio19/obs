package com.smg.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCheck {

	private Pattern pattern = null;
	private Matcher matcher = null;
	
	public boolean regexCheck(String password) {
		
		boolean isValid = false;
		String regexPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(password);
		if (matcher.find()) {
			isValid = true;
		}
		return isValid;
	}
	
	public String convertBigDecimalString(BigDecimal bigDecimal){
		
		DecimalFormat decimalformat = new DecimalFormat("P#,###.00");
		String stringAmount = new String(decimalformat.format(bigDecimal)); 
		return stringAmount;
		
	}
	
	public boolean isInputWhiteSpace(String input) {
		
		pattern = Pattern.compile("\\s");
		matcher = pattern.matcher(input);
		return matcher.find();
		
	}
	
	public boolean isInputSpecialCharacter(String input) {
		
		pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(input);
		return matcher.find();
		
	}
	
	public boolean isInputQuote(String input) {
		
		boolean isQuote = true;
		if (input.contains("\'")||input.contains("\"")) {
			isQuote = false;
		}
		return isQuote;
	}
	
	
	public boolean isEmailValid(String input) {
		
		pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(input);
        return matcher.find();
		
	}
	
}
