<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Set Up</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
.div-1{
	margin-left:10%;
	width:80%;
	align-content:center;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	z-index: -1;
}
#accountadd {
	margin-left:30%;
	align-content:center;
	width:30%;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	position:absolute;
	z-index: 9999;
    overflow: hidden;
    top: 50%;
    
}
</style>
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select corporateName,corporateId from corporate where softdeleted=0 group by corporateId;
     </sql:query>
     
     <div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="user.jsp">User SetUp</a>
			<a href="corporate.jsp">Corporate SetUp</a>
		</div>
			    <h3 style="text-align: center;font-weight:normal;"><u>Our Accounts.</u></h3>
			    <c:forEach var="row" items="${result.rows}">
			    <div class="div-1">
				   <table class="ac">
				   <tr class="ac-r">
				   		<td>Corporate Id</td>
				   		<td>Corporate Name</td>
				   		<td>Accounts List</td>
				   		<td>Want to Add a account?</td>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.corporateId}"/></td>
                        <td><c:out value="${row.corporateName}"/></td>
                        <td><a href="Accountslist.jsp?cid=<c:out value="${row.corporateId}"/>">list</a></td>
                        <td><a href="accountadd.jsp?id=<c:out value="${row.corporateId}"/>">add</a></td>
                      </tr>
                   </table><br>
                </div>
            </c:forEach>
	</div>
</body>
</html>