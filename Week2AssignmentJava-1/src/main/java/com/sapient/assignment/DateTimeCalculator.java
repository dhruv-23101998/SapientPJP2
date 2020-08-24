package com.sapient.assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.*;

public class DateTimeCalculator {
	private ArrayList<String> menu;
	private ArrayList<String> history;
	private ArrayList<String> sessionHistory;
	private ArrayList<String> localHistory;
	Scanner in=new Scanner(System.in);
	DateTimeCalculator() {
		this.menu=new ArrayList<>();
		this.history=new ArrayList<>();
		this.sessionHistory=new ArrayList<>();
		this.menu.add("Enter 1 - Add/Substract Two Dates");
		this.menu.add("Enter 2 - Add/Substract Days From Date");
		this.menu.add("Enter 3 - Add/Substract Weeks From Date");
		this.menu.add("Enter 4 - Add/Substract Months From Date");
		this.menu.add("Enter 5 - Find Weekday Given Date");
		this.menu.add("Enter 6 - Find Week Number Given Date");
		this.menu.add("Enter 7 - Natural Language Phrases Converter");
		this.menu.add("Enter 8 - See The History");
		this.menu.add("Enter 9 - Clear History");
		this.menu.add("Enter 10 - See The Session History");
		this.menu.add("Enter 0 - To Exit The Application");
	}
	public ArrayList<String> getMenu() {
		return menu;
	}
	private String takeInput() {
		String str=in.nextLine();
		localHistory.add("Input - "+str);
		return str;
	}
	public void calculate(int n) throws ParseException {
		localHistory=new ArrayList<>();
		localHistory.clear();
		String ans="";
		if(n==1) {
			localHistory.add("Operation - Add/Substract Two Dates");
			localHistory.add("Enter Date 1(DD/MM/YYYY)");
			System.out.println("Enter Date 1(DD/MM/YYYY)");
			Calendar date1=DateOperations.stringToDate(takeInput());
			localHistory.add("Enter Date 2(DD/MM/YYYY)");
			System.out.println("Enter Date 2(DD/MM/YYYY)");
			Calendar date2=DateOperations.stringToDate(takeInput());
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("+"))
				ans=DateOperations.dateToString(DateOperations.addDates(date1,date2));
			else 
				ans=DateOperations.substractDates(date1,date2);
		}
		else if(n==2) {
			localHistory.add("Operation - Add/Substract Days From Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput());
			localHistory.add("Enter Days");
			System.out.println("Enter Days");
			int days=Integer.parseInt(takeInput());
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("-")) days=-days;
			ans=DateOperations.dateToString(DateOperations.addDaysToDate(date,days));
		}
		else if(n==3) {
			localHistory.add("Operation - Add/Substract Weeks From Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput());
			localHistory.add("Enter Weeks");
			System.out.println("Enter Weeks");
			int weeks=Integer.parseInt(takeInput());
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("-")) weeks=-weeks;
			ans=DateOperations.dateToString(DateOperations.addWeeksToDate(date,weeks));
		}
		else if(n==4) {
			localHistory.add("Operation - Add/Substract Months From Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput());
			localHistory.add("Enter Months");
			System.out.println("Enter Months");
			int months=Integer.parseInt(takeInput());
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("-")) months=-months;
			ans=DateOperations.dateToString(DateOperations.addMonthsToDate(date,months));
		}
		else if(n==5) {
			localHistory.add("Operation - Find Weekday Given Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput());
			System.out.println(DateOperations.findWeekday(date));
		}
		else if(n==6) {
			localHistory.add("Operation - Find Week Number Given Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput());
			ans=DateOperations.findWeekNumber(date);
		}
		else if(n==7) {
			localHistory.add("Operation - Natural Language Phrases Converter");
			localHistory.add("Enter Natural Language Phrase To Be Converted To Date");
			System.out.println("Enter Natural Language Phrase To Be Converted To Date");
			String phrase=takeInput();
			ans=DateOperations.dateToString(NaturalPhraseProcessor.convertNaturalPhrase(phrase));
		}
		else if(n==8) {
			if(history.size()==0) {
				ans="No History Found!";
				System.out.println(ans);
			}
			else {
				for(int i=0;i<history.size();i++) {
					System.out.println(history.get(i));
				}
			}
		}
		else if(n==9) {
			sessionHistory.add("Operation - Clear History");
			history.clear();
			ans="History Cleared!";
			sessionHistory.add("History Cleared!");
			sessionHistory.add(" ");
		}
		else if(n==10) {
			for(int i=0;i<sessionHistory.size();i++) {
				System.out.println(sessionHistory.get(i));
			}
			if(sessionHistory.size()==0)
				System.out.println("No History Found!");
		}
		else if(n==0) {
			try {
				BufferedWriter out=new BufferedWriter(new FileWriter("CalculatorHistory.txt",true));
				out.write("--------Session Started-----------");
				out.write("\n");
				for(int i=0;i<sessionHistory.size();i++) {
					out.write(sessionHistory.get(i));
					out.write("\n");
				}
				out.write("--------Session Ended-----------");
				out.write("\n");
				out.write("\n");
				out.close();
			} catch(IOException e) {
				System.out.println("IOException Happened!");
			}
			System.out.println("Closing Application......");
		}
		else {
			throw new InvalidParameterException("Invalid Input");
		}
		if(n>=1 && n<=7) {
			localHistory.add("Output - "+ans);
			localHistory.add(" ");
			System.out.println(ans);
		}
		else if(n==9) System.out.println(ans);
		for(int i=0;i<localHistory.size();i++) {
			history.add(localHistory.get(i));
			sessionHistory.add(localHistory.get(i));
		}
		localHistory.clear();
	}
}