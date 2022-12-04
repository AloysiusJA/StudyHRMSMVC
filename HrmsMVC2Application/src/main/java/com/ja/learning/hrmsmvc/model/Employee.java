/**
 * 
 */
package com.ja.learning.hrmsmvc.model;

import java.time.LocalDate;
import java.util.Date;



/**
 * @author Jiby
 *
 */
public class Employee {

	

	

	/**
	 * 
	 */
	
	
	private long empId;	
	
	private String firstName;
	private String lastName;
	private String emailID;
	private String designation;
	private String department;
	private double salary;
	private LocalDate joinDate;
	private Address homeAddress;
	private Address officeAddress;	
	private Payroll payrollInfo;
	private int addr_id;
	
	
	/**
	 * No argument constructor
	 */
	public Employee() {
		
	}


	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailID() {
		return emailID;
	}


	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	public LocalDate getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}


	public Address getHomeAddress() {
		return homeAddress;
	}


	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}


	public Address getOfficeAddress() {
		return officeAddress;
	}


	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}


	public Payroll getPayrollInfo() {
		return payrollInfo;
	}


	public void setPayrollInfo(Payroll payrollInfo) {
		this.payrollInfo = payrollInfo;
	}


	public int getAddr_id() {
		return addr_id;
	}


	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailID="
				+ emailID + ", designation=" + designation + ", department=" + department + ", joinDate=" + joinDate
				+ ", homeAddress=" + homeAddress + ", officeAddress=" + officeAddress + ", payrollInfo=" + payrollInfo
				 + "]";
	}


	public Employee(long empId, String firstName, String lastName, String emailID, String designation,
			String department, double salary, LocalDate joinDate, Address homeAddress, Address officeAddress,
			Payroll payrollInfo, int addr_id) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.designation = designation;
		this.department = department;
		this.salary = salary;
		this.joinDate = joinDate;
		this.homeAddress = homeAddress;
		this.officeAddress = officeAddress;
		this.payrollInfo = payrollInfo;
		this.addr_id = addr_id;
	}



	
	
	

}
