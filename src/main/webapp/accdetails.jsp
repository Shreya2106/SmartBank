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
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
        <sql:query dataSource="${dbsource}" var="result">
            select * from accounts where accountNumber=?;
             <sql:param value="${param.id}" />
        </sql:query>
        
        <div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="login.jsp">LogOut</a>
			<a href="userhome.jsp">Home</a>
		</div>
		<h2 style="text-align: center;font-weight:normal;">Hi ${name}!!</h2>
		<h3 style="text-align: center;font-weight:normal;">Your A/C details for the account ${param.id} is as follows: </h3>
		<c:forEach var="row" items="${result.rows}">
				   <table style="margin-left:35%; border-collapse:separate;border-spacing: 0 15px;background-color:rgb(161, 52, 235);color:rgb(163, 243, 155);width:30%;text-align:center;box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);">
				   <tr>
				   		<td style="color:white;">A/C No</td>
				   		<td><c:out value="${row.accountNumber}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">A/C Name</td>
				   		<td><c:out value="${row.accountName}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">Branch</td>
				   		<td><c:out value="${row.branch}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">Currency</td>
				   		<td><c:out value="${row.currency}"/></td>
				   	</tr>
				   	<tr>
				   		<td style="color:white;">Balance</td>
				   		<td><c:out value="${row.balance}"/></td>
				   	</tr>
                    <tr>
                    	<td style="color:white;">Opening Balance</td>
                        <td><c:out value="${row.openingBalance}"/></td>
                     </tr>
                     <tr>
                     	<td style="color:white;">Closing Balance</td>
                         <td><c:out value="${row.closingBalance}"/></td>
                      </tr>
                      <tr>
                      	<td style="color:white;">Transaction Details</td>
                        <td><a style="color:rgb(163, 243, 155);" href="transdetails.jsp?id=<c:out value="${row.accountNumber}"/>">Details</a></td> 
                    </tr>
                    </table><br>
                </c:forEach>
             </div>
</body>
</html>