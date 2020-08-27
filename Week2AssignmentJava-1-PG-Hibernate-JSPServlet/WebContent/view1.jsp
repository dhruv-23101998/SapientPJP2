<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Add/Substract Two Dates</title>
	</head>
	<body>
		<h2>Provide Following Inputs To Get Result of Operation</h2>
		<h3>Operation - Add/Substract Two Dates</h3>
		<form action="OperationServlet" method="GET">
			<input type="hidden" name="type" value="1" readonly>
			Enter Date 1 (DD/MM/YYYY): <input type="text" name="date1"/>
			<br/><br/>
			Enter Date 2 (DD/MM/YYYY): <input type="text" name="date2"/>
			<br/><br/>
			<input type="radio" name="operation" value="+"> Add Dates
			<br/><br/>
			<input type="radio" name="operation" value="-"> Substract Dates
			<br/><br/>
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<a href="index.html">Back To Menu</a>
	</body>
</html>