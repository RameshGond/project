<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
<title>Reset Password</title>
<style>
form{
width:400px;
height:475px;
margin: 10px 500px 75px 450px;
background-color:snow;
border-radius: 22px;
padding-top: 40px;
opacity: 0.8;
}
form label{
color:black;
display:flex;
margin-top:25px;
font-size: 15px;
margin-left: 25px;
}
form input{
width: 300px;
padding: 2px;
border-radius: 7px;
margin-right: 50px;
}
h4{
text-align: Center;
padding-top: -10px;
}
.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: black;
	color: white;
	text-align: center;
}

</style>
</head>
<body style= "background-image: url('https://cdn.pixabay.com/photo/2014/11/25/16/32/drop-545377__340.jpg');background-repeat: no-repeat; background-size: cover;">
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">

		<a class="navbar-brand" href="#"> <img
			src="https://img.freepik.com/premium-vector/letter-p-logo-power_42564-11.jpg?w=2000"
			alt="Logo" style="width: 50px;">
		</a>

		<ul class="nav navbar-nav ml-auto">
			<li class="nav-items"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-items"><a class="nav-link" href="SignIn.jsp">Sign In</a></li>
		</ul>
	</nav>
	<h2>Reset Password</h2>
	${msg}
	<form action="pass" method="get" style="text-align: center;">
	    <h4>Reset Password</h4>
	    <p>Password Should have characters between 10-12</p>
	   <label>Email:</label><input type="email" placeholder="Enter Your Email" name="email" required><br> 
	   <label>Enter OTP</label><input type="text" placeholder="Enter OTP" name="otp"><br>
		<label>Enter New Password:</label> <input type="text" placeholder="Enter your password" name="password"><br><br>
		<input type="submit" value="Reset Password" style="background-color: blue; color: white">
	</form>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>

</body>
</html>