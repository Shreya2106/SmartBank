<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="/passError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css" />
<title>FrontOffice</title>
<style>
body{
background-image:url("../ImagesServlet?action=password");
background-size:35%;
background-repeat:no-repeat;
background-position:0 50%;
background-color:white;
}
.cards {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 30%;
  margin-top:5%;
  background-color:#b1d3ee;
  margin-left: 40%; 
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
<h1 style="text-align:center; color:#33d4e2;">SMART BANK</h1>
   <div class="cards">
	 <div class="nav" style="background-color:#8d88e9;">
		<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">CHANGE PASSWORD</h3>
	  </div><br>
	  <div>
	    <form action="../LoginServlet" method="get">
		<input type="hidden" value="changepassword" name="action">
   		<table style="padding-left:20%;border-collapse:separate;border-spacing: 0 15px;">
 		<tr><td>New Password:</td></tr>
  		<tr><td><input type="password" name="password1" placeholder="Enter password" style="height:30px;width:200px;" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
  	       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required oninvalid="setCustomValidity('Password must contain a lowercase, a uppercase, a number and minimum 8 characters')"
           onchange="try{setCustomValidity('')}catch(e){}"></td>
 		</tr>
 		<tr><td>Re-type Password:</td></tr>
  		<tr><td><input type="password" name="password2" placeholder="Enter password" style="height:30px;width:200px;" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
  		pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required oninvalid="setCustomValidity('Password must contain a lowercase, a uppercase, a number and minimum 8 characters')"
        onchange="try{setCustomValidity('')}catch(e){}"></td>
 		</tr>
		</table><br>
  		<input type="submit" value="Submit" style="background-color:#8d88e9; width:40%; height: 30px; margin:0px auto; display:block; border-color:#8d88e9; color:white;"><br>
  	</form>
  	</div>
</div>
</body>
</html>