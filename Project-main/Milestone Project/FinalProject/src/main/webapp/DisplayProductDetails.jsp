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
<title>Product Details</title>
</head>
<body
	style="background-image: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547__340.jpg'); background-repeat: no-repeat; background-size: cover;">
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
	<h2>Product Details</h2>
	<div class="tab">
	<table border="1" style="color: white">
	<thead>
	<tr>
	<th>Product ID</th>
	<th>Vendor Email</th>
	<th>Product Name</th>
	<th>Product Category</th>
	<th>Product Price</th>
	<th>Product Image</th>
	<th>Created Time</th>
	<th>Created Date</th>
	<th>Update Product</th>
	</tr>
	<c:forEach items="${products}" var="pro">
	<tr>
	<td>${pro.id}</td>
	<td>${pro.email}</td>
	<td>${pro.productName}</td>
	<td>${pro.category}</td>
	<td>${pro.price}</td>
	<td><img src="product/image?fileName=${pro.fileName}" style="width:100px; height:100px;"></td>
	<td>${pro.createdDate}</td>
	<td>${pro.createdTime}</td>
	<td><a href="prath?email=${pro.email}">Update Product</a></td>
	</c:forEach>
	</table>
	</div>
<div class="footer">
		<p>&copy; 2022 Pratheek,All Rights Reserved</p>
	</div>
</body>
</html>