package com.sapient.assignment;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OperationServlet")
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public OperationServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String loc=request.getParameter("type");
		String ans="";
		int n=Integer.parseInt(loc);
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
		if(n>=1 && n<=7) {
			request.setAttribute("result",ans);
			RequestDispatcher dispatcher=request.getRequestDispatcher("result.jsp");
			dispatcher.forward(request,response);
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}