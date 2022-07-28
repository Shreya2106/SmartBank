<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<title>FrontOffice</title>
<style>
.card {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 30%;
  margin-top:10%;
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
	<h2 style="text-align: center;font-weight:normal;">Change Password</h2>
	<form action="LoginServlet" method="get">
		<input type="hidden" value="changepassword" name="action">
   		<table style="padding-left:15%;border-collapse:separate;border-spacing: 0 15px;">
 		<tr>
  			<td>New Password:</td>
  			<td><input type="password" name="password1" placeholder="Enter password" style="height:30px;width:200px;"></td>
 		</tr>
 		<tr>
  			<td>Re-type Password:</td>
  			<td><input type="password" name="password2" placeholder="Enter password" style="height:30px;width:200px;"></td>
 		</tr>
		</table><br>
  		<input type="submit" value="Submit" style="background-color:blue; width:40%; height: 30px; margin:0px auto; display:block; border-color:blue; color:white;"><br>
  	</form>
</div>
</body>
</html>