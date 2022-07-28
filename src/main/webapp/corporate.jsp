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
<title>Corporate Set-Up</title>
<style>
<style>
.flex-left{
	margin-left:10px;
	width:60%;
}
.flex-right {
	margin-left:20px;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	align-content:right;
	width:30%;
}
</style>
</head>
<body>
	<sql:setDataSource var="dbsource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/smartbank"
                           user="root"  password="#&AnaShree24##"/>
 
     <sql:query dataSource="${dbsource}" var="result">
            select * from corporate where softdeleted=0;
     </sql:query>
	 <div class="card">
		<div class="nav">
			<h3>SMART BANK</h3>
			<a href="LoginServlet?action=<c:out value="logout"/>">LogOut</a>
			<a href="user.jsp">User SetUp</a>
			<a href="account.jsp">Account SetUp</a>
		</div>
		<div class="flex-container">
		   <div class="flex-left">
			<h3 style="text-align: center;font-weight:normal;"><u>Our Corporate Customers</u></h3>
			<c:forEach var="row" items="${result.rows}">
				   <table class="ac">
				   <tr class="ac-r">
				   		<td>Corporate Id</td>
				   		<td>Corporate Name</td>
				   		<td>Address</td>
				   		<td>Phone Number</td>
				   		<td colspan="2">Action</td>
				   	</tr>
                    <tr>
                        <td><c:out value="${row.corporateId}"/></td>
                        <td><c:out value="${row.corporateName}"/></td>
                         <td><c:out value="${row.address}"/></td>
                          <td><c:out value="${row.contactNumber}"/></td>
                        <td><a href="corporateupdate.jsp?id=<c:out value="${row.corporateId}"/>">Update</a></td>
                        <td><a href= "LoginServlet?id=<c:out value="${row.corporateId}"/>&action=corpdelete">Delete</a></td>
                     </tr>
                    </table><br>
                </c:forEach>
		</div>
		<div class="flex-right">
			 <h3 style="text-align: center;font-weight:normal;">Want to add a corporate?</h3>
			 <button id="add" onclick="changeVisibility()">Add
			 </button>
			 <div id="corpadd" style="visibility: hidden;">
			 	<form action="LoginServlet" method="get">
					<input type="hidden" value="corpadd" name="action">
   					<table style="padding-left:5%;border-collapse:separate; width:100%;">
						<tr>
  							<td style="width:100px;">Corporate Name</td>
  							<td><input type="text" name="corpName" style="width:70%;"></td>
 						</tr>
						<tr>
  							<td>Address</td>
  							<td><input type="text" name="corpAddress" style="width: 70%;"></td>
 						</tr>
 						<tr>
  							<td>Contact Number</td>
  							<td><input type="text" name="corpPh" style="width: 70%;"></td>
 						</tr>
					</table><br>
  					<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; color:white; border-color:blue;"><br>
  				</form>
			 </div>
			 </div>
			 </div>
			 <script>
				function changeVisibility() {
  					document.getElementById("corpadd").style.visibility = "visible";
  					document.getElementById("add").style.visibility = "hidden";
				}
			</script>
	</div>
</body>
</html>