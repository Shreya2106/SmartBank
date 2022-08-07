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
	background-image:url("../ImagesServlet?action=user");
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
     		response.sendRedirect("../loginError.jsp");
     }%>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
        <sql:query dataSource="${dbsource}" var="result">
            select * from accounts where corporateId=(select corporateId from users where loginId="${email}");
        </sql:query>
	<div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
		</div>
		<h2 style="text-align:center;font-weight:normal;">Welcome ${name}!!</h2>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" style="margin-top:2%; height:100%;">
		<div class="flex-left">
			<h3 style="text-align: center;font-weight:normal;"><u>Account Summary</u></h3>
			<c:forEach var="row" items="${result.rows}">
				   <table class="ac">
				   <tr class="ac-r">
				   		<td>A/C No</td>
				   		<td>A/C Name</td>
				   		<td>Branch</td>
				   		<td>Currency</td>
				   		<td>Balance</td>
				   		<td>Account Details</td>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.accountNumber}"/></td>
                        <td><c:out value="${row.accountName}"/></td>
                         <td><c:out value="${row.branch}"/></td>
                          <td><c:out value="${row.currency}"/></td>
                            <td><c:out value="${row.balance}"/></td>
                        <td><a style="color:rgb(163, 243, 155);" href="accdetails.jsp?id=<c:out value="${row.accountNumber}"/>">Details</a></td> 
                    </tr>
                    </table><br>
                </c:forEach>
		</div>
		<div class="flex-right" id="ch">
			 <h3 style="text-align: center;font-weight:normal;">Want to add a transaction?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="transadd" style="visibility: hidden;">
			 	<form action="../LoginServlet" method="get">
					<input type="hidden" value="transadd" name="action">
   					<table style="padding-left:5%; width:100%;">
						<tr>
  							<td style="width: 30%;"><b>A/C No:</b></td>
  							<td><select name="acNo" id="acNo" style="width: 70%;" required>
  									<c:forEach var="row" items="${result.rows}">
  									<option><c:out value="${row.accountNumber}"/></option>
  									</c:forEach>
  								</select>
  							</td>
 						</tr>
 						<tr>
  							<td><b>Trans Type:</b></td>
  							<td><select name="transType" style="width: 70%;" required>
  									<option value="credit">Credit</option>
  									<option value="debit">Debit</option>
  								</select>
 						</tr>
 						<tr>
  							<td><b>Trans Date:</b></td>
  							<td><input type="date" name="transDate" style="width: 70%;" required></td>
 						</tr>
 						<tr>
  							<td><b>Amount:</b></td>
  							<td><input type="text" id="bal" name="balance" style="width: 70%;" title="Amount should be less than balance" pattern="[0-9]+" required oninvalid="setCustomValidity('Invalid Amount')" onchange="try{setCustomValidity('')}catch(e){}"></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form>
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
				}
			</script>
		</div>
		</div>
	</div>
</body>
</html>