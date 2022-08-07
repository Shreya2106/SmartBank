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
<title>A/C Details</title>
<style>
body{
background-image:url("../ImagesServlet?action=transaction");
background-size:50%;
background-repeat:no-repeat;
background-position:0 50%;
background-color:white;
}
.card {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 35%;
  margin-top: 2%;
  background-color:#b1d3ee;
  margin-left: 50%; 
}
</style>
</head>
<body>
	<% response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
	if (session.getAttribute("email") ==null || session.getAttribute("userType").equals("back")) {
     		response.sendRedirect("../loginError.jsp");
     }%>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
        <sql:query dataSource="${dbsource}" var="result">
            select * from accounts where accountNumber=?;
             <sql:param value="${param.id}" />
        </sql:query>

		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="userhome.jsp">Home</a>
		</div>
	<div class="card">
	    <h2 style="text-align: center;font-weight:normal;">Hi ${name}!!</h2>
		<h3 style="text-align: center;font-weight:normal;">Your A/C details for the account ${param.id} is as follows: </h3>
		<c:forEach var="row" items="${result.rows}">
				   <table style="margin-left:5%; border-collapse:separate;border-spacing: 0 10px;color:rgb(163, 243, 155);width:85%;font-size:17px;;text-align:center;">
				   <tr>
				   		<td style="color:white;">A/C No</td>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${row.accountNumber}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">A/C Name</td>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${row.accountName}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">Branch</td>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${row.branch}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">Currency</td>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${row.currency}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">Balance</td>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${row.balance}"/></td>
				   	</tr>
                    <tr>
                    	<td style="color:white;">Opening Balance</td>
                        <td style="color:rgb(161, 52, 235);"><c:out value="${row.openingBalance}"/></td>
                     </tr>
                     <tr>
                     	<td style="color:white;">Closing Balance</td>
                         <td style="color:rgb(161, 52, 235);"><c:out value="${row.closingBalance}"/></td>
                      </tr>
                      <tr>
                      	<td style="color:white;">Transaction Details</td>
                        <td><a style="color:rgb(161, 52, 235);" href="transdetails.jsp?id=<c:out value="${row.accountNumber}"/>">Details</a></td> 
                    </tr>
                    </table><br>
                </c:forEach>
        </div>
</body>
</html>