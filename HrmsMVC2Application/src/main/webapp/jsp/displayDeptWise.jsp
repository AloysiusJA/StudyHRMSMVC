<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@page import="com.ja.learning.hrmsmvc.model.User"%>
    <%@page import="com.ja.learning.hrmsmvc.model.Employee"%>
      <%@page import="java.util.List"%>
     <%@page import="java.util.ArrayList"%>
    
     
    
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>
<% User currentUser = (User)(session.getAttribute("currentSessionUser"));%>
 
  
 <% List<Employee> empList = (ArrayList<Employee>)(session.getAttribute("employeeList"));%>

      <div class="container">
  <h2>Employees of Department <%=empList.get(0).getDepartment() %></h2>
  <p></p>            
  <table class="table">
   
    <tbody>
    <% for(Employee emp:empList) {%>
      <tr>
        <td>
         <%=emp %>
        </td>   
    
      </tr>
       <%} %>
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