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
			<form action="${contextPath}/job/apply">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Company</th>
						<th>Job Title</th>
						<th>Job Type</th>
						<th>Job Field</th>
						<th>Description</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				   <c:forEach var="job" items="${jobs}">
					<tr>
						<td>${job.company.companyName}</td>
						<td>${job.jobTitle}</td>
						<td>${job.jobType}</td>
						<td>${job.jobField}</td>
						<td>${job.description}</td>
						<td><button type="submit" class="btn btn-success" value="${job.jobID}" name="job">Apply</button></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</form>
		</div>
	</main>
</body>
</html>