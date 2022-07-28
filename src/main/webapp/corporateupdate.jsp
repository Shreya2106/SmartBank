<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corporate Update</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select * from corporate where corporateId=?;
            <sql:param value="${param.id}" />
     </sql:query>
     <div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="corporate.jsp">Corporate Home SetUp</a>
			<a href="account.jsp">Account SetUp</a>
			<a href="user.jsp">User SetUp</a>
		</div>
		<h3 style="text-align: center;font-weight:normal;">Update Form for ${param.id}: </h3>
		 <form action="LoginServlet">
        <table style="padding-left:40%;border-collapse:separate;border-spacing: 0 15px;">
        <c:forEach var="row" items="${result.rows}" >
        <input type="hidden" value="${param.id}" name="id" />
           <tr>
           	   <td>Corporate Id:</td>
               <td><input type="text" value="${row.corporateId}" name="corpId" style="height:30px;width:250px;" readonly/></td>
           </tr>
           <tr>
           		<td>Corporate Name:</td>
           		<td><input type="text" value="${row.corporateName}" name="corpName" style="height:30px;width:250px;" readonly/></td>
           	</tr>
           <tr>
           		<tr>
           		<td>Address:</td>
           		<td><input type="text" name="corpAddress" value="${row.address}"style="color:red; height:30px;width:250px;" /></td>
           </tr>
           <tr>
           		<tr>
           		<td>Contact Number:</td>
           		<td><input type="text" name="corpPh" value="${row.contactNumber}" style="color:red; height:30px;width:250px;" /></td>
           </tr>
           	<td><input type="hidden" value="corpupdate" name="action"/></td>
  			<td><input type="submit" value="update" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; border-color:blue; color:white;" /></td>
  			</c:forEach>
        </table>
	</form>
	</div>
</body>
</html>