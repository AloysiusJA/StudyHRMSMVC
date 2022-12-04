<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.ja.learning.hrmsmvc.model.User"%>
    <%@page import="com.ja.learning.hrmsmvc.model.Departments"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>This is HR home page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <!-- CSS only -->

</head>
<body>
 <header>
  <% User currentUser = (User)(session.getAttribute("currentSessionUser"));%>
  <% List<Departments> deptList = (ArrayList<Departments>)(session.getAttribute("departments")); %>
 
  
 <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">HRMS Application</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="#">Home</a></li>
   
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Log out</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>
 </header>
 
 <main>
 
  

<div class="jumbotron text-center">
  <h1>HRMS Application</h1>
  <p> Welcome to your home page <%= currentUser.getUserName()%> !</p> 
</div>
  
<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-info">
        <div class="panel-heading"><a href="jsp/addemployee.jsp" class="link-light" class="active"> <b>ADD NEW EMPLOYEE</b> </a></div>        
       
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-info">
      
        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#delete">Delete Employee</button>
       <div id="delete" class="collapse">
       		
    		 <label for="empID">Please enter the employee ID:</label>
		      <input type="text" class="form-control" id="empID" name="empID" placeholder="Employee ID" value="" required>
		      <br/>
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
        
      </div>
    </div>
   <form>
    <div class="col-sm-4">
      <div class="panel panel-info">
         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#editemp">Edit Employee Details</button>
       <div id="editemp" class="collapse">       		
    		 <label for="empID">Please enter the Employee ID</label>
		      <input type="text" class="form-control" id="empID" name="empID" placeholder="Employee ID" value="" required>
		      <br/>
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
      </div>
    </div>
    </form>
  </div>
   
  </div>
</div><br>

<div class="container"> 
<form name="dispOneForm" method="post" action="./HRController?oper=displayOneEmp">  
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-info">
      <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#demo">Display Employee</button>
       <div id="demo" class="collapse">
       		
    		 <label for="empID">Please enter the employee ID:</label>
		      <input type="text" class="form-control" id="empID" name="empID" placeholder="Employee ID" value="" required="true">
		      <br/>
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
       
      </div>
    </div>
    </form>
    
    <div class="col-sm-4"> 
      <div class="panel panel-info">
        <div class="panel-heading"><a href="./HRController?oper=displayAll" class="link-light" class="active"> <b>DISPLAY ALL EMPLOYEES</b> </a></div>
      
      </div>
    </div>
    
    <form name="dispDeptWise" method="post" action="./HRController?oper=dispDeptWise">  
    <div class="col-sm-4"> 
      <div class="panel panel-info">      
           
         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#dispDepts">Display By Departments</button>
       		<div id="dispDepts" class="collapse">
       		 <label for="department">Please select the Department:</label>
		      <select id="department" name="department" required class="form-control">
		        <option selected>Choose...</option>
		        <%for(Departments dept:deptList) {%>
		        <option value="<%=dept.getDept_description()%>"><%=dept.getDept_description() %>
		        <%} %>		      
		      </select>  		 
		     
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
      
     
    </div>
  </div>
   </form>
   
  </div>
</div>
<br/>
<div class="container">    
  <div class="row">
  <form>
    <div class="col-sm-4">
      <div class="panel panel-info">
         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#adddepts">ADD Departments</button>
       <div id="adddepts" class="collapse">       		
    		 <label for="empID">Please enter the Department Name</label>
		      <input type="text" class="form-control" id="department" name="department" placeholder="Department Name" value="" required>
		      <br/>
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
      </div>
    </div>
    </form>
    <form>
    <div class="col-sm-4">
      <div class="panel panel-info">
         <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#editpayroll">Edit Payroll</button>
       <div id="editpayroll" class="collapse">       		
    		 <label for="empID">Please enter the Employee ID</label>
		      <input type="text" class="form-control" id="empID" name="empID" placeholder="Employee ID" value="" required>
		      <br/>
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
      </div>
    </div>
    </form>
    <form name="genpayslip" method="post" action="./HRController?oper=genpayslip">
    <div class="col-sm-4"> 
      <div class="panel panel-info">
        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#genpayslip">Generate Pay slip</button>
       <div id="genpayslip" class="collapse">       		
    		 <label for="empID">Please enter the employee ID:</label>
		      <input type="text" class="form-control" id="empID" name="empID" placeholder="Employee ID" value="" required>
		      <br/>
		     <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Submit">Submit</button>
  		</div>
      </div>
    </div>    
    </form>
    
  </div>
</div>


<br><br>
 </main>
           
			
            
    <footer>
    
    </footer>
</body>
</html>