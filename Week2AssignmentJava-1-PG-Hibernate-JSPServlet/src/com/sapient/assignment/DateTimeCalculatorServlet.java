package com.sapient.assignment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DateTimeCalculatorServlet")
public class DateTimeCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DateTimeCalculatorServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String loc=request.getParameter("operation");
		if(loc.equals("1")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view1.jsp");
			dispatcher.forward(request,response);
		}
		else if(loc.equals("2")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view2.jsp");
			dispatcher.forward(request,response);
		}
		else if(loc.equals("3")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view3.jsp");
			dispatcher.forward(request,response);
		}
		else if(loc.equals("4")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view4.jsp");
			dispatcher.forward(request,response);
		}
		else if(loc.equals("5")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view5.jsp");
			dispatcher.forward(request,response);
		}
		else if(loc.equals("6")) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view6.jsp");
			dispatcher.forward(request,response);
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("view7.jsp");
			dispatcher.forward(request,response);
		}
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}