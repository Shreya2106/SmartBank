<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="loginError.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
<style>
body{
background-image:url("resources/cliparts/login.jpg");
background-size:50%;
background-repeat:no-repeat;
background-position:0 50%;
background-color:white;
}
.cards{
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  width: 30%;
  margin-top: 7%;
  background-color:#b1d3ee;
  margin-left: 60%; 
}
.nav{
text-align:center;
}
th{
text-align:left;
font-weight:normal;
padding:0px;
}
</style>
</head>
<body>
<h1 style="text-align:center; color:#33d4e2;">SMART BANK</h1>
   <div class="cards">
	   <div class="nav" style="background-color:#8d88e9;">
			<h3 style="font-family:serif;font-weight:bold;text-align:center;font-size:large;">LOGIN</h3>
	   </div><br>
	<div>
	<form action="loginprocess">
	    <table aria-label="Login Form" style="padding-left:20%;border-collapse:separate;border-spacing: 0 15px;">
		<tr><th id="email">Email:</th></tr>
  		<tr><td><input type="email" name="email" placeholder="Enter email" title="Enter your email/loginId" style="height:30px;width:250px;" required></td>
 		</tr>
 		<tr><th id="pass">Password:</th></tr>
  		<tr><td><input type="password" name="password" placeholder="Enter password" style="height:30px;width:250px;" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
  		pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" oninvalid="setCustomValidity('Password must contain a lowercase, a uppercase, a number and minimum 8 characters')"
    onchange="try{setCustomValidity('')}catch(e){}"></td>
 		</tr>
		</table><br>
  		<input type="submit" value="Login" style="background-color:#8d88e9;; width:40%; height: 30px; margin:0px auto; display:block; border-color:#8d88e9; color:white;"><br>
  	 </form>
  	</div>
  </div>
</body>
</html>