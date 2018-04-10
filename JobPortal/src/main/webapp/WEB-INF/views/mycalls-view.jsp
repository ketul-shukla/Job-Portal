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
  	.adjust-margin{
  		margin-top: 70px;
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
		<a class="navbar-brand" href="">Find a Job</a>
		<ul class="nav navbar-nav navbar-right">
    		<li><a style="color: #ffffff" href="${contextPath}/user/logout"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Logout</a></li>
    		<li><a style="color: #ffffff" href="${contextPath}/user/home"><span class="glyphicon glyphicon-home"></span>&nbsp; Home</a></li>
    	</ul>
	   </div>	
	</nav>
	<main>
		<div class="container adjust-margin"> 
			<form action="${contextPath}">
			<div class="jumbotron"><h2>You have ${jobApplication.size()} calls till now </h2> </div>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Company Name</th>
						<th>Job Title</th>
						<th>Job Field</th>
						<th>Job Type</th>
						<th>Email ID</th>
					</tr>
				</thead>
				<tbody>
				   <c:forEach var="jobApplication" items="${jobApplication}">
					<tr>
						<td>${jobApplication.job.company.companyName}</td>
						<td>${jobApplication.job.jobTitle}</td>
						<td>${jobApplication.job.jobField}</td>
						<td>${jobApplication.job.jobType}</td>
						<td>${jobApplication.job.company.emailID}</td>
				  </tr>
					</c:forEach>
				</tbody>
			</table>
			</form>
		</div>
	</main>
</body>
</html>