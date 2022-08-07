<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="userUpdateError.jsp"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Update</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
body{
background-image:url("../ImagesServlet?action=update");
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
     		response.sendRedirect("../loginError.jsp");
     }%>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select * from users where loginId=?;
            <sql:param value="${param.id}" />
     </sql:query>
     <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="../CorporateSetUp/corporate.jsp">Corporate SetUp</a>
			<a href="../AccountSetUp/account.jsp">Account SetUp</a>
			<a href="user.jsp">User Home SetUp</a>
		</div>
		<div class="card">
		<div class="nav" style="background-color:#8d88e9;">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;padding-left:25%;">Update Form for ${param.id}: </h3>
	    </div><br>
	    <form action="../LoginServlet">
        <table style="padding-left:10%;border-collapse:separate;border-spacing: 0 15px;">
        <c:forEach var="row" items="${result.rows}" >
        <input type="hidden" value="${param.id}" name="id" />
           <tr>
           	   <td>Corporate Id:</td>
               <td><input type="text" value="${row.corporateId}" name="corpId" style="height:30px;width:250px;" title="You cannot change this field" readonly/></td>
           </tr>
           <tr>
           		<td>Login Id:</td>
           		<td><input type="text" value="${row.loginId}" name="loginId" style="height:30px;width:250px;" title="You cannot change this field" readonly/></td>
           	</tr>
           <tr>
           		<td>User Name:</td>
           		<td><input type="text" name="username" value="${row.username}" style="height:30px;width:250px;" title="You cannot change this field" readonly/></td>
           </tr>
           <tr>
           		<td>Department:</td>
           		<td><input type="text" name="department" value="${row.department}" style="height:30px;width:250px;" title="You cannot change this field" readonly/></td>
           </tr>
           <tr>
           		<tr>
           		<td>Address:</td>
           		<td><input type="text" name="corpAddress" value="${row.address}"style="color:red; height:30px;width:250px;" pattern="(?=.*[a-z])(?=.*[A-Z]).{5,}" oninvalid="setCustomValidity('Invalid Address')" onchange="try{setCustomValidity('')}catch(e){}" required/></td>
           </tr>
           <tr>
           		<tr>
           		<td>Contact Number:</td>
           		<td><input type="text" name="corpPh" value="${row.contactNumber}" style="color:red; height:30px;width:250px;" pattern="[7-9]{1}[0-9]{9}" required oninvalid="setCustomValidity('Invalid PhoneNo')" onchange="try{setCustomValidity('')}catch(e){}" /></td>
           </tr>
           	<td><input type="hidden" value="userupdate" name="action"/></td>
  			<td><input type="submit" value="update" style="background-color:#8d88e9; width:40%; height: 30px; margin:0px 20%; display:block; border-color:#8d88e9; color:white;" /></td>
  			</c:forEach>
        </table>
	</form>
	</div>
	</div>
</body>
</html>