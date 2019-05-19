package com.trainservice.trainservice;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.ParserDelegator;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.trainservice.mongodb.queries.EmployeeDetailsOperations;
import com.trainservice.mongodb.queries.TrainDetailsOperations;
import com.trainservice.train.DiscountCalculator;
import com.trainservice.train.DiscountDetails;
import com.trainservice.train.Employee;
import com.trainservice.train.Ticket;
import com.trainservice.train.TicketDetails;
import com.trainservice.train.TrainStations;

@Path("/traindetails")
public class TrainDetails {

	TrainDetailsOperations trainOperations;
	List <TrainStations> stations;
	EmployeeDetailsOperations employeeOperations;
	List <Employee> employee;
	
	public TrainDetails() throws UnknownHostException {
		trainOperations = new TrainDetailsOperations();
		stations = trainOperations.getAllStations();
		employeeOperations = new EmployeeDetailsOperations();
    	employee = employeeOperations.getAllEmployees();
	}
	
	@Path("/stations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrains() {
    	return Response.ok()
    			.entity(stations)
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.allow("OPTIONS").build();
    }
    
    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@Context HttpServletRequest request) {
    	return Response.ok()
    			.header("Access-Control-Allow-Origin", request.getHeader("Origin"))
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.allow("OPTIONS").build();
    }
     
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrice(TicketDetails details, @Context HttpServletResponse response, @Context HttpServletRequest request) {
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    	TrainStations startStationObj = null;
    	TrainStations endStationObj = null;
    	
    	String source = details.getSource();
    	String dest = details.getDestination();
    	int quantity = details.getNo_of_tickets();
    	
    	System.out.println("Source: " +source);
    	
    	
    	for(TrainStations tS: stations) {
//    		System.out.println("Inside 1");
    		if(tS.getStationName().equalsIgnoreCase(source)) {
    			System.out.println("Source: " + source+ ", in the list: " + tS.getStationCode());
    			startStationObj =  tS;
    		}
    	}
    	for(TrainStations tS2: stations) {
//    		System.out.println("Inside 2");
    		if(tS2.getStationName().equalsIgnoreCase(dest)) {
    			System.out.println("Destination: " + dest+ ", in the list: " + tS2.getStationCode());
    			endStationObj = tS2;
    		}
    	}
    	
    		Ticket ticket = new Ticket(Double.parseDouble(startStationObj.getStationID())
    				, Double.parseDouble(endStationObj.getStationID()), quantity);
    		ticket.calculatePrice();

        	return Response.ok()
        			.entity(ticket)
        			.header("Access-Control-Allow-Origin", "*")
        			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        			.allow("OPTIONS").build();
    }     
    
    }
