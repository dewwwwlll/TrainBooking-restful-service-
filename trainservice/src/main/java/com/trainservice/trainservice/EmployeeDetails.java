package com.trainservice.trainservice;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trainservice.mongodb.queries.EmployeeDetailsOperations;
import com.trainservice.train.DiscountCalculator;
import com.trainservice.train.DiscountDetails;
import com.trainservice.train.Employee;

@Path("employeedetails")
public class EmployeeDetails {
	
	EmployeeDetailsOperations employeeOperations;
	List <Employee> employee;
	
	//getting all the employees to the employee List
	public EmployeeDetails() throws UnknownHostException {
		employeeOperations = new EmployeeDetailsOperations();
    	employee = employeeOperations.getAllEmployees();
	}

	//returning all the employees
 	@Path("/employees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscount() throws UnknownHostException {
    	    	
    	return Response.ok()
    			.entity(employee)
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.allow("OPTIONS").build();
    }   
    
 	//checking the employee status from the database and returning a discount if the employee is a government employee
    @Path("/employeediscount")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public DiscountCalculator getDiscount(DiscountDetails discountDetails, @Context HttpServletResponse response, @Context HttpServletRequest request) {
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    
    	
    	String nic = discountDetails.getnIC();
    	double amount = discountDetails.getAmount();
    	int percentage = discountDetails.getDiscountPercentage();
    	DiscountCalculator discountCal = new DiscountCalculator();
    	discountCal = new DiscountCalculator(amount, percentage);
    	for(Employee e : employee) {
    		if(e.getEmployeeNIC().equalsIgnoreCase(nic)) {
    			
    			discountCal.calculateDiscount();
    			System.out.println("hello");
    			System.out.println(amount);
    			System.out.println(percentage);
    			System.out.println(discountCal.getTotal());
    			return discountCal;
    			
    		}
    	}
    	
    	return discountCal;
    }
    
    @Path("/employeediscount")
    @OPTIONS
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeResponse(@Context HttpServletRequest request) {
    	return Response.ok()
    			.header("Access-Control-Allow-Origin", request.getHeader("Origin"))
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.header("Access-Control-Allow-Headers", "content-type")
    			.build();
    }
	
}
