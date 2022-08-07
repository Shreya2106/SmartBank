<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Close</title>
</head>
<body>
	<% response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);
	if (session.getAttribute("email") ==null || session.getAttribute("userType").equals("front")) {
     		response.sendRedirect("../loginError.jsp");
     }%>
<form action="LoginServlet">
  <input type="hidden" value="${param.id}" name="id" />
  <input type="hidden" value="accountdelete" name="action"/>
  <input type="submit" value="delete"/>
</form>
</body>
</html>