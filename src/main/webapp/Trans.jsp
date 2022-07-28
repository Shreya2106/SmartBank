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
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
        <sql:query dataSource="${dbsource}" var="result">
            select * from transaction where accountNumber=?;
            <sql:param value="${param.id}" />
        </sql:query>
  
	<div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="corporate.jsp">Corporate SetUp</a>
			<a href="account.jsp">Account SetUp</a>
			<a href="user.jsp">User SetUp</a>
		</div>
		<div>
			<h3 style="text-align: center;font-weight:normal;"><u>Transaction Details</u></h3>
			<c:forEach var="row" items="${result.rows}">
				   <table style="padding-left:2px;border:1px solid rgb(161,52,235);background-color:rgb(161, 52, 235);color:rgb(163, 243, 155);width:100%;text-align:center;">
				   <tr style="color:white; text-decoration: underline;"><td style="width:15%;">Trans ID</td><td style="width:15%;">Trans Type</td><td style="width:15%;">Trans Date</td><td style="width:15%;">Amount</td></tr>
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