<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select corporateName,corporateId from corporate where corporateId=?;
            <sql:param value="${param.id}" />
     </sql:query>
     
     <div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="user.jsp">User SetUp</a>
			<a href="corporate.jsp">Corporate SetUp</a>
		</div>
	    <h3 style="text-align: center;font-weight:normal;"><u>Add Account for corporate "${param.id}".</u></h3>
		 <c:forEach var="row" items="${result.rows}">
			<form action="LoginServlet" method="get">
					<input type="hidden" value="accountadd" name="action">
   					<table style="padding-left:5%;border-collapse:separate; width:50%; margin-left:25%;">
   						<tr>
  							<td><input type="hidden" id="cid" name="cid" value="${row.corporateId}"/></td>
  							<td><input type="hidden" id="cname" name="cname" value="${row.corporateName}"/></td>
 						</tr>
						<tr>
  							<td>A/C No:</td>
  							<td><input type="text" name="acno" style="width: 70%;"></td>
 						</tr>
 						<tr>
  							<td>A/C Name:</td>
  							<td><input type="text" name="acname" style="width: 70%;"></td>
 						</tr>
						<tr>
  							<td>Branch:</td>
  							<td><select name="branch" id="branch">
  								<option value="Mumbai,India">Mumbai,India</option>
  								<option value="Chicago,US">Chicago,US</option>
  								<option value="Tokyo,Japan">Tokyo,Japan</option>
  								<option value="Seoul,SKorea">Seoul,SKorea</option>
							</select></td>
 						</tr>
 						<tr>
  							<td>Currency:</td>
  							<td><select name="curr" id="curr">
  								<option value="Indian Rupee">Indian Rupee</option>
  								<option value="US Dollar">US Dollar</option>
  								<option value="Yen">Yen</option>
  								<option value="Won">Won</option>
							</select></td>
 						</tr>
 						<tr>
  							<td>Balance:</td>
  							<td><input type="text" name="bal" style="width: 70%;"></td>
 						</tr>
 						<tr>
  							<td>Opening Balance:</td>
  							<td><select name="obal" id="obal">
  								<option value="50000">50000</option>
  								<option value="100000">100000</option>
  								<option value="150000">150000</option>
							</select></td>
 						</tr>
 						<tr>
  							<td>Closing Balance:</td>
  							<td><select name="cbal" id="obal">
  								<option value="500000">500000</option>
  								<option value="1000000">1000000</option>
  								<option value="1500000">1500000</option>
							</select></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form>
  			</c:forEach>
  		</div>
</body>
</html>