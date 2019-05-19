package com.trainservice.train;

//ticket details 
public class Ticket {
	private double startStation = 0;
	private double endStation = 0;
	private int quantity = 0;
	private double price = 0;
	
	public Ticket(double startStation, double endStation,int quantity) {
		this.startStation = startStation;
		this.endStation = endStation;
		this.quantity = quantity;
	}

	public double getStartStation() {
		return startStation;
	}

	public void setStartStation(double startStation) {
		this.startStation = startStation;
	}

	public double getEndStation() {
		return endStation;
	}

	public void setEndStation(double endStation) {
		this.endStation = endStation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	//calculating the prices for source to destination
	public void calculatePrice() {
		this.price = this.startStation + this.endStation;
		if(price > 20 && price <= 100)
			price = price / 4;
		else if(price > 100)
			price = price / 8;
		price = price * quantity;
	}

}
