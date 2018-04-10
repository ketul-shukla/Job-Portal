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
		<a class="navbar-brand" href="#">Find a Job</a>
		<ul class="nav navbar-nav navbar-right">
    		<li><a style="color: #ffffff" href="${contextPath}/user/logout"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Logout</a></li>
    		<li><a style="color: #ffffff" href="${contextPath}/user/home"><span class="glyphicon glyphicon-home"></span>&nbsp; Home</a></li>
    	</ul>
	   </div>	
	</nav>
	<main class="container well adjust-margin">
		<form action="${contextPath}/job/post" novalidate="" id="postJobForm" method="post">
		 	<div class="form-group">
			    <label for="jobTitle">Job Title:</label>
		    	<input type="text" class="form-control" id="jobTitle" name="jobTitle" placeholder="Enter job title" required="">
		  	</div>
		  	<div class="form-group">
		  		<label for="jobType">Job Type:</label>
			    <select class="form-control" name="jobType">
			     <option value="Full-time">Full-time</option>
			     <option value="Part-time">Part-time</option>
			     <option value="Internship">Internship</option>
			    </select>
		  	</div>
		  	<div class="form-group">
			    <label for="jobPoster">Job Poster:</label>
		    	<input type="text" class="form-control" id="jobPoster" name="jobPoster" placeholder="Enter your name" required="">
		  	</div>
		  	<div class="form-group">
			    <label for="jobField">Job Field:</label>
		    	<input type="text" class="form-control" id="jobField" name="jobField" placeholder="Enter job Field" required="">
		  	</div>
		  	<div class="form-group">
			    <label for="contactEmail">Contact Email ID:</label>
		    	<input type="email" class="form-control" id="contactEmailID" name="contactEmailID" placeholder="Enter contact Email" required="">
		  	</div>
		  	<div class="form-group">
			    <label for="jobDescription">Job Description:</label>
		    	<textarea class="form-control" rows="10" cols="" style="width: 100%;" name="jobDescription" placeholder="Enter Job Description"></textarea>
		  	</div>
		  	<button type="submit" class="btn btn-success">Register</button>
		</form>
	</main>	
	<script>	
 	$(document).ready(function(){
 		$.validator.addMethod("jobTitle", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("jobPoster", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("jobField", function(value, element) {
                return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
            });
 		$.validator.addMethod("contactEmailID", function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
            });
 		$.validator.addMethod("jobDescription", function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9 ]+$/i.test(value);
            });


 			$("#postJobForm").validate({
            onfocusout: function(element){
                this.element(element);
            },

 			rules: {
 				jobTitle: {
 					required: true,
 					jobTitle: true,
 					minlength: 2,
 				},
 				jobPoster: {
 					required: true,
 					jobPoster: true
 				},
 				jobField: {
 					required: true,
 					jobField: true
 				},
 				contactEmailID: {
 					required:true,
 					contactEmailID:true
 				},
 				jobDescription: {
 					required: true,
 					jobDescription: true,
 				}
 			},

 			messages: {
 				jobTitle: 
 				{
 					required: "Please enter job title.",
 					jobTitle: "Please enter characters only",
 					minlength: "Title should be of 2 characters.",
 				},
 				jobPoster: {
 					required: "Please enter your name.",
 					jobPoster: "Please enter characters only",
 				},
 				jobField: {
 					required: "Please enter job field.",
 					jobField: "Please enter characters only",
 				},
 				contactEmailID: {
 					required: "Please enter email Id.",
 					contactEmailID: "Please enter a valid email Id.",
 				},
 				jobDescription: {
 					required: "Please enter job description",
 					jobDescription: "This field takes only valid characters",
 				}
 			}
 		});     		
 		
 	});

 </script>
</body>
</html>