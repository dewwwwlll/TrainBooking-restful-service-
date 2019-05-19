package com.trainservice.train;

//class to get the ticket details from client
public class TicketDetails {
	private String source;
	private String destination;
	private int no_of_tickets;
	
	public TicketDetails(String source, String destination, int no_of_tickets) {
		super();
		this.source = source;
		this.destination = destination;
		this.no_of_tickets = no_of_tickets;
	}

	public TicketDetails() {
		super();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getNo_of_tickets() {
		return no_of_tickets;
	}

	public void setNo_of_tickets(int no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	};
	
	
	
	
}
