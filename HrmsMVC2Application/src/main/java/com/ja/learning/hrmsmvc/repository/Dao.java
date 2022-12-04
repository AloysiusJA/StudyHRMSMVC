package com.ja.learning.hrmsmvc.repository;

import java.util.List;

import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Departments;
import com.ja.learning.hrmsmvc.model.Employee;
import com.ja.learning.hrmsmvc.model.User;

public interface Dao {
	public User verifyLoginCredentials(User user) throws HrmsException;
	
	public int deleteEmployee(String employeeID) throws HrmsException;	
	public List<Employee> getEmployees() throws HrmsException;	
	public int addEmployee(Employee emp) throws HrmsException;
	
	public Employee getEmployeeDetails(long employeeID) throws HrmsException;

	public List<Departments> getDepartments() throws HrmsException;

	public List<Employee> getEmployeesForDept(String department) throws HrmsException;
}
