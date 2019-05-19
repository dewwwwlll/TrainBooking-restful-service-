package com.trainservice.mongodb.queries;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.trainservice.train.Employee;
import com.trainservice.train.TrainStations;

public class EmployeeDetailsOperations {

	MongoClient mongoClient;
	private ArrayList<Employee> employeeObject = new ArrayList<>();
	
	public EmployeeDetailsOperations() throws UnknownHostException {
		
		mongoClient =new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	}
	
	//getting all the employees from the mongoDB
	public  List<Employee> getAllEmployees() {
		try {		
			
			DB employee = mongoClient.getDB("employee");
			DBCollection employees = employee.getCollection("employee_details");
		
			DBCursor employeeCursor = employees.find();
		
			while(employeeCursor.hasNext()) {
				
				BasicDBObject stationObj = (BasicDBObject) employeeCursor.next();
				String _id = stationObj.getString("_id");
				String employeeID = stationObj.getString("employeeID");
				String employeeName = stationObj.getString("employeeName");
				String employeeNIC = stationObj.getString("employeeNIC");
				
				employeeObject.add(new Employee
						(_id, employeeID, employeeName, employeeNIC));
				
			}	
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return employeeObject;
	}	

}
