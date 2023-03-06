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
<title>Welcome To Home Page</title>
<style>
h3{
color: white;
}
p{
color: white;
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
<body style= "background-image: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547__340.jpg'); background-repeat: no-repeat; background-size: cover;">
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">

		<a class="navbar-brand" href="#"> <img
			src="https://img.freepik.com/premium-vector/letter-p-logo-power_42564-11.jpg?w=2000"
			alt="Logo" style="width: 50px;">
		</a>

		<ul class="nav navbar-nav ml-auto">
			<li class="nav-items"><a class="nav-link" href="SignUp.jsp">Sign Up</a></li>
			<li class="nav-items"><a class="nav-link" href="SignIn.jsp">Sign In</a></li>
		</ul>
	</nav>
	<p>${msg}</p>
	<!-- Sample page content -->
	<div class="container">
		<h3>Social is Superpower</h3>

		<p>Sign Up to connect and interact with like minded folks from each corner of the world</p>
	</div>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>
</body>
</html>
