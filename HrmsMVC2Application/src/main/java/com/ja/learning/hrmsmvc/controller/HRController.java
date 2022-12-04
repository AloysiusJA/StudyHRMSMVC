package com.ja.learning.hrmsmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ja.learning.hrmsmvc.exception.HrmsException;
import com.ja.learning.hrmsmvc.model.Address;

import com.ja.learning.hrmsmvc.model.Employee;
import com.ja.learning.hrmsmvc.model.Payroll;
import com.ja.learning.hrmsmvc.model.User;
import com.ja.learning.hrmsmvc.service.HRService;
import com.ja.learning.hrmsmvc.service.HRServiceImpl;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class HRController
 */
public class HRController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HRController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession(true);	
		
		HRService hrservice = new HRServiceImpl();
		int status=1;
		
		PrintWriter pw = response.getWriter();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		String oper1=request.getParameter("oper1");

		String oper=request.getParameter("oper");
		
		
		List<Employee> empList = new ArrayList<Employee>();
		
		Employee employee= new Employee();
		Address officeAddress = new Address();
		Address homeAddress = new Address();
		Payroll payroll = new Payroll();
		
		System.out.println(oper);
		
		if(oper.equals("displayAll")) {
			try {
				empList=hrservice.getEmployees();
				session.setAttribute("employeeList", empList);
				request.getRequestDispatcher("./jsp/displayAllEmployees.jsp").forward(request, response);
			} catch (HrmsException e) {
				pw.println(e.getErrorCode());
				e.printStackTrace();
			}		
			
		}
		
		else if(oper.equals("displayOneEmp")) {
			long empID = Long.parseLong(request.getParameter("empID"));
			//System.out.println(empID);
			try {
				employee = hrservice.getEmployeeDetails(empID);
				System.out.println(employee);
				session.setAttribute("employee", employee);
				request.getRequestDispatcher("./jsp/displayEmployee.jsp").forward(request, response);
			} catch (HrmsException e) {
				pw.println(e.getErrorCode());
				e.printStackTrace();
			}
		}
		
		else if(oper.equals("dispDeptWise")) {
		
			String selDepartment = request.getParameter("department");
			System.out.println(selDepartment);
			
			try {
				empList=hrservice.getEmployeesForDept(selDepartment);
				//empList.forEach(System.out::println);
				session.setAttribute("employeeList", empList);
				request.getRequestDispatcher("./jsp/displayDeptWise.jsp").forward(request, response);
			} catch (HrmsException e) {
				pw.println(e.getErrorCode());
				e.printStackTrace();
			}
			
			
		}
		
		else if(oper.equals("genpayslip")) {
			long empID = Long.parseLong(request.getParameter("empID"));			
			try {
				employee = hrservice.getEmployeeDetails(empID);
				session.setAttribute("employee", employee);
				request.getRequestDispatcher("./jsp/payslip.jsp").forward(request, response);
			} catch (HrmsException e) {	
				pw.println(e.getErrorCode());
				e.printStackTrace();
			}
		}
		
		else if(oper.equals("backToHome")) {
			request.getRequestDispatcher("./jsp/hrHomepage.jsp").forward(request, response);
		}
		
		else if(oper.equals("add")){
			
			System.out.println("Here");
			
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmailID(request.getParameter("email"));
			employee.setDepartment(request.getParameter("department"));
			employee.setDesignation(request.getParameter("designation"));
			LocalDate dt = LocalDate.parse(request.getParameter("joinDate"));
			employee.setJoinDate(dt);
			
			String netSal=request.getParameter("salarynet");
			double netD=(netSal!=null)?Double.parseDouble(netSal):0.0;
			String basicSal = request.getParameter("salarybasic");
			double basicD = (basicSal!=null)?Double.parseDouble(basicSal):0.0;
			String hraSal = request.getParameter("salaryhra");
			double hraD = (hraSal!=null)?Double.parseDouble(hraSal):0.0;
			String daSal = request.getParameter("salaryda");
			double daD = (daSal!=null)?Double.parseDouble(daSal):0.0;
			String taSal = request.getParameter("salaryta");
			double taD = (taSal!=null)?Double.parseDouble(taSal):0.0;
			payroll.setSalary_net(netD);
			payroll.setSalary_basic(basicD);
			payroll.setSalary_hra(hraD);
			payroll.setSalary_ta(taD);
			payroll.setSalary_da(daD);
			employee.setSalary(netD);
			employee.setPayrollInfo(payroll);
			
			officeAddress.setAddressLine1(request.getParameter("officeaddress1"));
			officeAddress.setAddressLine2(request.getParameter("officeaddress2"));
			officeAddress.setCity(request.getParameter("officeinputCity"));
			
			String officeNbr = request.getParameter("phoneNbrOffice");
			long offNbr = Long.parseLong(officeNbr);
			officeAddress.setPhoneNbr(offNbr);
			String zipOfficeCode = request.getParameter("inputOfficeZip");
			int zipOffice = Integer.parseInt(zipOfficeCode);
			officeAddress.setPinCode(zipOffice);
			
			homeAddress.setAddressLine1(request.getParameter("homeAddress1"));
			homeAddress.setAddressLine2(request.getParameter("homeAddress2"));
			homeAddress.setCity(request.getParameter("homeCity"));
			
			String zipHomeCode = request.getParameter("inputHomeZip");
			int zipHome = Integer.parseInt(zipOfficeCode);
			officeAddress.setPinCode(zipHome);
			
			employee.setOfficeAddress(officeAddress);
			employee.setHomeAddress(homeAddress);
			
			
			System.out.println("again");
			try {
				System.out.println("again123");
				status = hrservice.addEmployee(employee);
				System.out.println(status);
				session.setAttribute("employee", employee);
				request.getRequestDispatcher("./jsp/displayEmployee.jsp").forward(request, response);
				
			} catch (HrmsException e) {
				pw.println(e.getErrorCode());
				e.printStackTrace();
			}
			
			
		}
		else if(oper1.equals("save")) {		    
	          session.setAttribute("employee",employee); 
			request.getRequestDispatcher("./jsp/addemployee.jsp").forward(request, response);
	}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
