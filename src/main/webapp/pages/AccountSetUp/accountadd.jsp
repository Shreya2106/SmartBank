<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="accountError.jsp"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Account</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
<style>
body{
background-image:url("../cliparts/add_account.jpg");
background-size:60%;
background-repeat:no-repeat;
background-color:white;
}
.card {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 30%;
  margin-top: 5%;
  background-color:#b1d3ee;
  margin-right:3%;
  margin-left: 65%; 
}
</style>
</head>
<body>
	<div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../logout">LogOut</a>
			<a href="user">User SetUp</a>
			<a href="corporates">Corporate SetUp</a>
			<a href="account">Account Home SetUp</a>
		</div>
		<div class="card">
		  <div class="nav" style="background-color:#8d88e9;">
			<h3 style="font-family:serif;text-align:center;font-size:large;padding-left:30%;">Add Account for corporate "${cid}".</h3>
	      </div><br>
			<form:form action="addaccount" modelAttribute="account" method="post">
   					<table aria-label="AC Add" style="border-collapse:separate; width:80%; margin-left:10%;">
   						<tr>
  							<td><form:input type="hidden" id="cid" path="corporate.corporateId" value="${cid}"/></td>
 						</tr>
						<tr>
  							<th id="acname">A/C Name:</th>
  							<td><form:input type="text" path="accountName" style="width: 100%;" required="true" /></td>
 						</tr>
						<tr>
  							<th id="branch">Branch:</th>
  							<td><form:select path="branch" id="branch" style="width: 100%;" required="true">
  								<form:option value="Mumbai,India">Mumbai,India</form:option>
  								<form:option value="Chicago,US">Chicago,US</form:option>
  								<form:option value="Tokyo,Japan">Tokyo,Japan</form:option>
  								<form:option value="Seoul,SKorea">Seoul,SKorea</form:option>
							</form:select></td>
 						</tr>
 						<tr>
  							<th id="currency">Currency:</th>
  							<td><form:select path="currency" id="curr" style="width: 100%;" required="true" >
  								<form:option value="Indian Rupee">Indian Rupee</form:option>
  								<form:option value="US Dollar">US Dollar</form:option>
  								<form:option value="Yen">Yen</form:option>
  								<form:option value="Won">Won</form:option>
							</form:select></td>
 						</tr>
 						<tr>
  							<th id="balance">Balance:</th>
  							<td><form:input type="text" path="balance" id="bal" style="width: 100%;" title="Balance should be between opening balance & closing balance" required="true" /></td>
 						</tr>
 						<tr>
  							<th id="obal">Opening Balance:</th>
  							<td><form:select path="openingBalance" id="obal" style="width: 100%;" required="true">
  								<form:option value="50000">50000</form:option>
  								<form:option value="100000">100000</form:option>
  								<form:option value="150000">150000</form:option>
							</form:select></td>
 						</tr>
 						<tr>
  							<th id="cbal">Closing Balance:</th>
  							<td><form:select path="closingBalance" id="cbal" style="width: 100%;" required="true">
  								<form:option value="500000">500000</form:option>
  								<form:option value="1000000">1000000</form:option>
  								<form:option value="1500000">1500000</form:option>
							</form:select></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:#8d88e9; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:#8d88e9;"><br>
  				</form:form>
  		</div>
  		</div>
</body>
</html>