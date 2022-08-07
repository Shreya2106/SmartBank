<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
*{
	font-family:serif;
	font-weight:bold;
}
</style>
</head>
<body>
	<% response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
    if (session.getAttribute("email") ==null || session.getAttribute("userType").equals("back")) {
     		response.sendRedirect("loginError.jsp");
     }%>
		<% if(response.getStatus() == 500){ %>
		<h2 style="color:red;font-weight:bold;font-size:large;text-align:center;">*Error: <%=exception.getMessage() %></h2><br>
		<%@ include file="frontoffice.jsp"%>
		<%}
		else if(response.getStatus() == 200){ %>
		<div style="text-align:center;">
		<h2 style="font-family:serif;color:red;font-weight:bold;font-size:large;text-align:center;"> * ${errorMsg} </h2>
		</div>
		<%-- include login page --%>
		<%@ include file="frontoffice.jsp"%>
		<%}
		else {%>
		<div class="nav">
			<h3 style="font-size:large;">SMART BANK</h3>
		</div>
		<div style="margin-top:5%;text-align:center;">
			<h1>Oops!! We are sorry. There was an error.</h1>
			<h2>Would you like to
			<a href="frontoffice.jsp">try again</a>?
			</h2>
		</div>
		<%} %>
</body>
</html>