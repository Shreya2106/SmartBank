<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="upateError.jsp"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Corporate Update</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
<style>
.error{color:red} 
body{
background-image:url("../cliparts/update.jpg");
background-size:40%;
background-repeat:no-repeat;
background-position:0 50%;
background-color:white;
}
.card {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 35%;
  margin-top: 2%;
  background-color:#b1d3ee;
  margin-left: 40%; 
}
</style>
</head>
<body>
	<div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../logout">LogOut</a>
			<a href="corporates">Corporate Home SetUp</a>
			<a href="account">Account SetUp</a>
			<a href="user">User SetUp</a>
		</div>
		<div class="card">
		<div class="nav" style="background-color:#8d88e9;">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;padding-left:25%;">Update Form for ${id}: </h3>
	    </div><br>
		<form:form action="editUserSub" modelAttribute="editUserForm">
		<form:errors path="*" cssClass="error"/> 
        <table aria-label="User Edit" style="padding-left:20%;border-collapse:separate;border-spacing: 0 15px;">
           <tr>
           	   <th>Corporate Id:</th>
               <td><form:input type="text" value="${editUserForm.getCorporate().getCorporateId()}" path="corporate.corporateId" style="height:30px;width:250px;" title="You cannot change this field" readonly="true" /></td>
           </tr>
           <tr>
           		<th>Login Id:</th>
           		<td><form:input type="text" value="${editUserForm.getLoginId()}" path="loginId" style="height:30px;width:250px;" title="You cannot change this field" readonly="true" /></td>
           	</tr>
           <tr>
           		<th>User Name:</th>
           		<td><form:input type="text" value="${editUserForm.getUsername()}" path="username" style="height:30px;width:250px;" title="You cannot change this field" readonly="true" /></td>
           	</tr>
           	<tr>
           		<th>Department:</th>
           		<td><form:input type="text" value="${editUserForm.getDepartment()}" path="department" style="height:30px;width:250px;" title="You cannot change this field" readonly="true" /></td>
           </tr>
           <tr>
           		<tr>
           		<th>Address:</th>
           		<td><form:input type="text" value="${editUserForm.getAddress()}" path="address" style="color:red; height:30px;width:250px;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required="true" /></td>
           </tr>
           <tr>
           		<tr>
           		<th>Contact Number:</th>
           		<td><form:input type="text" value="${editUserForm.getContactNumber()}" path="contactNumber" style="color:red; height:30px;width:250px;" pattern="[7-9]{1}[0-9]{9}" required="true" oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}"/></td>
           </tr>
           </table><br>
  		   <input type="submit" value="update" style="background-color:#8d88e9; width:40%; height: 30px; margin:0px auto; display:block; border-color:#8d88e9; color:white;" />
	</form:form>
	</div>
	</div>
</body>
</html>