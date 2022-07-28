<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<title>BackOffice</title>
<style>
.card{
height:550px;
}
.flex-left{
width:30%;
}
</style>
</head>
<body>
  <div class="card">
	<div class="nav">
		  <h3>SMART BANK</h3>
		  <a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
	</div>
	<h2 style="text-align: center;font-weight:normal;">Welcome Alan!! What would you like to do?</h2>
	<div class="flex-container">
	   <div class="flex-left">
	       <div style="padding-top:75px;">
	         <h3 style="text-align: center;font-weight:normal;">Corporate Set Up</h3>
	         <a href="corporate.jsp">Want to add/edit/delete the corporate customers?Click here.</a>
	       </div>
	   </div>
	   <div class="flex-left">
	   	   <div style="padding-top:75px;">
	         <h3 style="text-align: center;font-weight:normal;">User Set Up</h3>
	         <a href="user.jsp">Want to add/edit/delete the users from corporate customers?Click here.</a>
	        </div>
	   </div>
	   <div class="flex-left">
	   	  <div style="padding-top:75px;">
	         <h3 style="text-align: center;font-weight:normal;">Account Set Up</h3>
	         <a href="account.jsp">Want to add/edit/delete the bank accounts?Click here.</a>
	       </div>
	   </div>
    </div>
  </div>
</body>
</html>