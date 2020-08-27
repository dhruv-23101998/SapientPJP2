<%@ page import="com.sapient.assignment.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.security.InvalidParameterException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Result of Operation</title>
		<link rel="stylesheet"  href="index.css">
	</head>
	<body>
		<%
			String loc=request.getParameter("type");
			int n=Integer.parseInt(loc);
			String ans="";
			try {
				if(n==1) {
					Calendar date1=DateOperations.stringToDate(request.getParameter("date1"));
					Calendar date2=DateOperations.stringToDate(request.getParameter("date2"));
					String oper=request.getParameter("operation");
					if(oper.equals("+"))
						ans=DateOperations.dateToString(DateOperations.addDates(date1,date2));
					else
						ans=DateOperations.substractDates(date1,date2);
				}
				else if(n==2) {
					Calendar date=DateOperations.stringToDate(request.getParameter("date"));
					int days=Integer.parseInt(request.getParameter("days"));
					String oper=request.getParameter("operation");
					if(oper.equals("-")) days=-days;
					ans=DateOperations.dateToString(DateOperations.addDaysToDate(date,days));
				}
				else if(n==3) {
					Calendar date=DateOperations.stringToDate(request.getParameter("date"));
					int weeks=Integer.parseInt(request.getParameter("weeks"));
					String oper=request.getParameter("operation");
					if(oper.equals("-")) weeks=-weeks;
					ans=DateOperations.dateToString(DateOperations.addWeeksToDate(date,weeks));
				}
				else if(n==4) {
					Calendar date=DateOperations.stringToDate(request.getParameter("date"));
					int months=Integer.parseInt(request.getParameter("months"));
					String oper=request.getParameter("operation");
					if(oper.equals("-")) months=-months;
					ans=DateOperations.dateToString(DateOperations.addMonthsToDate(date,months));
				}
				else if(n==5) {
					Calendar date=DateOperations.stringToDate(request.getParameter("date"));
					ans=DateOperations.findWeekday(date);
				}
				else if(n==6) {
					Calendar date=DateOperations.stringToDate(request.getParameter("date"));
					ans=DateOperations.findWeekNumber(date);
				}
				else if(n==7) {
					String phrase=request.getParameter("phrase");
					ans=DateOperations.dateToString(NaturalPhraseProcessor.convertNaturalPhrase(phrase));
				}
			} catch(InvalidParameterException ex) {
				ans="Invalid Input!";
			} catch(NullPointerException ex) {
				ans="Invalid Input!";
			} catch(NumberFormatException ex) {
				ans="Invalid Input!";
			}
		%>
		<h3>Result of Operation: <%=ans %></h3> <br/><br/>
		<a href="index.html">Back To Menu</a>
	</body>
</html>