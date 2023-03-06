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
<title>Enter product Details</title>
<style type="text/css">
.main {
	width: 300px;
	height: 475px;
	margin: 20px 500px 75px 490px;
	background-color: snow;
	border-radius: 22px;
	padding-top: -8px;
	opacity: 0.9;
}

h6 {
	text-align: center;
	padding-top: 20px;
}

h2 {
	color: white;
}

form {
	opacity: 1.5;
	width: 270px;
	margin-left: 20px;
}

form label {
	display: flex;
	margin-top: 10px;
	font-size: 15px;
}

form input {
	width: 250px;
	padding: 2px;
	border-radius: 6px;
}

p {
	text-align: center;
	padding-top: 10px;
	font-size: 13px;
}

h5 {
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
<body
	style="background-image: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547__340.jpg'); background-repeat: no-repeat; background-size: cover;">
	<c:forEach items="${Data}" var="profile">
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="#"> <img
			src="https://img.freepik.com/premium-vector/letter-p-logo-power_42564-11.jpg?w=2000"
			alt="Logo" style="width: 50px;">
		</a>

		<ul class="nav navbar-nav ml-auto">
			<li class="nav-items"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-items"><a class="nav-link" href="SignIn.jsp">SignIn</a></li>
		</ul>
	</nav>
	<h2>Enter Product Details Here</h2>
	<h5>${message}</h5>
	<div class="main">
		<form action="addp" method="Post" enctype="multipart/form-data"
			style="text-align: left;">
			<h6>Product details</h6>

			<label>Product Name</label> <input type="text"
				placeholder="Enter Product Name" name="productName" required><br>
			<label>Admin Email:</label> <input type="email"
				value="${profile.email}" name="email" disabled="disabled"><br>
			 <input type="email"name="email" value="${profile.email}" hidden="hidden">
			<label>Category</label><select name="category">
			<option>Fashion</option>
			<option>Home Appliances</option>
			<option>Electronics</option>
			<option>Books</option>
			</select><br> 
			<label>Price</label>
			<input type="tel" placeholder="Enter the Price" name="price"><br>
			<label>Product Image</label><input type="file" name="image" /><br>
	<input type="submit" value="Add Product" 
		style="background-color: blue; color: white;">
	</form>
	</div>
	<div class="pro">
	<a href = "addp/show?email=${profile.email}"><button style="position: absolute;top: 610px;">Display Products</button></a>
	</div>
	</c:forEach>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>
	</body>
	</html>