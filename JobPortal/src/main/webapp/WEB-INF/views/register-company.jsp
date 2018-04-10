<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Job Portal</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
  <style type="text/css">
  	.adjust-margin{
  		margin-top: 70px;
  	}

  	.nav-color{
		background-color: #084268;
  	}
    label.error{
      	color: #ff0000;
    } 

  </style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse navbar-fixed-top nav-color">
	   <div class="navbar-header">
		<a class="navbar-brand" href="">Find a Job</a>
		<ul class="nav navbar-nav">
    		<li><a style="color: #ffffff" href="${contextPath}"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Back to Log In</a></li>
    	</ul>
	   </div>	
	</nav>
	<main class="container well adjust-margin">
		<form action="${contextPath}/user/company" novalidate="" id="registerCompanyForm" method="post">
			<div class="form-group">
			    <label for="companyname">Company Name:</label>
			    <input type="text" class="form-control" id="companyName" name="companyName" placeholder="Enter company name" required="">
  			</div>
		 	<div class="form-group">
			    <label for="Street">Street:</label>
			    <input type="text" class="form-control" id="street" name="street" placeholder="Enter street address" required="">
  			</div>
		 	<div class="form-group">
			    <label for="country">Country:</label>
			    <input type="text" class="form-control" id="country" name="country" placeholder="Enter country" required="">
  			</div>
		 	<div class="form-group">
			    <label for="state">State</label>
			    <input type="text" class="form-control" id="state" name="state" placeholder="Enter your state" required="">
  			</div>
  			<div class="form-group">
			    <label for="city">City</label>
			    <input type="text" class="form-control" id="city" name="city" placeholder="Enter your city" required="">
  			</div>
		 	<div class="form-group">
			    <label for="zipCode">Zip Code:</label>
			    <input type="text" class="form-control" id="ZipCode" name="zipCode" placeholder="Enter your zip code" required="">
  			</div>
  			<div class="form-group">
			    <label for="compnayUrl">Company URL:</label>
			    <input type="text" class="form-control" id="companyUrl" name="companyUrl" placeholder="Enter url here" required="">
  			</div>
		 	<div class="form-group">
			    <label for="emailID">Email ID:</label>
			    <input type="email" class="form-control" id="emailID" name="emailID" placeholder="Enter user name here" required="">
  			</div>
		 	 <div class="form-group">
			    <label for="pwd">Password:</label>
		    	<input type="password" class="form-control" id="password" name="password" placeholder="Enter password here" required="">
		  	</div>
		  	<div class="form-group">
			    <label for="pwd">Confirm Password:</label>
		    	<input type="password" class="form-control" id="cpassword" name="cpassword" placeholder="ReEnter password here" required="">
		  	</div>
		  	<button type="submit" class="btn btn-success">Register</button>
		</form>
	</main>
	<script>	
 	$(document).ready(function(){
 		$.validator.addMethod("companyName", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("street", function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9 ]+$/i.test(value);
            });
 		$.validator.addMethod("country", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("state", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("city", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("zipCode", function(value, element) {
                return this.optional(element) || /^[0-9]{5}$/i.test(value);
            });
 		$.validator.addMethod("companyUrl", function(value, element) {
                return this.optional(element) || /^(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9]\.[^\s]{2,})$/i.test(value);
            });
 		$.validator.addMethod("emailID", function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
            });
 		$.validator.addMethod("password", function(value, element) {
                return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}$/i.test(value);
            });


 		$("#registerCompanyForm").validate({
            onfocusout: function(element){
                this.element(element);
            },

 			rules: {
 				companyName: {
 					required: true,
 					companyName: true,
 					minlength: 2,
 				},
 				street: {
 					required: true,
 					street: true
 				},
 				country: {
 					required: true,
 					country: true
 				},
 				state: {
 					required: true,
 					state: true
 				},
 				city: {
 					required: true,
 					city: true
        },
 				zipCode: {
 					required: true,
 					zipCode: true,
 				},
 				companyUrl: {
 					required: true,
 					companyUrl: true
 				},
 				emailID: {
 					required:true,
 					emailID:true
 				},
 				password:{
          			required: true,
          			password: true
        		},
        		cpassword:{
        			equalTo: "#password",
        		}
 			},

 			messages: {
 				companyName: 
 				{
 					required: "Please enter your company name.",
 					companyName: "Please enter characters only",
 					minlength: "Company name should be of 2 characters.",
 				},
 				street: {
 					required: "Please enter your street.",
 					street: "Please enter characters only",
 				},
 				country: {
 					required: "Please enter country.",
 					country: "Please enter characters only",
 				},
 				state: {
 					required: "Please enter state.",
 					state: "Please enter characters only",
 				},
 				city: {
 					required: "Please enter city.",
 					city: "Please enter characters only",
 				},
 				zipCode: {
 					required: "Please enter zipcode",
 					zipCode: "Please enter valid zipcode",
 				},
 				companyUrl: {
 					required: "Please enter url.",
 					companyUrl: "Please enter characters only",
 				},
 				emailID: {
 					required: "Please enter email Id.",
 					emailID: "Please enter a valid email Id.",
 				},
 				password: {
          			required: "Please enter password.",
          			password: "Password should contain Minimum 8 characters at least 1 uppercase alphabet, 1 lowercase alphabet, 1 number and 1 Special Character."
        		},
        		cpassword:{
        			equalTo: "Both the passwords should match."
        		}
 			}
 		});     			
 	});
 </script>
</body>
</html>