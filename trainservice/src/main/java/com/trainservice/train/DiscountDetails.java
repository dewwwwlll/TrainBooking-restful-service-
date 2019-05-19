package com.trainservice.train;

//for getting the details require to offer a discount
public class DiscountDetails {

	private double amount;
	private int discountPercentage;
	private String nIC;

	public DiscountDetails() {
	}

	public DiscountDetails(double amount, int discountPercentage, String nIC) {
		super();
		this.amount = amount;
		this.discountPercentage = discountPercentage;
		this.nIC = nIC;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getnIC() {
		return nIC;
	}

	public void setnIC(String nIC) {
		this.nIC = nIC;
	}

	
		
}
