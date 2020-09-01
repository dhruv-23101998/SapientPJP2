<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Result of Operation</title>
		<style>
			body {
				background-color:white;
				font-family: 'Kavivanar', cursive;
				font-size:100%;
			}
			header {
				font:
			}
			input[type="submit"] { 
				background-color:white; 
				font-family: 'Kavivanar', cursive;
				color: solid black; 
				border: solid black 2px; 
				padding:10px;
			}
		</style>
	</head>
	<body>
		<h3>Result of Operation: ${ans}</h3> <br/><br/>
		<a href="/index">Back To Menu</a>
	</body>
</html>