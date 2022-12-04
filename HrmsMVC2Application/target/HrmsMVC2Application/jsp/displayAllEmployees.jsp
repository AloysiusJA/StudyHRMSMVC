<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 <% List<Employee> empList = (ArrayList<Employee>)(session.getAttribute("employeeList"));%>

 <div class="container">
 
        <table border="1" class="table">
            
            <% for(Employee emp:empList) {%>
            <tr>
            <td>
              <%=emp %>
              </td>
            </tr>
            <br/>
            <br/>
            
          <%} %>
        </table>
         <form name="backToHome" method="post" action="./HRController?oper=backToHome">  
		       				 		<button type="submit" class="btn btn-primary">Back to Home page</button>	
		       				</form>
    </div>
   
    <br/>
  
</body>
</html>