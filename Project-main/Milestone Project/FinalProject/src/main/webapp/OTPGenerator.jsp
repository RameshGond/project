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
<title>Forgot Password</title>
<style>
form{
width:350px;
height:350px;
margin: 20px 500px 75px 420px;
background-color:snow;
border-radius: 22px;
padding-top: 40px;
opacity: 0.8;
}
h2{
color:black;
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
<body style= "background-image: url('https://cdn.pixabay.com/photo/2014/11/25/16/32/drop-545377__340.jpg'); background-repeat: no-repeat; background-size: cover;">
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
	<h2>Forgot Password</h2>
	${msg}
	<form action="otp" method="get" style="text-align: center;">
	<h3>Forgot Password</h3><br>
	<p>Enter your email and we'll send you a OTP to reset your password</p>
	   <input type="email" placeholder="Enter Your Email" name="email" required><br> <br>
		<input type="submit" value="Generate OTP" style="background-color: blue; color:white">
	</form>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>

</body>
</html>