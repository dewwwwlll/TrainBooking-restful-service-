package com.trainservice.train;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import javax.crypto.*;

public class CreditCardPayment {
	private String cardNo;
	private String cvcNo;
	private String expDate;
	private String cardHolder;
	private double amount;
	private String email;
	private String message;
	private String subject;
	
	public CreditCardPayment() {
	}


	public CreditCardPayment(String cardNo, String cvcNo, String expDate, String cardHolder, double amount,
			String email, String message, String subject) {
		super();
		this.cardNo = cardNo;
		this.cvcNo = cvcNo;
		this.expDate = expDate;
		this.cardHolder = cardHolder;
		this.amount = amount;
		this.email = email;
		this.message = message;
		this.subject = subject;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getCvcNo() {
		return cvcNo;
	}


	public void setCvcNo(String cvcNo) {
		this.cvcNo = cvcNo;
	}


	public String getExpDate() {
		return expDate;
	}


	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}


	public String getCardHolder() {
		return cardHolder;
	}


	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String[] pay(){
		
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
