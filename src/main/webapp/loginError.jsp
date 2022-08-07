<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<% if(response.getStatus() == 500){ %>
<h2 style="color:red;font-weight:bold;font-size:large;text-align:center;">*Error: <%=exception.getMessage() %></h2><br>

<%-- include login page --%>
<%@ include file="login.jsp"%>
<%}
else if(response.getStatus() == 400){ %>
<h2 style="color:red;font-weight:bold;font-size:large;text-align:center;">*Error: <%=exception.getMessage() %></h2><br>
Please go to <a href="login.jsp">login page</a>
<%}
else if(response.getStatus() == 200){ %>
<h2 style="color:red;font-weight:bold;font-size:30px;text-align:center;">Sorry!! The credentials are Invalid.
Please login using valid credentials. <a href="login.jsp">login page</a></h2>
<%}
else if(response.getStatus() == 404){ %>
<% session.invalidate();%>
<h2 style="color:red;font-weight:bold;font-size:30px;text-align:center;">Sorry!! The page can't be accessed.
You have been logged out. Please login again.</h2><h2 style="text-align:center;"><a href="login.jsp">login page</a></h2>
<%}
else {%>
Hi There, error code is <%=response.getStatus() %><br>
Please go to <a href="login.jsp">login page</a>
<%} %>
</body>
</html>