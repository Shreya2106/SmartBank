<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="userError.jsp"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
<title>User Set-Up</title>
<style>
.error{color:red} 
html{
    height: 100%;
}
.flex-left{
	margin-left:5%;
	width:80%;
	background-color:#b1d3ee;
	height:50%;
}
.flex-right {
	margin-left:10%;
	margin-right:5%;
	align-content:right;
	width:25%;
}
body{
	background-image:url("resources/cliparts/users.jpg");
	background-size:40%;
	background-repeat:no-repeat;
    background-position:bottom left;
    background-color:white;
    min-height: 95%;
}
th{
	font-weight:normal;
    padding:0px;
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
	 <sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank?useSSL=false"
                           user="root"  password="#&AnaShree24##"/>
     <sql:query dataSource="${dbsource}" var="result">
            select * from users where corporateId in(select distinct(corporateId) from corporate where softdeleted=0);
     </sql:query>
     <sql:query dataSource="${dbsource}" var="result1">
            select distinct(corporateId) from corporate where softdeleted=0;
     </sql:query>
	 <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="logout">LogOut</a>
			<a href="corporate">Corporate SetUp</a>
			<a href="account">Account SetUp</a>
		</div>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" style="margin-top:2%; height:100%;">
		   <div class="flex-left">
			<h2 style="text-align: center;font-weight:normal;"><u>Users from our Corporate Customers</u></h2>
			<c:forEach var="row" items="${result.rows}">
				<table aria-label="User Details" class="ac">
				   <tr class="ac-r">
				   		<th>Corporate Id</th>
				   		<th>Login Id</th>
				   		<th>User Name</th>
				   		<th>Department</th>
				   		<th>Address</th>
				   		<th>Phone Number</th>
				   		<th colspan="2">Action</th>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.corporateId}"/></td>
                        <td><c:out value="${row.loginId}"/></td>
                        <td><c:out value="${row.username}"/></td>
                        <td><c:out value="${row.department}"/></td>
                        <td><c:out value="${row.address}"/></td>
                          <td><c:out value="${row.contactNumber}"/></td>
                        <td><a href="userupdate?id=${row.loginId}">Update</a></td>
                        <td><a href= "userdelete?id=${row.loginId}">Delete</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
		</div>
		<%if (session.getAttribute("usererror") =="true") {%>
		  <div class="flex-right" id="ch" style="height:40%;background-color:#b1d3ee;boxShadow:0 8px 16px 0 rgba(0,0,0,0.2);">
			 <div id="ch" style="height:40%;">
			 <h3 style="text-align: center;font-weight:normal;color:black;">Want to add a user?</h3>
			 <div id="useradd">
			 	<form:form action="addUser" modelAttribute="user" method="post">
			 	<form:errors path="*" cssClass="error"/> 
   					<table aria-label="Add User" style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<th style="width:100px;">Corporate Id</th>
  							<td><form:select path="corporateId" style="width: 70%;" required="true">
  									<c:forEach var="row1" items="${result1.rows}">
  									<form:option value="${row1.corporateId}"><c:out value="${row1.corporateId}"/></form:option>
  									</c:forEach>
  								</form:select>
  							</td>
 						</tr>
 						<tr>
  							<th>Login Id</th>
  							<td><form:input type="email" path="loginId" style="width: 70%;" required="true" /></td>
 						</tr>
 						<tr>
  							<th>Username</th>
  							<td><form:input type="text" path="username" style="width: 70%;" required="true" /></td>
 						</tr>
 						<tr>
  							<th>Department</th>
  							<td><form:select path="department" style="width: 70%;" required="true">
  									<form:option value="HR">HR</form:option>
  									<form:option value="Sales">Sales</form:option>
  									<form:option value="Finance">Finance</form:option>
  									<form:option value="IT">IT</form:option>
  									<form:option value="Marketing">Marketing</form:option>
  								</form:select></td>
 						</tr>
						<tr>
  							<th>Address</th>
  							<td><form:input type="text" path="address" style="width: 70%;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required="true" /></td>
 						</tr>
 						<tr>
  							<th>Contact Number</th>
  							<td><form:input type="text" path="contactNumber" style="width: 70%;" pattern="[6-9]{1}[0-9]{9}" required="true" oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}" /></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form:form>
  			</div>
  		<%} else { %>
		<div class="flex-right" id="ch" style="height:40%;">
			 <h3 style="text-align: center;font-weight:normal;color:black;">Want to add a user?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="useradd" style="visibility: hidden;">
			 	<form:form action="addUser" modelAttribute="user" method="post">
   					<table aria-label="User Details" style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<th style="width:100px;">Corporate Id</th>
  							<td><form:select path="corporateId" style="width: 70%;" required="true">
  									<c:forEach var="row1" items="${result1.rows}">
  									<form:option value="${row1.corporateId}"><c:out value="${row1.corporateId}"/></form:option>
  									</c:forEach>
  								</form:select>
  							</td>
 						</tr>
 						<tr>
  							<th>Login Id</th>
  							<td><form:input type="email" path="loginId" style="width: 70%;" required="true"  /></td>
 						</tr>
 						<tr>
  							<th>Username</th>
  							<td><form:input type="text" path="username" style="width: 70%;" required="true" /></td>
 						</tr>
 						<tr>
  							<th>Department</th>
  							<td><form:select path="department" style="width: 70%;" required="true">
  									<form:option value="HR">HR</form:option>
  									<form:option value="Sales">Sales</form:option>
  									<form:option value="Finance">Finance</form:option>
  									<form:option value="IT">IT</form:option>
  									<form:option value="Marketing">Marketing</form:option>
  								</form:select></td>
 						</tr>
						<tr>
  							<th>Address</th>
  							<td><form:input type="text" path="address" style="width: 70%;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required="true" /></td>
 						</tr>
 						<tr>
  							<th>Contact Number</th>
  							<td><form:input type="text" path="contactNumber" style="width: 70%;" pattern="[6-9]{1}[0-9]{9}" required="true" oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}" /></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form:form>
			 </div>
		<%} %>
			 </div>
			 </div>
			 <script>
				function changeVisibility() {
  					document.getElementById("useradd").style.visibility = "visible";
  					document.getElementById("add").style.visibility = "hidden";
  					document.getElementById("ch").style.backgroundColor = "#b1d3ee";
  					document.getElementById("ch").style.boxShadow= "0 8px 16px 0 rgba(0,0,0,0.2)";
				}
				function change(){
					document.getElementById("msg").style.visibility = "hidden";
					document.getElementById("flex-container").style.marginTop = "0px";
					<%session.setAttribute("successMsg","empty");%>
				}
			</script>
	</div>
</body>
</html>