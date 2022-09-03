<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Transaction Details</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
<style>
html{
    height: 100%;
}
body{
	background-image:url("../cliparts/trans.jpg");
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
     <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../logout">LogOut</a>
			<a href="corporates">Corporate SetUp</a>
			<a href="account">Account SetUp</a>
			<a href="user">User SetUp</a>
		</div>
		<div class="cards">
			<h2 style="text-align: center;font-weight:normal;"><u>Transaction Details of a/c no: "${cid}" </u></h2>
			<c:forEach var="row" items="${result}">
				   <table  aria-label="Trans Details" style="padding-left:2px;border:1px solid rgb(161,52,235);background-color:rgb(161, 52, 235);color:rgb(163, 243, 155);width:100%;text-align:center;">
				   <tr style="color:white; text-decoration: underline;">
				   <th style="width:15%;">Trans ID</th>
				   <th style="width:15%;">Trans Type</th>
				   <th style="width:15%;">Trans Date</th>
				   <th style="width:15%;">Amount</th></tr>
                    <tr>
                        <td><c:out value="${row.transactionId}"/></td>
                        <td><c:out value="${row.transactionType}"/></td>
                        <td><c:out value="${row.transactionDate}"/></td>
                        <td><c:out value="${row.balance}"/></td>
                    </tr>
                    </table><br>
                </c:forEach>
		</div>
	</div>
</body>
</html>