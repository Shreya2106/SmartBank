<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<% if(response.getStatus() == 404){ %>
<h1 style="color:red;font-weight:bold;font-size:30px;text-align:center;">Sorry the page you are requesting does not exist!! Give a correct address or <a href="login">Login</a>
</h1><br>
<%} else if(response.getStatus() == 403){ %>
<h1 style="color:red;font-weight:bold;font-size:30px;text-align:center;">Sorry you are not authorized yet to access it.<br><br> Please Login First as a authorized user of that page.<br><a href="../login">Login</a>
</h1><br>
<%} else if(response.getStatus() == 200){ %>
<h2 style="color:red;font-weight:bold;font-size:large;text-align:center;">*Error: ${errorMsg}.
</h2><br>
<%@ include file="login.jsp"%>
<%} else{ %>
<h2 style="color:red;font-weight:bold;font-size:large;text-align:center;">*Error: ${errorMsg} </h2><br>
<%@ include file="login.jsp"%>
<%} %>
</body>
</html>