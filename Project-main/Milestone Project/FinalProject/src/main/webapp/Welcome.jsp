<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<title>Welcome to Your Page</title>
<style type="text/css">
h1{
text-align: center;
color: white;
}
click{
background-color: blue;
color: white;
text-align: center;
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
<body style= "background-image: url('https://wallpaperaccess.com/full/3214390.jpg'); background-repeat: no-repeat; background-size: cover;" >
<c:forEach items="${name}" var="profile">
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">

		<a class="navbar-brand" href="#"> <img
			src="https://img.freepik.com/premium-vector/letter-p-logo-power_42564-11.jpg?w=2000"
			alt="Logo" style="width: 50px;">
		</a>

		<ul class="nav navbar-nav ml-auto">
		<img src="project/image?fileName=${profile.fileName}" style="width:57px; border-radius: 50px;">
			<li class="nav-items"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-items"><a class="nav-link" href="mail?email=${profile.email}">Add Products</a></li>
		</ul>
	</nav>
<div class="profile" style="color: white; text-align:center;" >
UserName:${profile.userName}<br><br>
Email:${profile.email}<br><br>
Phone Number:${profile.number}<br><br>
<a href="show/prath?email=${profile.email}">
<input type="submit" value="Edit"></input></a>
</div>
</c:forEach>
<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>
</body>
</html>