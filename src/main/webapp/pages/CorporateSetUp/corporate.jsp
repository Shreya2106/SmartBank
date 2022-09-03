<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/main.css" type="text/css" />
<title>Corporate Set-Up</title>
<style>
.error{color:red} 
html{
    height: 100%;
}
.flex-left{
	margin-left:15%;
	width:60%;
	background-color:#b1d3ee;
	height:50%;
}
.flex-right {
	margin-left:8%;
	margin-right:5%;
	align-content:right;
	width:25%;
}
body{
	background-color:white;
	background-image:url("../cliparts/corporate-icon.jpg");
	background-size:30%;
	background-repeat:no-repeat;
    background-position:bottom left;
    min-height: 95%;
}

</style>
</head>
<body>
	<div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../logout">LogOut</a>
			<a href="user">User SetUp</a>
			<a href="account">Account SetUp</a>
		</div>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" id="flex-container" style="margin-top:2%; height:100%;">
		   <div class="flex-left">
			<h2 style="text-align: center;font-weight:normal;"><u>Our Corporate Customers</u></h2>
			<c:forEach var="row" items="${result}">
				   <table aria-label="Corp Details" class="ac">
				   <tr class="ac-r">
				   		<th>Corporate Id</th>
				   		<th>Corporate Name</th>
				   		<th>Address</th>
				   		<th>Phone Number</th>
				   		<th colspan="2">Action</th>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.corporateId}"/></td>
                        <td><c:out value="${row.corporateName}"/></td>
                         <td><c:out value="${row.address}"/></td>
                          <td><c:out value="${row.contactNumber}"/></td>
                        <td><a href="corporateupdate?id=${row.corporateId}">Update</a></td>
                        <td><a href= "corporatedelete/${row.corporateId}">Delete</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
		</div>
		<%if (session.getAttribute("corperror") =="true") {%>
		  <div class="flex-right" id="ch" style="height:40%;background-color:#b1d3ee;boxShadow:0 8px 16px 0 rgba(0,0,0,0.2);">
			 <h3 style="text-align: center;font-weight:normal;">Want to add a corporate?</h3>
			   <div id="corpadd"> 
				 <form:form action="addCorporate" modelAttribute="corp" method="post">  
		         <form:errors path="*" cssClass="error"/> 
					<table  aria-label="Corp Add" style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<th style="width:100px;">Corporate Name</th>
  							<td><form:input type="text" path="corporateName" style="width:70%;" required="true"/></td>
 						</tr>
						<tr>
  							<th>Address</th>
  							<td><form:input type="text" path="address" style="width: 70%;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required="true"  /></td>
 						</tr>
 						<tr>
  							<th>Contact Number</th>
  							<td><form:input type="text" path="contactNumber" style="width: 70%;" pattern="[6-9]{1}[0-9]{9}" required="true" oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}" /></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form:form>
  			</div>
  		</div>
	 <%} else {%>
	    <div class="flex-right" id="ch" style="height:40%;">
			 <h3 style="text-align: center;font-weight:normal;">Want to add a corporate?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="corpadd" style="visibility: hidden;"> 
			 	<form:form action="addCorporate" modelAttribute="corp" method="post">  
					<table aria-label="Corp Details" style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<th style="width:100px;">Corporate Name</th>
  							<td><form:input type="text" path="corporateName" style="width:70%;" required="true"/></td>
 						</tr>
						<tr>
  							<th>Address</th>
  							<td><form:input type="text" path="address" style="width: 70%;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required="true"/></td>
 						</tr>
 						<tr>
  							<th>Contact Number</th>
  							<td><form:input type="text" path="contactNumber" style="width: 70%;" pattern="[7-9]{1}[0-9]{9}" required="true" oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}"/></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form:form> <% } %>
			 </div>
			</div>
		</div>
	</div>
	<script>
		function changeVisibility() {
  			document.getElementById("corpadd").style.visibility = "visible";
  			document.getElementById("add").style.visibility = "hidden";
  			document.getElementById("ch").style.backgroundColor = "#b1d3ee";
  			document.getElementById("ch").style.boxShadow= "0 8px 16px 0 rgba(0,0,0,0.2)";
  			console.log("Chnaged");
		}
		function change(){
		  document.getElementById("msg").style.visibility = "hidden";
		  document.getElementById("flex-container").style.marginTop = "0px";
		   <%session.setAttribute("successMsg","empty");%>
		}
	</script>
  </body>
</html>