<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
</head>
<body>
	<% response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
    if (session.getAttribute("email") ==null  || session.getAttribute("userType").equals("back")) {
     		response.sendRedirect("loginerrors");
     }%>
		<% if(response.getStatus() == 200){ %>
		<div style="text-align:center;">
		<h2 style="font-family:serif;color:red;font-weight:bold;font-size:large;text-align:center;"> * ${errorMsg} </h2>
		</div>
		<%-- include login page --%>
		<%@ include file="userhome.jsp"%>
		<%}
		else {%>
		<div class="nav">
			<h3 style="font-size:large;">SMART BANK</h3>
		</div>
		<div style="margin-top:5%;text-align:center;">
			<h1>Oops!! We are sorry. There was an error.</h1>
			<h2>Would you like to
			<a href="userhome">go back</a> or
			<a href="logout">LogOut</a>?
			</h2>
		</div>
		<%} %>
</body>
</html>