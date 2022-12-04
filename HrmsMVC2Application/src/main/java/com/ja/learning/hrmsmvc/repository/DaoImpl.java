package com.ja.learning.hrmsmvc.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.ja.learning.hrmsmvc.constants.Constants;
import com.ja.learning.hrmsmvc.exception.ErrorCode;
import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Address;
import com.ja.learning.hrmsmvc.model.Departments;
import com.ja.learning.hrmsmvc.model.Employee;
import com.ja.learning.hrmsmvc.model.Payroll;
import com.ja.learning.hrmsmvc.model.User;


public class DaoImpl implements Dao{

	public DaoImpl() {
		
	}

	

	@Override
	public User verifyLoginCredentials(User user) throws HrmsException {
		Connection con=null;	
		User currUser=new User();
		
		try {
			con = Connect.getConnection();	
			
			PreparedStatement pstmt_verifyLogin = con.prepareStatement(Constants.SQL_SELECT_LOGINCREDENTIALS);
		
			pstmt_verifyLogin.setString(1, user.getUserName());
			pstmt_verifyLogin.setString(2, user.getPassword());
		
			ResultSet login_rs = pstmt_verifyLogin.executeQuery();
			System.out.println(pstmt_verifyLogin.toString());
		
			boolean isValid=login_rs.next();
			if(isValid) {				
				currUser.setUserID(login_rs.getLong(1));
				currUser.setUserName(login_rs.getString(2));
				currUser.setPassword(login_rs.getString(3));
				currUser.setEmail(login_rs.getNString(4));
				currUser.setPhone(login_rs.getLong(5));
				currUser.setRoleid(login_rs.getLong(6));
				currUser.setValid(isValid);
			}
					
		}
		catch(SQLException sqle) {
			
			throw new HrmsException(ErrorCode.USER_INVALID);
		}
		catch(ClassNotFoundException cnf) {
			
			throw new HrmsException(ErrorCode.DRIVER_ERROR);
		}
	catch(Exception he){
		
		throw new HrmsException(ErrorCode.USER_NOT_FOUND);
		
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
			
			
		return currUser;
	}



	@Override
	public int deleteEmployee(String employeeID) throws HrmsException {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Employee> getEmployees() throws HrmsException {
		List<Employee> empList = new ArrayList<Employee>();
		Connection con=null;
		
		try {
			con = Connect.getConnection();
			PreparedStatement ptst = con.prepareStatement(Constants.SQL_SELECT_EMPLOYEES,ResultSet.TYPE_SCROLL_INSENSITIVE,
					  ResultSet.CONCUR_UPDATABLE);
			System.out.println(ptst.toString());
			ResultSet rs = ptst.executeQuery();
			if(rs.next()) {
				
				rs.beforeFirst();
				while(rs.next()) {
					
					Employee emp= new Employee();
					Address homeAddr=new Address();
					Address officeAddr=new Address();
					Payroll payroll = new Payroll();
					
					
					emp.setEmpId(rs.getLong(1));				
					emp.setFirstName(rs.getNString(2));
					emp.setLastName(rs.getNString(3));
					emp.setEmailID(Optional.ofNullable(rs.getString(4)).orElse(""));
					emp.setDesignation(rs.getNString(5));	
					emp.setDepartment(rs.getNString(6));
					
					
					Date date =Optional.ofNullable(rs.getDate(7)).orElseGet(() -> new java.sql.Date(0));
					LocalDate joinDate = Instant.ofEpochMilli(date.getTime())
						      .atZone(ZoneId.systemDefault())
						      .toLocalDate();
					
					emp.setJoinDate(joinDate);
					
				
					
					homeAddr.setAddressLine1(Optional.ofNullable(rs.getString(8)).orElse(""));
					homeAddr.setAddressLine2(Optional.ofNullable(rs.getString(9)).orElse(""));
					homeAddr.setCity(Optional.ofNullable(rs.getString(10)).orElse(""));
					homeAddr.setPinCode(Optional.ofNullable(rs.getInt(11)).orElse(0));
					homeAddr.setPhoneNbr(Optional.ofNullable(rs.getLong(12)).orElse(0l));
					emp.setHomeAddress(homeAddr);
					
					
					officeAddr.setAddressLine1(Optional.ofNullable(rs.getString(13)).orElse(""));
					officeAddr.setAddressLine2(Optional.ofNullable(rs.getString(14)).orElse(""));
					officeAddr.setCity(Optional.ofNullable(rs.getString(15)).orElse(""));
					officeAddr.setPinCode(Optional.ofNullable(rs.getInt(16)).orElse(0));
					officeAddr.setPhoneNbr(Optional.ofNullable(rs.getLong(17)).orElse(0l));
					
					emp.setOfficeAddress(officeAddr);
								
				
					payroll.setSalary_net(Optional.ofNullable(rs.getDouble(18)).orElse(0.0));
					payroll.setSalary_basic(Optional.ofNullable(rs.getDouble(19)).orElse(0.0));
					payroll.setSalary_hra(Optional.ofNullable(rs.getDouble(20)).orElse(0.0));
					payroll.setSalary_ta(Optional.ofNullable(rs.getDouble(21)).orElse(0.0));
					payroll.setSalary_da(Optional.ofNullable(rs.getDouble(22)).orElse(0.0));
					emp.setSalary(payroll.getSalary_net());
					emp.setPayrollInfo(payroll);
					
					empList.add(emp);
					
					}		
			}
			else
				throw new HrmsException(ErrorCode.NO_EMPLOYEE_RECORDS);
			
		}
		
		catch(SQLException sqle) {
			
			throw new HrmsException(ErrorCode.DB_ERROR);
		}
		catch(ClassNotFoundException cnf) {
			
			throw new HrmsException(ErrorCode.DRIVER_ERROR);
		}
		catch(Exception he){
			
			throw new HrmsException(ErrorCode.NO_EMPLOYEE_RECORDS);
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return empList;
	}



	@Override
	public int addEmployee(Employee emp) throws HrmsException {
	Connection con=null;
		
		int status=0;
		
		try {
			con = Connect.getConnection();			
		
			
			PreparedStatement ptst_addEmployee = con.prepareStatement(Constants.SQL_ADD_EMPLOYEE);			
			
			//fname,lname,email,designation,department,salary,joindate
			ptst_addEmployee.setString(1, emp.getFirstName());
			ptst_addEmployee.setString(2, emp.getLastName());
			ptst_addEmployee.setString(3, emp.getEmailID());
			ptst_addEmployee.setString(4, emp.getDesignation());
			ptst_addEmployee.setString(5, emp.getDepartment());
			ptst_addEmployee.setDouble(6, emp.getSalary());
			
			Date dt = Date.valueOf(emp.getJoinDate());
			
			ptst_addEmployee.setDate(7, dt);
			ptst_addEmployee.setLong(8, emp.getOfficeAddress().getPhoneNbr());
			
			PreparedStatement p1 = con.prepareStatement(Constants.SQL_SELECT_LASTID);
			
			
			
			status = ptst_addEmployee.executeUpdate();
			
			ResultSet rs1 = p1.executeQuery();
			while(rs1.next()) {
				
				emp.setEmpId(rs1.getLong(1));
				
				System.out.println(emp.getEmpId());
			}
			
			System.out.print(ptst_addEmployee.toString());

			//insert into address(homeaddress1,homeaddress2,homecity,homepincode,officeaddress1,officeaddress2,officecity,officepincode,officephone,id) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ptst_addAddress =con.prepareStatement(Constants.SQL_ADD_ADDRESS);
			
			
			ptst_addAddress.setString(1, emp.getHomeAddress().getAddressLine1());
			ptst_addAddress.setString(2, emp.getHomeAddress().getAddressLine2());			
			ptst_addAddress.setString(3, emp.getHomeAddress().getCity());
			ptst_addAddress.setInt(4, emp.getHomeAddress().getPinCode());
			ptst_addAddress.setLong(5, emp.getHomeAddress().getPhoneNbr());
			ptst_addAddress.setString(6, emp.getOfficeAddress().getAddressLine1());
			ptst_addAddress.setString(7, emp.getOfficeAddress().getAddressLine2());			
			ptst_addAddress.setString(8, emp.getOfficeAddress().getCity());
			ptst_addAddress.setInt(9, emp.getOfficeAddress().getPinCode());
			ptst_addAddress.setLong(10, emp.getOfficeAddress().getPhoneNbr());
			
			PreparedStatement ptstAddPayroll = con.prepareStatement(Constants.SQL_ADD_PAYROLL);			
	
			ptstAddPayroll.setDouble(1,Double.valueOf(emp.getPayrollInfo().getSalary_net()));
			ptstAddPayroll.setDouble(2,Double.valueOf(emp.getPayrollInfo().getSalary_basic()));
			ptstAddPayroll.setDouble(3,Double.valueOf(emp.getPayrollInfo().getSalary_hra()) );
			ptstAddPayroll.setDouble(4,Double.valueOf(emp.getPayrollInfo().getSalary_da()));
			ptstAddPayroll.setDouble(5, Double.valueOf(emp.getPayrollInfo().getSalary_ta()));
			ptstAddPayroll.setDouble(6, emp.getEmpId());
			
			System.out.println(ptst_addEmployee.toString());
			
			System.out.println(ptst_addAddress.toString());
			System.out.println(ptstAddPayroll.toString());
			
			status=ptst_addAddress.executeUpdate();
			status=ptstAddPayroll.executeUpdate();
			  
			 
		         if(status > 0) {
		            System.out.println("Record is inserted successfully !!!");
		         }
			
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			throw new HrmsException(e,ErrorCode.DUPLICATE_EMPLOYEE);
		}
		catch(ClassNotFoundException e) {
			throw new HrmsException(e.getMessage());
		}
		catch(Exception he){
			throw new HrmsException(he,ErrorCode.DUPLICATE_EMPLOYEE);
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public static void main(String[] args) {
		Employee emp = new Employee();
		Address homeAddress = new Address();
		Address officeAddress = new Address();
		Payroll payroll  = new Payroll();
		
		emp.setFirstName("Mai");
		emp.setLastName("P");
		emp.setEmailID("mai.p@gmail.com");
		emp.setDesignation("Manager");
		emp.setDepartment("Admin");
		//LocalDate ld = new LocalDate(2022,2,12);
		emp.setJoinDate(LocalDate.parse("2022-02-02"));
		homeAddress.setAddressLine1("Address1");
		homeAddress.setAddressLine2("Address 2");
		homeAddress.setCity("Mumbai");
		homeAddress.setPinCode(45678);
		homeAddress.setPhoneNbr(1234567);
		officeAddress.setAddressLine1("Address1");
		officeAddress.setAddressLine2("Address 2");
		officeAddress.setCity("Mumbai");
		officeAddress.setPinCode(45678);
		officeAddress.setPhoneNbr(1234567);
		
		payroll.setSalary_net(560099.0);
		payroll.setSalary_basic(67885.0);
		payroll.setSalary_hra(2345.0);
		payroll.setSalary_da(4567);
		payroll.setSalary_ta(345);
		
		emp.setHomeAddress(homeAddress);
		emp.setOfficeAddress(officeAddress);
		emp.setPayrollInfo(payroll);
		
		System.out.println(emp);
		
		DaoImpl dao1 = new DaoImpl();
		try {
			dao1.addEmployee(emp);
		} catch (HrmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public Employee getEmployeeDetails(long employeeID) throws HrmsException {
		Employee emp=new Employee();
		
		Connection con=null;		
		
		try {
			con = Connect.getConnection();			
			PreparedStatement ptst = con.prepareStatement(Constants.SQL_SELECT_WITHEMPID,ResultSet.TYPE_SCROLL_INSENSITIVE,
					  ResultSet.CONCUR_UPDATABLE);
			ptst.setLong(1, employeeID);
			
			System.out.println(ptst.toString());
			ResultSet rs = ptst.executeQuery();
			
			if(rs.next()) {
				rs.beforeFirst();
				
				while(rs.next()) {				
					
					emp.setEmpId(employeeID);
					Address homeAddr=new Address();
					Address officeAddr=new Address();
					Payroll payroll = new Payroll();
					
					
					emp.setEmpId(rs.getLong(1));				
					emp.setFirstName(rs.getNString(2));
					emp.setLastName(rs.getNString(3));
					emp.setEmailID(Optional.ofNullable(rs.getString(4)).orElse(""));
					emp.setDesignation(rs.getNString(5));	
					emp.setDepartment(rs.getNString(6));
					
					
					Date date =Optional.ofNullable(rs.getDate(7)).orElseGet(() -> new java.sql.Date(0));
					LocalDate joinDate = Instant.ofEpochMilli(date.getTime())
						      .atZone(ZoneId.systemDefault())
						      .toLocalDate();
					
					emp.setJoinDate(joinDate);
					
				
					
					homeAddr.setAddressLine1(Optional.ofNullable(rs.getString(8)).orElse(""));
					homeAddr.setAddressLine2(Optional.ofNullable(rs.getString(9)).orElse(""));
					homeAddr.setCity(Optional.ofNullable(rs.getString(10)).orElse(""));
					homeAddr.setPinCode(Optional.ofNullable(rs.getInt(11)).orElse(0));
					homeAddr.setPhoneNbr(Optional.ofNullable(rs.getLong(12)).orElse(0l));
					emp.setHomeAddress(homeAddr);
					
					
					officeAddr.setAddressLine1(Optional.ofNullable(rs.getString(13)).orElse(""));
					officeAddr.setAddressLine2(Optional.ofNullable(rs.getString(14)).orElse(""));
					officeAddr.setCity(Optional.ofNullable(rs.getString(15)).orElse(""));
					officeAddr.setPinCode(Optional.ofNullable(rs.getInt(16)).orElse(0));
					officeAddr.setPhoneNbr(Optional.ofNullable(rs.getLong(17)).orElse(0l));
					
					emp.setOfficeAddress(officeAddr);
								
					
					payroll.setSalary_net(Optional.ofNullable(rs.getDouble(18)).orElse(0.0));
					payroll.setSalary_basic(Optional.ofNullable(rs.getDouble(19)).orElse(0.0));
					payroll.setSalary_hra(Optional.ofNullable(rs.getDouble(20)).orElse(0.0));
					payroll.setSalary_ta(Optional.ofNullable(rs.getDouble(21)).orElse(0.0));
					payroll.setSalary_da(Optional.ofNullable(rs.getDouble(22)).orElse(0.0));
					emp.setSalary(payroll.getSalary_net());
					
					//System.out.println(payroll);
					emp.setPayrollInfo(payroll);
			}	
				
			}
			else
				throw new HrmsException(ErrorCode.EMPLOYEE_NOT_FOUND);					
			
		}
		
		catch(SQLException sqle) {
			
			throw new HrmsException(ErrorCode.DB_ERROR);
		}
		catch(ClassNotFoundException cnf) {
			
			throw new HrmsException(ErrorCode.DRIVER_ERROR);
		}
		catch(Exception he){
			//he.printStackTrace();
			throw new HrmsException(he,ErrorCode.EMPLOYEE_NOT_FOUND);
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return emp;
	}



	@Override
	public List<Departments> getDepartments() throws HrmsException {
		List<Departments>  deptList = new ArrayList<Departments>();
		Connection con=null;		
		
		try {
			con = Connect.getConnection();
		
			PreparedStatement pstmt_getDepts = con.prepareStatement(Constants.SQL_SELECT_DEPARTMENTS,ResultSet.TYPE_SCROLL_INSENSITIVE,
					  ResultSet.CONCUR_UPDATABLE);
		
			System.out.println(pstmt_getDepts.toString());
			ResultSet dept_rs = pstmt_getDepts.executeQuery();
			
			if(dept_rs.next()) {
				dept_rs.beforeFirst();
				while(dept_rs.next()) {
					Departments dept = new Departments();
					long deptID=dept_rs.getLong(1);
					String deptDescription=dept_rs.getString(2);
					dept.setDept_description(deptDescription);
					dept.setDept_id(deptID);
					deptList.add(dept);
					
				}
			}
			else
				throw new HrmsException(ErrorCode.NO_DEPARTMENTS_FOUND);
		
		}
		catch(SQLException sqle) {	
			//sqle.printStackTrace();
			throw new HrmsException(ErrorCode.DB_ERROR);
		}
		catch(ClassNotFoundException cnf) {			
			throw new HrmsException(ErrorCode.DRIVER_ERROR);
		}
		catch(Exception he){	
			//he.printStackTrace();
			throw new HrmsException(ErrorCode.NO_DEPARTMENTS_FOUND);
		
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return deptList;
	}



	@Override
	public List<Employee> getEmployeesForDept(String department) throws HrmsException {
		List<Employee> empList = new ArrayList<Employee>();
		Connection con=null;
		
		try {
			con = Connect.getConnection();
			PreparedStatement ptst = con.prepareStatement(Constants.SQL_SELECT_WITHDEPT,ResultSet.TYPE_SCROLL_INSENSITIVE,
					  ResultSet.CONCUR_UPDATABLE);
			
			ptst.setString(1, department);
			System.out.println(ptst.toString());
			ResultSet rs = ptst.executeQuery();
			if(rs.next()) {
				
				rs.beforeFirst();
				while(rs.next()) {
					
					Employee emp= new Employee();
					Address homeAddr=new Address();
					Address officeAddr=new Address();
					Payroll payroll = new Payroll();
					
					
					emp.setEmpId(rs.getLong(1));				
					emp.setFirstName(rs.getNString(2));
					emp.setLastName(rs.getNString(3));
					emp.setEmailID(Optional.ofNullable(rs.getString(4)).orElse(""));
					emp.setDesignation(rs.getNString(5));	
					emp.setDepartment(rs.getNString(6));
					
					
					Date date =Optional.ofNullable(rs.getDate(7)).orElseGet(() -> new java.sql.Date(0));
					LocalDate joinDate = Instant.ofEpochMilli(date.getTime())
						      .atZone(ZoneId.systemDefault())
						      .toLocalDate();
					
					emp.setJoinDate(joinDate);
					
				
					
					homeAddr.setAddressLine1(Optional.ofNullable(rs.getString(8)).orElse(""));
					homeAddr.setAddressLine2(Optional.ofNullable(rs.getString(9)).orElse(""));
					homeAddr.setCity(Optional.ofNullable(rs.getString(10)).orElse(""));
					homeAddr.setPinCode(Optional.ofNullable(rs.getInt(11)).orElse(0));
					homeAddr.setPhoneNbr(Optional.ofNullable(rs.getLong(12)).orElse(0l));
					emp.setHomeAddress(homeAddr);
					
					
					officeAddr.setAddressLine1(Optional.ofNullable(rs.getString(13)).orElse(""));
					officeAddr.setAddressLine2(Optional.ofNullable(rs.getString(14)).orElse(""));
					officeAddr.setCity(Optional.ofNullable(rs.getString(15)).orElse(""));
					officeAddr.setPinCode(Optional.ofNullable(rs.getInt(16)).orElse(0));
					officeAddr.setPhoneNbr(Optional.ofNullable(rs.getLong(17)).orElse(0l));
					
					emp.setOfficeAddress(officeAddr);
								
				
					payroll.setSalary_net(Optional.ofNullable(rs.getDouble(18)).orElse(0.0));
					payroll.setSalary_basic(Optional.ofNullable(rs.getDouble(19)).orElse(0.0));
					payroll.setSalary_hra(Optional.ofNullable(rs.getDouble(20)).orElse(0.0));
					payroll.setSalary_ta(Optional.ofNullable(rs.getDouble(21)).orElse(0.0));
					payroll.setSalary_da(Optional.ofNullable(rs.getDouble(22)).orElse(0.0));
					emp.setSalary(payroll.getSalary_net());
					emp.setPayrollInfo(payroll);
					empList.add(emp);
					
					}		
			}
			else
				throw new HrmsException(ErrorCode.NO_EMPLOYEE_RECORDS);
			
		}
		
		catch(SQLException sqle) {
			
			throw new HrmsException(ErrorCode.DB_ERROR);
		}
		catch(ClassNotFoundException cnf) {
			
			throw new HrmsException(ErrorCode.DRIVER_ERROR);
		}
		catch(Exception he){
			he.printStackTrace();
			throw new HrmsException(ErrorCode.NO_EMPLOYEE_RECORDS);
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return empList;
	}

}
