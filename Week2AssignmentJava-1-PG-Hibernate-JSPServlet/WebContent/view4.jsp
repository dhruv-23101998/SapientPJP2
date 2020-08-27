<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Add/Substract Months From Date</title>
	</head>
	<body>
		<h2>Provide Following Inputs To Get Result of Operation</h2>
		<h3>Operation - Add/Substract Months From Date</h3>
		<form action="OperationServlet" method="GET">
			<input type="hidden" name="type" value="4" readonly>
			Enter Date (DD/MM/YYYY): <input type="text" name="date"/>
			<br/><br/>
			Enter Months: <input type="text" name="months"/>
			<br/><br/>
			<input type="radio" name="operation" value="+"> Add Months To Date
			<br/><br/>
			<input type="radio" name="operation" value="-"> Substract Months To Date
			<br/><br/>
			<input type="submit" value="Submit"/>
		</form>
		<br/><br/>
		<a href="index.html">Back To Menu</a>
	</body>
</html>