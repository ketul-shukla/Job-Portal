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
  <style type="text/css">
  	.margin-adjust{
		margin-top: 225px;
	}

	.links-settle{
		float: right;
	}

	.nav-color{
		background-color: #084268;
	}

  </style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<nav class="navbar navbar-inverse navbar-fixed-top nav-color">
	   <div class="navbar-header">
		<a class="navbar-brand" href="#">Find a Job</a>
		<ul class="nav navbar-nav">
    		<li><a style="color: #ffffff" href="user/register"><span class="glyphicon glyphicon-user"></span>&nbsp; Register as Job Seeker</a></li>
      		<li><a style="color: #ffffff" href="user/company"><span class="glyphicon glyphicon-user"></span>&nbsp; Register as Company</a></li>
    	</ul>
	   </div>	
	</nav>
	<main>
		<div class="container well col-md-4 col-md-offset-4 margin-adjust">
			<form action="user/login" method="post">
	  			<div class="form-group">
				    <label for="emailID">Email address:</label>
				    <input type="email" class="form-control" id="emailID" name="emailID" placeholder="Enter email id here" required="">
	  			</div>
			 	 <div class="form-group">
				    <label for="password">Password:</label>
			    	<input type="password" class="form-control" id="password" name="password" placeholder="Enter password here" required="">
			  	</div>
			  	<button style="align-self: center;" type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</main>
</body>
</html>