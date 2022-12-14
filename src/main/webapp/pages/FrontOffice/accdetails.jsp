<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/main.css" type="text/css" />
<title>A/C Details</title>
<style>
body{
background-image:url("../cliparts/transaction1.jpg");
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
	<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../logout">LogOut</a>
			<a href="userhomes">Home</a>
		</div>
	<div class="card">
	    <h2 style="text-align: center;font-weight:normal;">Hi ${name}!!</h2>
		<h3 style="text-align: center;font-weight:normal;">Your A/C details for the account ${param.id} is as follows: </h3>
<%-- 		 <c:forEach var="row" items="${result}"> --%>
				   <table aria-label="AC Details" style="margin-left:5%; border-collapse:separate;border-spacing: 0 10px;color:rgb(163, 243, 155);width:85%;font-size:17px;;text-align:center;">
				   <tr>
				   		<th style="color:white;">A/C No</th>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${result.accountNumber}"/></td>
				   	</tr>
				   	<tr>
				   		<th style="color:white;">A/C Name</th>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${result.accountName}"/></td>
				   	</tr>
				   	<tr>
				   		<th style="color:white;">Branch</th>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${result.branch}"/></td>
				   	</tr>
				   	<tr>
				   		<th style="color:white;">Currency</th>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${result.currency}"/></td>
				   	</tr>
				   	<tr>
				   		<th style="color:white;">Balance</th>
				   		<td style="color:rgb(161, 52, 235);"><c:out value="${result.balance}"/></td>
				   	</tr>
                    <tr>
                    	<th style="color:white;">Opening Balance</th>
                        <td style="color:rgb(161, 52, 235);"><c:out value="${result.openingBalance}"/></td>
                     </tr>
                     <tr>
                     	<th style="color:white;">Closing Balance</th>
                         <td style="color:rgb(161, 52, 235);"><c:out value="${result.closingBalance}"/></td>
                      </tr>
                      <tr>
                      	<th style="color:white;">Transaction Details</th>
                        <td><a style="color:rgb(161, 52, 235);" href="transdetails?id=${result.accountNumber}">Details</a></td> 
                    </tr>
                    </table><br>
<%--                 </c:forEach> --%>
        </div>
</body>
</html>