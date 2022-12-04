<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hrms mvc Login</title>
  </head>
  <body>
  <div class="jumbotron text-center">
  <h1>Welcome to HRMS Application</h1>
  <p></p> 
</div>
<section class="h-100 gradient-form" style="background-color: #eee;">

  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-5">
     
      <br/>
                <form name="loginForm" method="post" action="../LoginController">
                <h2>Please login to your account</h2>
                <br/> 
              <div class="row">
  				<div class="col-sm-3">
  					<label class="form-label" for="loginpage">User Name:</label>
  				</div>
 				 <div class="col-sm-8">
 				 	<input type="text" name="userName" class="form-control" />
 				 </div>
				</div>
				<br/> 
                <div class="row">
  				<div class="col-sm-3">
  					<label class="form-label" for="password">Password:</label>
  				</div>
 				 <div class="col-sm-8">
 				 	<input type="password" name="passWord" class="form-control" />
 				 </div>
				</div>   
				<br/>
				<br/>                
				<div class="row">
				
				 <div class="col-sm-4"></div>
 					 <div class="col-sm-3">
 					 	<button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="login">Log
                      in
                      </button>
                      </div>
  					<div class="col-sm-3"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Reset">Reset</button></div>
				</div>
                 

                 
                </form>
      </div>
    </div>
  </div>
</section>

  </body>
 </html>