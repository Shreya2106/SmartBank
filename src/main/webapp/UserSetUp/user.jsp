<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="userError.jsp"%>
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
	background-image:url("../ImagesServlet?action=user");
	background-size:40%;
	background-repeat:no-repeat;
    background-position:bottom left;
    background-color:white;
    min-height: 95%;
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
            select * from users where corporateId in(select distinct(corporateId) from corporate where softdeleted=0);
     </sql:query>
     <sql:query dataSource="${dbsource}" var="result1">
            select distinct(corporateId) from corporate where softdeleted=0;
     </sql:query>
	 <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="../CorporateSetUp/corporate.jsp">Corporate SetUp</a>
			<a href="../AccountSetUp/account.jsp">Account SetUp</a>
		</div>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" style="margin-top:2%; height:100%;">
		   <div class="flex-left">
			<h2 style="text-align: center;font-weight:normal;"><u>Users from our Corporate Customers</u></h2>
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
                        <td><a href= "../LoginServlet?id=<c:out value="${row.loginId}"/>&action=userdelete">Delete</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
		</div>
		<div class="flex-right" id="ch" style="height:40%;">
			 <h3 style="text-align: center;font-weight:normal;color:black;">Want to add a user?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="useradd" style="visibility: hidden;">
			 	<form action="../LoginServlet" method="get">
					<input type="hidden" value="useradd" name="action">
   					<table style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<td style="width:100px;">Corporate Id</td>
  							<td><select name="corpId" style="width: 70%;" required>
  									<c:forEach var="row" items="${result1.rows}">
  									<option><c:out value="${row.corporateId}"/></option>
  									</c:forEach>
  								</select>
  							</td>
 						</tr>
 						<tr>
  							<td>Login Id</td>
  							<td><input type="email" name="loginId" style="width: 70%;" required></td>
 						</tr>
 						<tr>
  							<td>Username</td>
  							<td><input type="text" name="username" style="width: 70%;" required></td>
 						</tr>
 						<tr>
  							<td>Department</td>
  							<td><select name="department" style="width: 70%;" required>
  									<option value="HR">HR</option>
  									<option value="Sales">Sales</option>
  									<option value="Finance">Finance</option>
  									<option value="IT">IT</option>
  									<option value="Marketing">Marketing</option>
  								</select></td>
 						</tr>
						<tr>
  							<td>Address</td>
  							<td><input type="text" name="corpAddress" style="width: 70%;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required></td>
 						</tr>
 						<tr>
  							<td>Contact Number</td>
  							<td><input type="text" name="corpPh" style="width: 70%;" pattern="[7-9]{1}[0-9]{9}" required oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}"></td>
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