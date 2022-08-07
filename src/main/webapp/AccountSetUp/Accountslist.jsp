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
html{
    height: 100%;
}
body{
	background-image:url("../ImagesServlet?action=account_list");
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
     		response.sendRedirect("../loginError.jsp");
     }%>
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
	 <div>
		<div class="nav">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">SMART BANK</h3>
			<a href="../LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="../CorporateSetUp/corporate.jsp">Corporate SetUp</a>
			<a href="account.jsp">Account SetUp</a>
			<a href="../UserSetUp/user.jsp">User SetUp</a>
		</div>
		<% Object successMsg = session.getAttribute("successMsg");
			    if(!successMsg.equals("empty")) { %>
					<p id="msg" style="color:blue; text-align:center;">${successMsg}<button onclick="change()">Close</button></p>
                <% } %>
		<div class="flex-container" style="margin-top:2%; height:100%;">
		<div class="cards">
		<h2 style="text-align: center;font-weight:normal;"><u>Accounts List of Corporate ${param.cid}</u></h2>
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
                        <td><a href= "../LoginServlet?id=<c:out value="${row.accountNumber}"/>&action=accountdelete">Close</a></td>
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