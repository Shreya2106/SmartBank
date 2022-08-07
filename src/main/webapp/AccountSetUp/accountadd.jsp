<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="accountError.jsp"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
body{
background-image:url("../ImagesServlet?action=account_add");
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
	<% response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);
	if (session.getAttribute("email") ==null || session.getAttribute("userType").equals("front")) {
     		response.sendRedirect("../loginError.jsp");
     }%>
     
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select corporateName,corporateId from corporate where corporateId=?;
            <sql:param value="${param.id}" />
     </sql:query>
     
     <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="../UserSetUp/user.jsp">User SetUp</a>
			<a href="../CorporateSetUp/corporate.jsp">Corporate SetUp</a>
			<a href="account.jsp">Account Home SetUp</a>
		</div>
		<div class="card">
		  <div class="nav" style="background-color:#8d88e9;">
			<h3 style="font-family:serif;text-align:center;font-size:large;padding-left:30%;">Add Account for corporate "${param.id}".</h3>
	      </div><br>
	      <c:forEach var="row" items="${result.rows}">
			<form action="../LoginServlet" method="get">
					<input type="hidden" value="accountadd" name="action">
   					<table style="border-collapse:separate; width:80%; margin-left:10%;">
   						<tr>
  							<td><input type="hidden" id="cid" name="cid" value="${row.corporateId}"/></td>
  							<td><input type="hidden" id="cname" name="cname" value="${row.corporateName}"/></td>
 						</tr>
						<tr>
  							<td>A/C No:</td>
  							<td><input type="text" name="acno" style="width: 100%;" title="A/C No must be of 10 characters" pattern="[0-9]{10}" oninvalid="setCustomValidity('Invalid A/C Number')" onchange="try{setCustomValidity('')}catch(e){}"required></td>
 						</tr>
 						<tr>
  							<td>A/C Name:</td>
  							<td><input type="text" name="acname" style="width: 100%;" required></td>
 						</tr>
						<tr>
  							<td>Branch:</td>
  							<td><select name="branch" id="branch" style="width: 100%;" required>
  								<option value="Mumbai,India">Mumbai,India</option>
  								<option value="Chicago,US">Chicago,US</option>
  								<option value="Tokyo,Japan">Tokyo,Japan</option>
  								<option value="Seoul,SKorea">Seoul,SKorea</option>
							</select></td>
 						</tr>
 						<tr>
  							<td>Currency:</td>
  							<td><select name="curr" id="curr" style="width: 100%;" required>
  								<option value="Indian Rupee">Indian Rupee</option>
  								<option value="US Dollar">US Dollar</option>
  								<option value="Yen">Yen</option>
  								<option value="Won">Won</option>
							</select></td>
 						</tr>
 						<tr>
  							<td>Balance:</td>
  							<td><input type="text" name="bal" id="bal" style="width: 100%;" title="Balance should be between opening balance & closing balance" required></td>
 						</tr>
 						<tr>
  							<td>Opening Balance:</td>
  							<td><select name="obal" id="obal" style="width: 100%;" required>
  								<option value="50000">50000</option>
  								<option value="100000">100000</option>
  								<option value="150000">150000</option>
							</select></td>
 						</tr>
 						<tr>
  							<td>Closing Balance:</td>
  							<td><select name="cbal" id="cbal" style="width: 100%;" required>
  								<option value="500000">500000</option>
  								<option value="1000000">1000000</option>
  								<option value="1500000">1500000</option>
							</select></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:#8d88e9; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:#8d88e9;"><br>
  				</form>
  			</c:forEach>
  		</div>
  		</div>
</body>
</html>