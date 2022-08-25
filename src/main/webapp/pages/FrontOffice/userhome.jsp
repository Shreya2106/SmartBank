<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/main.css" type="text/css" />
<title>UserHome</title>
<style>
html{
    height: 100%;
}
.flex-left{
	margin-left:5%;
	width:80%;
	background-color:#b1d3ee;
	height:50%;
}
.flex-right {
	margin-left:7%;
	margin-right:5%;
	align-content:right;
	width:30%;
}
body{
	background-image:url("cliparts/users.jpg");
	background-size:40%;
	background-repeat:no-repeat;
    background-position:bottom left;
    background-color:white;
    min-height: 95%;
}
</style>
</head>
<body>
	<% response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
    if (session.getAttribute("email") ==null || session.getAttribute("userType").equals("back")) {
     		response.sendRedirect("loginerrors");
     }%>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
        <sql:query dataSource="${dbsource}" var="result">
            select * from accounts where corporateId=(select corporateId from users where loginId="${email}");
        </sql:query>
        <sql:query dataSource="${dbsource}" var="result1">
             select corporateId,corporateName from corporate where corporateId=(select corporateId from users where loginId="${email}");
        </sql:query>
	<div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="logout">LogOut</a>
		</div>
		<c:forEach var="row1" items="${result1.rows}">
		<h2 style="text-align:center;font-weight:normal;">Welcome ${name} of Corporate ${row1.corporateName} - ${row1.corporateId}!!</h2>
		</c:forEach>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" style="margin-top:2%; height:100%;">
		<div class="flex-left">
			<h3 style="text-align: center;font-weight:normal;"><u>Account Summary</u></h3>
			<c:forEach var="row" items="${result.rows}">
				   <table  aria-label="User Home" class="ac">
				   <tr class="ac-r">
				   		<th>A/C No</th>
				   		<th>A/C Name</th>
				   		<th>Branch</th>
				   		<th>Currency</th>
				   		<th>Balance</th>
				   		<th>Account Details</th>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.accountNumber}"/></td>
                        <td><c:out value="${row.accountName}"/></td>
                         <td><c:out value="${row.branch}"/></td>
                          <td><c:out value="${row.currency}"/></td>
                            <td><c:out value="${row.balance}"/></td>
                        <td><a style="color:rgb(163, 243, 155);" href="accdetails?id=${row.accountNumber}">Details</a></td> 
                    </tr>
                    </table><br>
                </c:forEach>
		</div>
		<div class="flex-right">
		  <div id="ch" style="height:70%;">
			 <h3 style="text-align: center;font-weight:normal;">Want to add a transaction?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="transadd" style="visibility: hidden;">
			 	<form:form action="transadd" modelAttribute="transaction" method="post">
   					<table aria-label="Trans Add" style="padding-left:5%; width:100%;">
						<tr>
  							<th style="width: 30%;"><strong>A/C No:</strong></th>
  							<td><form:select path="accountNumber" id="acNo" style="width: 70%;" required="true">
  									<c:forEach var="row" items="${result.rows}">
  									<form:option value="${row.accountNumber}"><c:out value="${row.accountNumber}"/></form:option>
  									</c:forEach>
  								</form:select>
  							</td>
 						</tr>
 						<tr>
  							<th><strong>Trans Type:</strong></th>
  							<td><form:select path="transactionType" style="width: 70%;" required="true">
  									<form:option value="credit">Credit</form:option>
  									<form:option value="debit">Debit</form:option>
  								</form:select>
 						</tr>
 						<tr>
  							<th><strong>Trans Date:</strong></th>
  							<td><form:input type="date" path="transactionDate" style="width: 70%;" required="true" /></td>
 						</tr>
 						<tr>
  							<th><strong>Amount:</strong></th>
  							<td><form:input type="text" id="bal" path="balance" style="width: 70%;" title="Amount should be less than balance" pattern="[0-9]+" required="true" oninvalid="setCustomValidity('Invalid Amount')" onchange="try{setCustomValidity('')}catch(e){}" /></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form:form>
  				</div>
			 </div>
			 <script>
				function changeVisibility() {
  					document.getElementById("transadd").style.visibility = "visible";
  					document.getElementById("add").style.visibility = "hidden";
  					document.getElementById("ch").style.backgroundColor = "#b1d3ee";
  					document.getElementById("ch").style.boxShadow= "0 8px 16px 0 rgba(0,0,0,0.2)";
				}
				function change(){
					document.getElementById("msg").style.visibility = "hidden";
					<%session.setAttribute("successMsg","empty");%>
					document.getElementById("flex-container").style.marginTop = "0px";
						
				}
			</script>
		</div>
		</div>
	</div>
</body>
</html>