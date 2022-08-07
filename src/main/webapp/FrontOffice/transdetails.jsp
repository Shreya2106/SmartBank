<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Details</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
html{
    height: 100%;
}
body{
	background-image:url("../ImagesServlet?action=trans");
	background-size:30%;
    background-repeat:no-repeat;
    background-position:bottom left;
    background-color:white;
    min-height:95%;
}
.cards {
	margin-left:5%;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	width:90%;
	height:100%;
	background-color:#b1d3ee;
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
            select * from transaction where accountNumber=?;
            <sql:param value="${param.id}" />
        </sql:query>
  
	<div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="userhome.jsp">Home</a>
		</div>
		<div class="cards">
			<h2 style="text-align: center;font-weight:normal;color:white;"><u>Transaction Details</u></h2>
			<c:forEach var="row" items="${result.rows}">
				   <table style="padding-left:2px;border:1px solid rgb(161,52,235);background-color:rgb(161, 52, 235);color:rgb(163, 243, 155);width:100%;text-align:center;">
				   <tr style="color:white; text-decoration: underline;"><td style="width:15%;">Trans ID</th><td style="width:15%;">Trans Type</td><td style="width:15%;">Trans Date</td><td style="width:15%;">Amount</td></tr>
                    <tr>
                        <td><c:out value="${row.transactionId}"/></td>
                        <td><c:out value="${row.transactiontype}"/></td>
                        <td><c:out value="${row.transactionDate}"/></td>
                        <td><c:out value="${row.balance}"/></td>
                    </tr>
                    </table><br>
                </c:forEach>
		</div>
	</div>
</body>
</html>