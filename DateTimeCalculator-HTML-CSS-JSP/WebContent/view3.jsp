<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Add/Substract Weeks From Date</title>
	<link rel="stylesheet"  href="index.css">
	</head>
	<body>
		<h2>Provide Following Inputs To Get Result of Operation</h2>
		<h3>Operation - Add/Substract Weeks From Date</h3>
		<form action="result.jsp">
			<input type="hidden" name="type" value="3" readonly>
			Enter Date (DD/MM/YYYY): <input type="text" name="date"/>
			<br/><br/>
			Enter Weeks: <input type="text" name="weeks"/>
			<br/><br/>
			<input type="radio" name="operation" value="+"> Add Weeks To Date
			<br/><br/>
			<input type="radio" name="operation" value="-"> Substract Weeks To Date
			<br/><br/>
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<a href="index.html">Back To Menu</a>
	</body>
</html>