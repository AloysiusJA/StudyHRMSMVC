<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@page import="com.ja.learning.hrmsmvc.model.User"%>
    <%@page import="com.ja.learning.hrmsmvc.model.Employee"%>
    
     
    
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Pay Slip</title>
</head>
<body>
<% User currentUser = (User)(session.getAttribute("currentSessionUser"));%>
 
  
  <% Employee emp =(Employee)(session.getAttribute("employee")); %>
  <% String firstName;
  
 firstName=(emp!=null)?emp.getFirstName():"";
  %>

      <div class="container">
  <h2>Payslip for Employee ID <%=emp.getEmpId() %></h2>
  <p></p>            
  <table class="table">
   
    <tbody>
      <tr>
        <td><b>Name : </b><%= firstName%> <%=emp.getLastName() %></td>
        <td></td>
        <td><b>Email : </b><%=emp.getEmailID() %></td>
        <td></td>
        <td><b>Phone Number :</b> <%=emp.getOfficeAddress().getPhoneNbr() %></td>
        <td></td>
         <td><b>Designation:</b> <%=emp.getDesignation() %></td>
         <td></td>
          <td></td>
      </tr>
      <tr>
      
      </tr>
      <tr>
      <td><b>Salary Net : </b><%=emp.getPayrollInfo().getSalary_net() %></td>
        <td></td>
        <td><b>Salary Basic: </b><%=emp.getPayrollInfo().getSalary_basic() %></td>
        <td></td>
        <td><b>Salary HRA:</b> <%=emp.getPayrollInfo().getSalary_hra() %></td>
        <td></td>
         <td><b>Salary TA:</b><%=emp.getPayrollInfo().getSalary_ta()%></td>
          <td></td>
         <td><b>Salary DA:</b><%=emp.getPayrollInfo().getSalary_da()%></td>
      </tr>
      
         <tr>
      <td><b>Address : </b><%=emp.getOfficeAddress().getAddressLine1()%></td>
        <td></td>
        <td><%=emp.getOfficeAddress().getAddressLine2()%></td>
        <td></td>
        <td><%=emp.getOfficeAddress().getCity()%></td>
        <td></td>
         <td><%=emp.getOfficeAddress().getPinCode()%></td>
          <td></td>
         <td></td>
      </tr>
      
    </tbody>
  </table>
</div>
      <br/>
      <br/>
 <div class="container py-5 h-100">
    <div class="row d-flex justify-content-left align-items-center h-100">
     	
			
						<div class="panel-heading">  
		      			    <form name="backToHome" method="post" action="./HRController?oper=backToHome">  
		       				 		<button type="submit" class="btn btn-primary">Back to Home page</button>	
		       				</form>
						</div>		
				</div>
		</div>
	
</body>
</html>