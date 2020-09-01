<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Natural Language Phrases Converter</title>
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
		<h2>Provide Following Inputs To Get Result of Operation</h2>
		<h3>Operation - Natural Language Phrases Converter</h3>
		<form method="POST">
			<input type="hidden" name="type" value="7" readonly>
			Enter Phrase: <input type="text" name="phrase"/>
			<br/><br/>
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<a href="/index">Back To Menu</a>
	</body>
</html>