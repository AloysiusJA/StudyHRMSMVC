<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.ja.learning.hrmsmvc.model.User"%>
    <%@page import="com.ja.learning.hrmsmvc.model.Employee"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Display Employee</title>
</head>
<body style="background-color: #eee;">
 <% User currentUser = (User)(session.getAttribute("currentSessionUser"));%>
 
  
  <% Employee emp =(Employee)(session.getAttribute("employee")); %>
  <% String firstName;
  
 firstName=(emp!=null)?emp.getFirstName():"";
 
 String lastName=(emp!=null)?emp.getLastName():"";


  %>

  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-left align-items-center h-100">
      <div class="col-xl-9">
     
      <h1>Employee details for Employee ID <%=emp.getEmpId() %></h1>
      <br/>
		<form name="dispEmpForm" method="post" action="../HRController?">
		<div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="firstName">First Name</label>
		      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value=<%= firstName%> required="true">
		    </div>
		    <div class="form-group col-md-6">
		      <label for="lastName">Last Name</label>
		      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required="true" value=<%=lastName %>>
		    </div>
		  </div>
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="email">Email</label>
		      <input type="email" class="form-control" name="email" placeholder="Email" value=<%=emp.getEmailID() %> required>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="phoneNbrOffice">Office Phone Number</label>
		      <input type="tel" class="form-control" name="phoneNbrOffice" required placeholder="Phone Number" value=<%=emp.getOfficeAddress().getPhoneNbr() %>>
		    </div>
		  </div>
		     <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="joinDate">Join Date</label>
		      <input type="date" class="form-control" id="joinDate" required name="joinDate" value=<%=emp.getJoinDate() %>>		      
		    </div>
		     <div class="form-group col-md-2">
		      <label for="designation">Designation</label>
		      <input type="text" class="form-control" id="designation" required name="designation" value=<%=emp.getDesignation() %>>
		    </div>
		    <div class="form-group col-md-4">
		      <label for="department">Department</label>
		      <select id="department" name="department" required class="form-control" value=<%=emp.getDepartment() %>>
		        <option selected>Choose...</option>
		        <option>HR</option>
		        <option>Research and Development</option>
		         <option>Projects</option>
		      </select>
		    </div>
		   
		  </div>
		     <div class="form-row">
		      <div class="form-group col-md-3">
		      <label for="netSalary">Salary Net</label>
		      <input type="text" class="form-control" id="netSalary" required name="netSalary" required <%=emp.getPayrollInfo().getSalary_net() %>>
		    </div>
		    <div class="form-group col-md-3">
		      <label for="salarybasic">Salary Basic</label>
		      <input type="text" class="form-control" id="salarybasic" name="salarybasic" <%=emp.getPayrollInfo().getSalary_basic() %>>		      
		    </div>
		    <div class="form-group col-md-2">
		       <label for="salaryhra">Salary HRA</label>
		      <input type="text" class="form-control" id="salaryhra" name="salaryhra" <%=emp.getPayrollInfo().getSalary_hra() %>>		 
		    </div>
		    <div class="form-group col-md-2">
		       <label for="salaryta">Salary TA</label>
		      <input type="text" class="form-control" id="salaryta" name="salaryta" <%=emp.getPayrollInfo().getSalary_ta() %>>	
		    </div>
		    <div class="form-group col-md-2">
		       <label for="salaryda">Salary DA</label>
		      <input type="text" class="form-control" id="salaryda" name="salaryda" <%=emp.getPayrollInfo().getSalary_ta() %>>	
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputAddress">Office Address</label>
		    <input type="text" class="form-control" id="inputAddress"  name="inputAddress" placeholder="1234 Main St" <%=emp.getOfficeAddress().getAddressLine1() %>>
		  </div>
		  <div class="form-group">
		    <label for="inputAddress2">Office Address 2</label>
		    <input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" <%=emp.getOfficeAddress().getAddressLine2() %>>
		  </div>
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="officeinputCity">Office City</label>
		      <input type="text" class="form-control" id="officeinputCity" <%=emp.getOfficeAddress().getCity() %>>
		    </div>
		    <div class="form-group col-md-4">
		      <label for="inputState">State</label>
		      <select id="inputState" class="form-control">
		        <option selected>Choose...</option>
		        <option>...</option>
		      </select>
		    </div>
		    <div class="form-group col-md-2">
		      <label for="inputOfficeZip">Office Pincode</label>
		      <input type="text" class="form-control" id="inputOfficeZip" <%=emp.getOfficeAddress().getPinCode() %>>
		    </div>
		  </div>
		  
		
		  
		 
		  <div>
		  
		  </div>
		  
		  <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="homeAddress1">Home Address 1</label>
		      <input type="text" class="form-control" id="homeAddress1" placeholder="Home Address 1" value=<%= emp.getHomeAddress().getAddressLine1()%>>
		    </div>
		    <div class="form-group col-md-6">
		      <label for="homeAddress2">Home Address 2</label>
		      <input type="text" class="form-control" id="homeAddress2" placeholder="Home Address 2" value=<%= emp.getHomeAddress().getAddressLine2()%>>
		    </div>
		  </div>
		  
		   <div class="form-row">
		    <div class="form-group col-md-6">
		      <label for="homeCity">Home City</label>
		      <input type="text" class="form-control" id="homeCity" placeholder="Home City" value=<%= emp.getHomeAddress().getCity()%>>
		    </div>
		    <div class="form-group col-md-2">
		      <label for="inputHomeZip">Home Pincode</label>
		      <input type="text" class="form-control" id="inputHomeZip" value=<%= emp.getHomeAddress().getPinCode()%>>
		    </div>
		  </div>
		  
			</form> 
			<div>
			 <button type="submit" class="btn btn-primary">Edit Details</button>	
		 
         		
      			 <div class="panel-heading">  
      			       <form name="backToHome" method="post" action="./HRController?oper=backToHome">  
       				 		<button type="submit" class="btn btn-primary">Back to Home page</button>	
       				 	</form>
			</div>
		  
		   
		
		</div>
		</div>
		</div>
</body>
</html>