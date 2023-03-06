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
<title>Sign In</title>
<style>
.main{
width:300px;
height:380px;
margin: 20px 500px 75px 490px;
background-color:snow;
border-radius: 22px;
padding-top: -8px;
opacity: 0.7;
}
label{
color:black;
}
form{
opacity: 1.5;
width:150px;
margin-left: 15px;
}
form label{
display:flex;
margin-top:25px;
font-size: 15px;
}
form input{
width: 250px;
padding: 2px;
border-radius: 7px;
}
h4{
text-align: left;
padding-top: 20px;
}
h2{
color:white;
}
p{
color:white;
}
h3{
color:white;
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
<body style= "background-image: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547__340.jpg'); background-repeat: no-repeat; background-size: cover;" >
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">

		<a class="navbar-brand" href="#"> <img
			src="https://img.freepik.com/premium-vector/letter-p-logo-power_42564-11.jpg?w=2000"
			alt="Logo" style="width: 50px;">
		</a>

		<ul class="nav navbar-nav ml-auto">
			<li class="nav-items"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-items"><a class="nav-link" href="ResetPassword.jsp">Reset Password</a></li>
		</ul>
	</nav>
	<h2>Sign In</h2>
	<h3>${msg}</h3>
	<h3>${mesg}</h3>
	<div class="main">
	<form action="login" method="post" style="text-align: center;">
	   <h4>Sign In</h4>
	   <label>Email:</label><input type="email" placeholder="Enter Your Email" name="email" required><br> 
		<label>Password:</label> <input type="text" placeholder="Enter your password" name="password"><br><br>
		<a href="OTPGenerator.jsp">Forgot Password</a><br><br>
		<input type="submit" value="Sign In" style="background-color: blue; color:white" ><br>
	</form>
	</div>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>
</body>
</html>