package com.trainservice.train;

//government employee entity
public class Employee {
	private String _id;
	private String employeeID;
	private String employeeName;
	private String employeeNIC;
	public Employee(String _id,String employeeID, String employeeName, String employeeNIC) {
		super();
		this._id = _id;
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeNIC = employeeNIC;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNIC() {
		return employeeNIC;
	}
	public void setEmployeeNIC(String employeeNIC) {
		this.employeeNIC = employeeNIC;
	}
	
	
}
