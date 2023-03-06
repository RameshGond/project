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
<script type="text/javascript">
function change_button(checkbox,button_id){
	var btn=document.getElementById(button_id);
	if(checkbox.checked==true){
		btn.disabled="";
	}
	else{
		btn.disabled="disabled";
	}
	}

</script>
<title>Sign Up</title>
<style type="text/css">
.main{
width:300px;
height:475px;
margin: 20px 500px 75px 490px;
background-color:snow;
border-radius: 22px;
padding-top: -8px;
opacity: 0.9;
}
h6{
text-align: center;
padding-top: 20px;
}
h2{
color: white;
}
form{
opacity: 1.5;
width:270px;
margin-left: 20px;
}
form label{
display:flex;
margin-top:10px;
font-size: 15px;
}
form input{
width: 250px;
padding: 2px;
border-radius: 6px;
}
p{
text-align: center;
padding-top: 10px;
font-size: 13px;
}
h5{
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
			<li class="nav-items"><a class="nav-link" href="SignIn.jsp">Sign In</a></li>
		</ul>
	</nav>
	<h2>Sign Up</h2>
	<h5>${message}</h5>
	<div class="main">
	<form action="project" method="post"  enctype="multipart/form-data" style="text-align: left;">
	<h6>SIGN UP</h6>

		<label>Name:</label> <input type="text" placeholder="Enter Your Name" name="userName" required><br>
	    <label>Email:</label> <input type="email" placeholder="Enter Your Email" name="email" required><br> 
		<label>Mobile Number:</label> <input type="tel" placeholder="Enter the number" name="number"><br>
		<label>Profile Picture</label><input type="file" name="image"/><br>
		<div class="check">
		<label>By Clicking on the Checkbox You Agree to our Terms and Conditions</label><input type="checkbox" onclick="change_button(this,'sub')">
		</div>
		<input type="submit" value="Sign Up" id="sub" disabled="disabled" style="background-color:blue; color:white;" >
	</form>
	</div>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>
</body>
</html>