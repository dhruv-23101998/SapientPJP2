<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Natural Language Phrases Converter</title>
	</head>
	<body>
		<h2>Provide Following Inputs To Get Result of Operation</h2>
		<h3>Operation - Natural Language Phrases Converter</h3>
		<form action="OperationServlet" method="GET">
			<input type="hidden" name="type" value="7" readonly>
			Enter Phrase: <input type="text" name="phrase"/>
			<br/><br/>
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<a href="index.html">Back To Menu</a>
	</body>
</html>