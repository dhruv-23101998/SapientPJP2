<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Date Time Calculator</title>
		<style>
		.page {
			background-color:white;
			font-family: 'Kavivanar', cursive;
			font-size:100%;
			padding:20px;
			margin:auto;
		}
		input[type="submit"] { 
			background-color:white; 
			font-family: 'Kavivanar', cursive;
			color: solid black; 
			border: solid black 2px; 
			padding:15px;
		}
		</style>
	</head>
	<body class="page">
		<header><h1>Date Time Calculator Application</h1></header>
		<h2>Menu of Date Time Calculator</h2>
		<form action="/view1">
			<input type="submit" value="Add/Substract Two Dates"/>
		</form>
		<br/>
		<form action="/view2">
			<input type="submit" value="Add/Substract Days From Date"/>
		</form>
		<br/>
		<form action="/view3">
			<input type="submit" value="Add/Substract Weeks From Date"/>
		</form>
		<br/>
		<form action="/view4">
			<input type="submit" value="Add/Substract Months From Date"/>
		</form>
		<br/>
		<form action="/view5">
			<input type="submit" value="Find Weekday Given Date"/>
		</form>
		<br/>
		<form action="/view6">
			<input type="submit" value="Find Week Number Given Date"/>
		</form>
		<br/>
		<form action="/view7">
			<input type="submit" value="Natural Language Phrases Converter"/>
		</form>
	</body>
</html>