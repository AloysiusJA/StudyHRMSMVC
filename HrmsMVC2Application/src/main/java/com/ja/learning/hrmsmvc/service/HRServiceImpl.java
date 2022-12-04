package com.ja.learning.hrmsmvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ja.learning.hrmsmvc.exception.ErrorCode;
import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Departments;
import com.ja.learning.hrmsmvc.model.Employee;
import com.ja.learning.hrmsmvc.repository.Dao;
import com.ja.learning.hrmsmvc.repository.DaoImpl;


public class HRServiceImpl implements HRService{
	Dao dao = new DaoImpl();
	public HRServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int deleteEmployee(String employeeID) throws HrmsException {
		 Optional<String> op = Optional.ofNullable(employeeID);
		 if(op.isEmpty()) {
			 String errorTest = "Employee ID "+ErrorCode.CANNOT_BE_NULL;
			 throw new HrmsException(errorTest);
		 }
			 
		
		return dao.deleteEmployee(employeeID);
	}

	@Override
	public List<Employee> getEmployees() throws HrmsException {
		return dao.getEmployees();
	}

	@Override
	public int addEmployee(Employee emp) throws HrmsException {	
		
				
			return dao.addEmployee(emp);
	}
	@Override
	public Employee getEmployeeDetails(long employeeID) throws HrmsException {
		return dao.getEmployeeDetails(employeeID);
	}
	@Override
	public List<Departments> getDepartments() throws HrmsException {
	return dao.getDepartments();
	}
	public List<Employee> getEmployeesForDept(String department) throws HrmsException{
		return dao.getEmployeesForDept(department);
	}

}
