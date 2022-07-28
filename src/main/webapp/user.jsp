<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<title>User Set-Up</title>
<style>
.flex-left{
	margin-left:40px;
	width:80%;
}
.flex-right {
	margin-left:40px;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	align-content:right;
	width:30%;
}
</style>
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select * from users where corporateId in(select distinct(corporateId) from corporate where softdeleted=0);
     </sql:query>
     <sql:query dataSource="${dbsource}" var="result1">
            select distinct(corporateId) from corporate where softdeleted=0;
     </sql:query>
	 <div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="corporate.jsp">Corporate SetUp</a>
			<a href="account.jsp">Account SetUp</a>
		</div>
		<div class="flex-container">
		   <div class="flex-left">
			<h3 style="text-align: center;font-weight:normal;"><u>Users from our Corporate Customers</u></h3>
			<c:forEach var="row" items="${result.rows}">
				   <table class="ac">
				   <tr class="ac-r">
				   		<td>Corporate Id</td>
				   		<td>Login Id</td>
				   		<td>User Name</td>
				   		<td>Department</td>
				   		<td>Address</td>
				   		<td>Phone Number</td>
				   		<td colspan="2">Action</td>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.corporateId}"/></td>
                        <td><c:out value="${row.loginId}"/></td>
                        <td><c:out value="${row.username}"/></td>
                        <td><c:out value="${row.department}"/></td>
                        <td><c:out value="${row.address}"/></td>
                          <td><c:out value="${row.contactNumber}"/></td>
                        <td><a href="UserUpdate.jsp?id=<c:out value="${row.loginId}"/>">Update</a></td>
                        <td><a href= "LoginServlet?id=<c:out value="${row.loginId}"/>&action=userdelete">Delete</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
		</div>
		<div class="flex-right">
			 <h3 style="text-align: center;font-weight:normal;">Want to add a user?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="useradd" style="visibility: hidden;">
			 	<form action="LoginServlet" method="get">
					<input type="hidden" value="useradd" name="action">
   					<table style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<td style="width:100px;">Corporate Id</td>
  							<td><select name="corpId" style="width: 70%;">
  									<c:forEach var="row" items="${result1.rows}">
  									<option><c:out value="${row.corporateId}"/></option>
  									</c:forEach>
  								</select>
  							</td>
 						</tr>
 						<tr>
  							<td>Login Id</td>
  							<td><input type="email" name="loginId" style="width: 70%;"></td>
 						</tr>
 						<tr>
  							<td>Username</td>
  							<td><input type="text" name="username" style="width: 70%;"></td>
 						</tr>
 						<tr>
  							<td>Department</td>
  							<td><input type="text" name="department" style="width: 70%;"></td>
 						</tr>
						<tr>
  							<td>Address</td>
  							<td><input type="text" name="corpAddress" style="width: 70%;"></td>
 						</tr>
 						<tr>
  							<td>Contact Number</td>
  							<td><input type="text" name="corpPh" style="width: 70%;"></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form>
			 </div>
			 </div>
			 </div>
			 <script>
				function changeVisibility() {
  					document.getElementById("useradd").style.visibility = "visible";
  					document.getElementById("add").style.visibility = "hidden";
				}
			</script>
	</div>
</body>
</html>