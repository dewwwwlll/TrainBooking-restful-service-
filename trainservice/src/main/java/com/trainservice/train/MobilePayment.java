package com.trainservice.train;

import java.util.Date;

//mobile pay entity
public class MobilePayment {
	
	private String mobileNumber;
	private String pinNumber;
	private String email;
	private double amount;
	private String message;
	
	public MobilePayment() {
		
	}
	
	public MobilePayment(String mobileNumber, String pinNumber, String email, double amount, String message) {
		super();
		this.mobileNumber = mobileNumber;
		this.pinNumber = pinNumber;
		this.email = email;
		this.amount = amount;
		this.message = message;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] pay() {
		
		String sequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz"
                					+ "0123456789"; 
		String date = new Date().toString();
				
			StringBuilder confirmationcodeGenerator = new StringBuilder(7); 
			
			for (int i = 0; i < 7; i++) { 
				
				int index = (int)(sequence.length() * Math.random()); 
				confirmationcodeGenerator.append(sequence.charAt(index)); 
			} 
	 
		String payment[] = {"Successfully Payed!", "cCP"+ confirmationcodeGenerator.toString() + date.replaceAll("\\s","")};
		return payment;
	}

}
