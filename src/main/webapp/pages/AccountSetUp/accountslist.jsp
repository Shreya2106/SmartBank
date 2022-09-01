<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Accounts List</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<style>
html{
    height: 100%;
}
body{
	background-image:url("cliparts/aclist.jpg");
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
	background-color:#b1d3ee;
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
			<a href="corporates">Corporate SetUp</a>
			<a href="account">Account SetUp</a>
			<a href="user">User SetUp</a>
		</div>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" style="margin-top:2%; height:100%;">
		<div class="cards">
		<h2 id="desc" style="text-align: center;font-weight:normal;"><u>Accounts List of Corporate ${cid}</u></h2>
		   <c:forEach var="row" items="${result}">
			  <table class="ac" aria-describedby="desc"  style="table-layout: auto;">
				   <tr class="ac-r">
				   		<th style="width:2%;">A/C No</th>
				   		<th style="width:2%;">A/C Name</th>
				   		<th style="width:2%;">Branch</th>
				   		<th style="width:2%;">Currency</th>
				   		<th style="width:2%;">Balance</th>
				   		<th style="width:2%;">Opening Balance</th>
				   		<th style="width:2%;">Closing Balance</th>
				   		<th style="width:2%;">Transaction Details</th>
				   		<th style="width:2%;">Close Account</th>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.accountNumber}"/></td>
                        <td><c:out value="${row.accountName}"/></td>
                        <td><c:out value="${row.branch}"/></td>
                        <td><c:out value="${row.currency}"/></td>
                        <td><c:out value="${row.balance}"/></td>
                        <td><c:out value="${row.openingBalance}"/></td>
                        <td><c:out value="${row.closingBalance}"/></td>
                        <td><a href="trans?id=${row.accountNumber}">View</a></td>
                        <td><a href= "acdelete?id=${row.accountNumber}">Close</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
              </div>
              </div>
		</div>
		<script>
		function change(){
			document.getElementById("msg").style.visibility = "hidden";
			document.getElementById("flex-container").style.marginTop = "0px";
			<%session.setAttribute("successMsg","empty");%>
		}
		</script>
</body>
</html>