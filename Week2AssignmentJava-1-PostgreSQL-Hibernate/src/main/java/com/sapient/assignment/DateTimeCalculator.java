package com.sapient.assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.joda.time.DateTime;

public class DateTimeCalculator {
	private ArrayList<String> menu;
	private ArrayList<String> history;
	private ArrayList<String> sessionHistory;
	private ArrayList<String> localHistory;
	Session session;
	Scanner in=new Scanner(System.in);
	DateTimeCalculator() {
		this.menu=new ArrayList<>();
		this.history=new ArrayList<>();
		this.sessionHistory=new ArrayList<>();
		this.menu.add("----------------------------Menu---------------------------");
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
		this.menu.add("-------------------------Menu Ends-------------------------");
		Configuration config=new Configuration().configure().addAnnotatedClass(OperationTable.class);
		ServiceRegistry registry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sf=config.buildSessionFactory(registry);
		this.session=sf.openSession();
	}
	public int getSessionNumber() {
		int sessionNumber=0;
		session.beginTransaction();
		Query query=session.createQuery("FROM OperationTable");
		@SuppressWarnings("unchecked")
		List<OperationTable> list=(List<OperationTable>)query.list();
		if(list.size()==0)
			sessionNumber=1;
		else sessionNumber=Integer.parseInt(list.get(list.size()-1).getSessionNumber())+1;
		session.getTransaction().commit();
		return sessionNumber;
	}
	public void initialize() throws IOException {
		for(int i=0;i<menu.size();i++)
			System.out.println(menu.get(i));
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int sessionNumber=this.getSessionNumber();
		while(true) {
			this.session.beginTransaction();
			OperationTable row=new OperationTable();
			row.setSt(new Timestamp(new DateTime().getMillis()).toString());
			row.setSessionNumber(String.valueOf(sessionNumber));
			row.setId(String.valueOf(sessionNumber)+new Timestamp(new DateTime().getMillis()).toString()+UUID.randomUUID().toString());
			String s=in.nextLine();
			boolean isNumeric=s.chars().allMatch(Character::isDigit);
			if(!isNumeric) {
				row.setError("Invalid Input!");
				row.setEd(new Timestamp(new DateTime().getMillis()).toString());
				System.out.println("Invalid Input!");
				this.session.getTransaction().commit();
				this.session.save(row);
				continue;
			}
			int n=Integer.parseInt(s);
			this.calculate(n,row);
			row.setEd(new Timestamp(new DateTime().getMillis()).toString());
			this.session.getTransaction().commit();
			if(n!=8 && n!=10) 
				this.session.save(row);
			if(n==0) {
				this.session.beginTransaction();
				this.session.getTransaction().commit();
				break;
			}
		}
		this.session.close();
	}
	private String takeInput() {
		String str=in.nextLine();
		localHistory.add("Input - "+str);
		return str;
	}
	public void calculate(int n,OperationTable row) {
		localHistory=new ArrayList<>();
		localHistory.clear();
		String ans="";
		if(n==1) {
			localHistory.add("Operation - Add/Substract Two Dates");
			localHistory.add("Enter Date 1(DD/MM/YYYY)");
			System.out.println("Enter Date 1(DD/MM/YYYY)");
			Calendar date1=DateOperations.stringToDate(takeInput(),row,1);
			localHistory.add("Enter Date 2(DD/MM/YYYY)");
			System.out.println("Enter Date 2(DD/MM/YYYY)");
			Calendar date2=DateOperations.stringToDate(takeInput(),row,2);
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("+"))
				ans=DateOperations.dateToString(DateOperations.addDates(date1,date2,row));
			else 
				ans=DateOperations.substractDates(date1,date2,row);
		}
		else if(n==2) {
			localHistory.add("Operation - Add/Substract Days From Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			localHistory.add("Enter Days");
			System.out.println("Enter Days");
			int days=Integer.parseInt(takeInput());
			row.setDays(String.valueOf(days));
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("-")) {
				days=-days;
				row.setType("Substraction of Days From Date");
			}
			else
				row.setType("Addition of Days To Date");
			ans=DateOperations.dateToString(DateOperations.addDaysToDate(date,days));
		}
		else if(n==3) {
			localHistory.add("Operation - Add/Substract Weeks From Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			localHistory.add("Enter Weeks");
			System.out.println("Enter Weeks");
			int weeks=Integer.parseInt(takeInput());
			row.setWeeks(String.valueOf(weeks));
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("-")) {
				weeks=-weeks;
				row.setType("Substraction of Weeks From Date");
			}
			else
				row.setType("Addition of Weeks To Date");
			ans=DateOperations.dateToString(DateOperations.addWeeksToDate(date,weeks));
		}
		else if(n==4) {
			localHistory.add("Operation - Add/Substract Months From Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			localHistory.add("Enter Months");
			System.out.println("Enter Months");
			int months=Integer.parseInt(takeInput());
			row.setMonths(String.valueOf(months));
			localHistory.add("Want to Add - Enter '+', Otherwise Enter '-'");
			System.out.println("Want to Add - Enter '+', Otherwise Enter '-'");
			String oper=takeInput();
			if(oper.equals("-")) {
				months=-months;
				row.setType("Substraction of Months From Date");
			}
			else
				row.setType("Addition of Months To Date");
			ans=DateOperations.dateToString(DateOperations.addMonthsToDate(date,months));
		}
		else if(n==5) {
			localHistory.add("Operation - Find Weekday Given Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			row.setType("Find Weekday Given Date");
			ans=DateOperations.findWeekday(date);
		}
		else if(n==6) {
			localHistory.add("Operation - Find Week Number Given Date");
			localHistory.add("Enter Date (DD/MM/YYYY)");
			System.out.println("Enter Date (DD/MM/YYYY)");
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			row.setType("Find Week Number Given Date");
			ans=DateOperations.findWeekNumber(date);
		}
		else if(n==7) {
			localHistory.add("Operation - Natural Language Phrases Converter");
			localHistory.add("Enter Natural Language Phrase To Be Converted To Date");
			System.out.println("Enter Natural Language Phrase To Be Converted To Date");
			String phrase=takeInput();
			row.setPhrase(phrase);
			row.setType("Natural Language Phrases Conversion");
			try {
				ans=DateOperations.dateToString(NaturalPhraseProcessor.convertNaturalPhrase(phrase));
			} catch(ParseException e) {
				row.setError("Not Able To Parse Your Phrase Into Date");
			}
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
			row.setType("Clear History");
			history.clear();
			ans="History Cleared!";
			sessionHistory.add("History Cleared!");
			row.setResult("History Cleared!");
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
			row.setType("Exit Session");
			try {
				BufferedWriter out=new BufferedWriter(new FileWriter("CalculatorConsoleOperationsHistory.txt",true));
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
			row.setResult("Session Exited!");
		}
		else {
			row.setError("Invalid Input!");
			System.out.println("Invalid Input! Execute New Operation Or Enter 0 To Exit");
		}
		if(n>=1 && n<=7) {
			localHistory.add("Output - "+ans);
			localHistory.add(" ");
			if(row.getError().equals("N.A")) {
				row.setResult(ans);
				System.out.println(ans);
			}
			else {
				System.out.println("Invalid Input! Execute New Operation Or Enter 0 To Exit");
			}
		}
		else if(n==9) System.out.println(ans);
		for(int i=0;i<localHistory.size();i++) {
			history.add(localHistory.get(i));
			sessionHistory.add(localHistory.get(i));
		}
		localHistory.clear();
	}
}