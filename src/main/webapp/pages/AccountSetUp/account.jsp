<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Account Set Up</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<style>
html{
    height: 100%;
}
body{
	background-image:url("cliparts/account-icon1.jpg");
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
     		response.sendRedirect("loginerrors");
     }%>
    <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="logout">LogOut</a>
			<a href="user">User SetUp</a>
			<a href="corporates">Corporate SetUp</a>
		</div>
		<%if (session.getAttribute("accerror") == "false") { %>
		<div class="cards">
			    <h2 style="text-align: center;font-weight:normal;"><u>Our Accounts.</u></h2>
			    <c:forEach var="rcid" items="${cid}" varStatus="status">
			    <div class="div-1">
				   <table aria-label="AC Details" class="ac">
				   <tr class="ac-r">
				   		<th>Corporate Id</th>
				   		<th>Corporate Name</th>
				   		<th>Accounts List</th>
				   		<th>Want to Add a account?</th>
				   	</tr>
                    <tr>
                        <td><c:out value="${rcid}"/></td>
                        <td><c:out value="${cname[status.index]}"/></td>
                        <td><a href="accountslists?id=${rcid}">list</a></td>
                        <td><a href="accountadd?id=${rcid}">add</a></td>
                      </tr>
                   </table><br>
                </div>
            </c:forEach>
           </div>
           <%} else { 
        		List<Integer> cid;
        		Object obj1 = session.getAttribute("cids"); 
        		cid = (List<Integer>)obj1;
        		pageContext.setAttribute("cid", cid);
           %>
           <div class="cards">
			    <h2 style="text-align: center;font-weight:normal;"><u>Our Accounts.</u></h2>
			    <c:forEach var="rcid" items="${pageScope.cid}" varStatus="status">
			    <div class="div-1">
				   <table aria-label="AC Details" class="ac">
				   <tr class="ac-r">
				   		<th>Corporate Id</th>
				   		<th>Corporate Name</th>
				   		<th>Accounts List</th>
				   		<th>Want to Add a account?</th>
				   	</tr>
                    <tr>
                        <td><c:out value="${rcid}"/></td>
                        <td><c:out value="${cname[status.index]}"/></td>
                        <td><a href="accountslists?id=${rcid}">list</a></td>
                        <td><a href="accountadd?id=${rcid}">add</a></td>
                      </tr>
                   </table><br>
                </div>
            </c:forEach>
           </div>
           <% } %>
		</div>
</body>
</html>