<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/main.css" type="text/css" />
<title>BackOffice</title>
<style>
body{
background-color:#b1d3ee;
}
.flex-left{
width:30%;
background-color:#33d4e2;
}
#corporate{
background-image:url("cliparts/corporate-icon-1.jpg");
background-size:cover;
background-repeat:no-repeat;
}
#user{
background-image:url("cliparts/user-icon1.jpg");
background-size:cover;
background-repeat:no-repeat;
}
#account{
background-image:url("cliparts/account-icon-1.jpg");
background-size: 100%;
}
</style>
</head>
<body>
	<% response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
	if (session.getAttribute("email") == null || session.getAttribute("userType").equals("front")) {
     		response.sendRedirect("loginerrors");
     }%>
  <div>
	<div class="nav">
		  <h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
		  <a href="logout">LogOut</a>
	</div>
	<h2 style="text-align: center;font-weight:normal;">Welcome ${name}!! What would you like to do?</h2>
	<div class="flex-container" style="color:black;">
	   <div id="corporate" class="flex-left">
	       <div style="padding-top:75px;">
	         <h2 style="text-align: center;font-weight:normal;">Corporate Set Up</h2>
	         <a href="corporate" style="color:black;font-size:large;">Want to add/edit/delete the corporate customers?Click here.</a>
	       </div>
	   </div>
	   <div id="user" class="flex-left">
	   	   <div style="padding-top:75px;">
	         <h2 style="text-align: center;font-weight:normal;">User Set Up</h2>
	         <a href="user" style="color:black;font-size:large;">Want to add/edit/delete the users from corporate customers?Click here.</a>
	        </div>
	   </div>
	   <div id="account" class="flex-left">
	   	  <div style="padding-top:75px;">
	         <h2 style="text-align: center;font-weight:normal;">Account Set Up</h2>
	         <a href="account" style="color:black;font-size:large;">Want to add/edit/delete the bank accounts?Click here.</a>
	       </div>
	   </div>
    </div>
  </div>
</body>
</html>