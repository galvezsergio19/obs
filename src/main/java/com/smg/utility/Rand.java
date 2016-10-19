package com.smg.utility;

import java.util.Random;

public class Rand {

	public long genRandomDigits(int length) {
		
		Random random = new Random();
		String startString = "";
		String endString = "";
		
		for (int i=1;i<=length;i++) {
			startString = startString == "" ? startString + "1" : startString + "0"; 
		}
		for (int i=1;i<=length;i++) {
			endString = endString + "9";
		}
		
		long start = Long.parseLong(startString);
		long end = Long.parseLong(endString); 
	    long range = end - start + 1;
	    long fraction = (long)(range * random.nextDouble());
	    long randomNumber =  fraction + start;
		return randomNumber;
		
	}
	
}
