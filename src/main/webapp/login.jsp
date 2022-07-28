<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<style>
.card {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 30%;
  margin-top: 10%;
  margin-left: auto;
  margin-right:auto;
}
</style>
</head>
<body>
<div class="card">
	<div class="nav">
		<h3>SMART BANK</h3>
	</div>
	<div>
	<h2 style="text-align: center;">LOGIN</h2>
	<form action="LoginServlet" method="get">
	<input type="hidden" value="login" name="action">
   		<table style="padding-left:15%;border-collapse:separate;border-spacing: 0 15px;">
		<tr>
  			<td>Email:</td>
  			<td><input type="email" name="email" placeholder="Enter email" style="height:30px;width:250px;"></td>
 		</tr>
 		<tr>
  			<td>Password:</td>
  			<td><input type="password" name="password" placeholder="Enter password" style="height:30px;width:250px;"></td>
 		</tr>
		</table><br>
  		<input type="submit" value="Login" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; border-color:blue; color:white;"><br>
  	</form>
  	</div>
</div>
</body>
</html>