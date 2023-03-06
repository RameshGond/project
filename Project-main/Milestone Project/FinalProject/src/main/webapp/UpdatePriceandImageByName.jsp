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
<title>Update Product By Email And Name</title>
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
			<%-- //<img src="show/prath/image?fileName=${product.fileName}" style="width:57px; border-radius: 50px;"> --%>
		</ul>
	</nav>
	<h2>Update Product</h2>
	${msg}
	<form action="updatedProduct" method="Post" enctype="multipart/form-data" style="text-align: center;">
	 <label>Admin Email:</label>
	 <input type="email" name="email" value="${product.email}" disabled="disabled"><br>
	  <input type="email"name="email" value="${product.email}" hidden="hidden">
     <label>Product Name</label>
     <input type="text" placeholder="Enter Product Name" name="productName"><br>
     <label>Product Price</label>
     <input type="number" placeholder="Enter new Price" name="price"><br>
     <label>Update Image</label>
     <input type="file" name="image"><br>
		<input type="submit" value="Update Profile" style="background-color: blue; color: white">
	</form>
	<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>

</body>
</html>