<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.ja.learning.hrmsmvc.model.User"%>
    <%@page import="com.ja.learning.hrmsmvc.model.Employee"%>
     <%@page import="com.ja.learning.hrmsmvc.model.Address"%>
      <%@page import="com.ja.learning.hrmsmvc.model.Payroll"%>
      <%@page import="java.util.Optional"%>
       <%@page import="com.ja.learning.hrmsmvc.model.Departments"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
      
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Add Employee</title>
</head>
<body style="background-color: #eee;">
 <% User currentUser = (User)(session.getAttribute("currentSessionUser"));%>
 
  <% List<Departments> deptList = (ArrayList<Departments>)(session.getAttribute("departments")); %>
  <% Employee emp =(Employee)(session.getAttribute("employee")); %>
  <% String firstName;
 
  
 firstName=(emp!=null)?emp.getFirstName():"";
 String lastName=(emp!=null)?emp.getLastName():"";

 String emailID = (emp!=null)?emp.getEmailID():"";
 String officeAddress1 =(emp!=null)?emp.getOfficeAddress().getAddressLine1():"";
String officeAddress2 =(emp!=null)?emp.getOfficeAddress().getAddressLine2():"";
String officeCity=(emp!=null)?emp.getOfficeAddress().getCity():"";
int officePin=(emp!=null)?emp.getOfficeAddress().getPinCode():0;
long officePhone=(emp!=null)?emp.getOfficeAddress().getPhoneNbr():0l;

String homeAddress1 =(emp!=null)?emp.getHomeAddress().getAddressLine1():"";
String homeAddress2 =(emp!=null)?emp.getHomeAddress().getAddressLine2():"";
String homeCity=(emp!=null)?emp.getHomeAddress().getCity():"";
int homePin=(emp!=null)?emp.getHomeAddress().getPinCode():0;
long homePhone=(emp!=null)?emp.getHomeAddress().getPhoneNbr():0l;
 

 
  %>

  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-left align-items-center h-100">
      <div class="col-xl-9">
     
      <h1>Please enter the employee details</h1>
      <br/>
		<form name="addEmpForm" method="post" action="../HRController?oper=add&oper1=save">
		<div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="firstName">First Name</label>
		      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required value=<%= firstName%>>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="lastName">Last Name</label>
		      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
		    </div>
		  </div>
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="email">Email</label>
		      <input type="email" class="form-control" name="email" placeholder="Email" required>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="phoneNbrOffice">Office Phone Number</label>
		      <input type="tel" class="form-control" name="phoneNbrOffice" required placeholder="Phone Number">
		    </div>
		  </div>
		     <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="joinDate">Join Date</label>
		      <input type="date" class="form-control" id="joinDate" required name="joinDate">		      
		    </div>
		     <div class="form-group col-md-2">
		      <label for="designation">Designation</label>
		      <input type="text" class="form-control" id="designation" required name="designation">
		    </div>
		    <div class="form-group col-md-4">
		      <label for="department">Department</label>
		      <select id="department" name="department" required class="form-control" required>
		        <option selected>Choose...</option>
		        <option selected>Choose...</option>
		       <%for(Departments dept:deptList) {%>
		        <option value="<%=dept.getDept_description()%>"><%=dept.getDept_description() %>
		        <%} %>	
		      </select>
		    </div>
		   
		  </div>
		     <div class="form-row">
		      <div class="form-group col-md-3">
		      <label for="netSalary">Salary Net</label>
		      <input type="text" class="form-control" id="netSalary" name="netSalary" required>
		    </div>
		    <div class="form-group col-md-3">
		      <label for="salarybasic">Salary Basic</label>
		      <input type="text" class="form-control" id="salarybasic" name="salarybasic" required>		      
		    </div>
		    <div class="form-group col-md-2">
		       <label for="salaryhra">Salary HRA</label>
		      <input type="text" class="form-control" id="salaryhra" name="salaryhra" required>		 
		    </div>
		    <div class="form-group col-md-2">
		       <label for="salaryta">Salary TA</label>
		      <input type="text" class="form-control" id="salaryta" name="salaryta" required>	
		    </div>
		    <div class="form-group col-md-2">
		       <label for="salaryda">Salary DA</label>
		      <input type="text" class="form-control" id="salaryda" name="salaryda" required>	
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputAddress">Office Address</label>
		    <input type="text" class="form-control" id="officeaddress1"  name="officeaddress1" placeholder="1234 Main St" required value=<%= officeAddress1%>>
		  </div>
		  <div class="form-group">
		    <label for="inputAddress2">Office Address 2</label>
		    <input type="text" class="form-control" id="officeaddress2" name="officeaddress2" placeholder="Apartment, studio, or floor" required value=<%= officeAddress2%>>
		  </div>
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="officeinputCity">Office City</label>
		      <input type="text" class="form-control" id="officeinputCity" required name="officeinputCity" value=<%= officeCity%>>
		    </div>
		    <div class="form-group col-md-4">
		      <label for="inputState">State</label>
		      <select id="inputState" class="form-control">
		      
		      </select>
		    </div>
		    <div class="form-group col-md-2">
		      <label for="inputOfficeZip">Office Pincode</label>
		      <input type="text" class="form-control" id="inputOfficeZip" name="inputOfficeZip" required value=<%=officePin %>>
		    </div>
		  </div>
		  
		
		  
		 
		  <div>
		  
		  </div>
		  
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="homeAddress1">Home Address 1</label>
		      <input type="text" class="form-control" id="homeAddress1" name="homeAddress1" placeholder="Home Address 1" value=<%=homeAddress1 %>>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="homeAddress2">Home Address 2</label>
		      <input type="text" class="form-control" id="homeAddress2" name="homeAddress2" placeholder="Home Address 2" value=<%=homeAddress2 %>>
		    </div>
		  </div>
		  
		   <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="homeCity">Home City</label>
		      <input type="text" class="form-control" id="homeCity" name="homeCity" placeholder="Home City" value=<%=homeCity %>>
		    </div>
		    <div class="form-group col-md-2">
		      <label for="inputHomeZip">Home Pincode</label>
		      <input type="text" class="form-control" id="inputHomeZip" name="inputHomeZip" value=<%=homePin %>>
		    </div>
		  </div>
		  
			
		  <button type="button" class="btn btn-primary">Save</button>
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <button type="reset" class="btn btn-primary">Clear</button>
		   <button type="button" class="btn btn-primary" onclick="alert('Are you sure you want to go back to homepage?')">Back to HR Homepage</button>
		</form>
		</div>
		</div>
		</div>
</body>
</html>