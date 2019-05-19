package com.trainservice.train;

public class DiscountCalculator {
	private double total;
	private double amount;
	private double percentage;
	private double discount;
	
	public DiscountCalculator() {}

	public DiscountCalculator(double amount, double percentage) {
		super();
		this.amount = amount;
		this.percentage = percentage;
	}

	public DiscountCalculator(double total, double amount, double percentage, double discount) {
		super();
		this.total = total;
		this.amount = amount;
		this.percentage = percentage;
		this.discount = discount;
	}

	//calculating discount
	public void calculateDiscount() {
		discount = ((Math.round((amount / 100) * percentage) * 100) / 100.0);
		total = amount - discount;
		total = (Math.round(total * 100)) / 100.0; 
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
