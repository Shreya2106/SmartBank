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
html{
    height: 100%;
}
body{
	background-image:url("../ImagesServlet?action=account_icon1");
	background-size:40% 30%;
    background-repeat:no-repeat;
    background-position:bottom left;
    background-color:white;
    min-height:95%;
}
.cards {
	margin-left:10%;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	background-color:#b1d3ee;
	width:80%;
	height:50%;
	
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
            select corporateName,corporateId from corporate where softdeleted=0 group by corporateId;
     </sql:query>
     
     <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="../UserSetUp/user.jsp">User SetUp</a>
			<a href="../CorporateSetUp/corporate.jsp">Corporate SetUp</a>
		</div>
		<div class="cards">
			    <h2 style="text-align: center;font-weight:normal;"><u>Our Accounts.</u></h2>
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
		</div>
</body>
</html>