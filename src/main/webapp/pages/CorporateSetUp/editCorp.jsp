<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="corporateUpdateError.jsp"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Corporate Update</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<style>
.error{color:red} 
body{
background-image:url("cliparts/update.jpg");
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
	<% response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
	if (session.getAttribute("email") ==null || session.getAttribute("userType").equals("front")) {
     		response.sendRedirect("loginerrors");
     }%>
     <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="logout">LogOut</a>
			<a href="corporate">Corporate Home SetUp</a>
			<a href="account">Account SetUp</a>
			<a href="user">User SetUp</a>
		</div>
		<div class="card">
		<div class="nav" style="background-color:#8d88e9;">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;padding-left:25%;">Update Form for ${param.id}: </h3>
	    </div><br>
		<form:form action="editCorporate" modelAttribute="editCorpForm">
		<form:errors path="*" cssClass="error"/> 
        <table aria-label="Corp Edit" style="padding-left:20%;border-collapse:separate;border-spacing: 0 15px;">
           <tr>
           	   <th>Corporate Id:</th>
               <td><form:input type="text" value="${editCorpForm.getCorporateId()}" path="corporateId" style="height:30px;width:250px;" title="You cannot change this field" readonly="true" /></td>
           </tr>
           <tr>
           		<th>Corporate Name:</th>
           		<td><form:input type="text" value="${editCorpForm.getCorporateName()}" path="corporateName" style="height:30px;width:250px;" title="You cannot change this field" readonly="true" /></td>
           	</tr>
           <tr>
           		<tr>
           		<th>Address:</th>
           		<td><form:input type="text" value="${editCorpForm.getAddress()}" path="address" style="color:red; height:30px;width:250px;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required="true" /></td>
           </tr>
           <tr>
           		<tr>
           		<th>Contact Number:</th>
           		<td><form:input type="text" value="${editCorpForm.getContactNumber()}" path="contactNumber" style="color:red; height:30px;width:250px;" pattern="[7-9]{1}[0-9]{9}" required="true" oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}"/></td>
           </tr>
           	<td><input type="hidden" value="corpupdate" name="action"/></td>
  			<td><input type="submit" value="update" style="background-color:#8d88e9; width:40%; height: 30px; padding:0px auto; display:block; border-color:#8d88e9; color:white;" /></td>
        </table>
	</form:form>
	</div>
	</div>
</body>
</html>