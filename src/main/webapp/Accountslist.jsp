<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
.flex-conatiner {
	margin-left:40px;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	align-content:center;
	width:80%;
}
</style>
</head>
<body>
	<c:set var="active" value="0"/> 
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select * from accounts where corporateId=? and active=0;
            <sql:param value="${param.cid}" />
            
   
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
			<a href="user.jsp">User SetUp</a>
		</div>
		<p style="text-align: center;"><u>Accounts List of Corporate ${param.cid}</u></p>
		   <c:forEach var="row" items="${result.rows}">
			  <table class="ac">
				   <tr class="ac-r">
				   		<td>A/C No</td>
				   		<td>A/C Name</td>
				   		<td>Branch</td>
				   		<td>Currency</td>
				   		<td>Balance</td>
				   		<td>Opening Balance</td>
				   		<td>Closing Balance</td>
				   		<td>Transaction Details</td>
				   		<td>Close Account</td>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.accountNumber}"/></td>
                        <td><c:out value="${row.accountName}"/></td>
                        <td><c:out value="${row.branch}"/></td>
                        <td><c:out value="${row.currency}"/></td>
                        <td><c:out value="${row.balance}"/></td>
                        <td><c:out value="${row.openingBalance}"/></td>
                        <td><c:out value="${row.closingBalance}"/></td>
                        <td><a href="Trans.jsp?id=<c:out value="${row.accountNumber}"/>">View</a></td>
                        <td><a href= "LoginServlet?id=<c:out value="${row.accountNumber}"/>&action=accountdelete">Close</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
		</div>
</body>
</html>