package com.sapient.assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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

public class DateTimeCalculatorBulkProcessing {
	Session session;
	File file;
	Scanner in;
	private int sessionNumber;
	DateTimeCalculatorBulkProcessing(String filename) throws FileNotFoundException {
		file=new File(filename);
		try {
			in=new Scanner(file);
		} catch(FileNotFoundException e) {
			throw new FileNotFoundException("No Such File Exists");
		}
		Configuration config=new Configuration().configure().addAnnotatedClass(OperationTable.class);
		ServiceRegistry registry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sf=config.buildSessionFactory(registry);
		this.session=sf.openSession();
	}
	public void initialize() {
		session.beginTransaction();
		Query query=session.createQuery("FROM OperationTable");
		@SuppressWarnings("unchecked")
		List<OperationTable> list=(List<OperationTable>)query.list();
		if(list.size()==0)
			sessionNumber=1;
		else sessionNumber=Integer.parseInt(list.get(list.size()-1).getSessionNumber())+1;
		session.getTransaction().commit();
		while(in.hasNextLine()) {
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
				this.session.getTransaction().commit();
				this.session.save(row);
				continue;
			}
			int n=Integer.parseInt(s);
			this.calculate(n,row);
			row.setEd(new Timestamp(new DateTime().getMillis()).toString());
			this.session.getTransaction().commit();
			this.session.save(row);
			if(n==0) {
				sessionNumber++;
				if(!in.hasNextLine()) {
					this.session.beginTransaction();
					this.session.getTransaction().commit();
				}
			}
		}
		this.session.close();
	}
	public void retriveDataOfLastNSessions(String filepath,int sessions) throws IOException {
		Configuration config=new Configuration().configure().addAnnotatedClass(OperationTable.class);
		ServiceRegistry registry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sf=config.buildSessionFactory(registry);
		this.session=sf.openSession();
		session.beginTransaction();
		Query query=session.createQuery("FROM OperationTable");
		@SuppressWarnings("unchecked")
		List<OperationTable> list=(List<OperationTable>)query.list();
		int actualSessions;
		if(list.size()==0)
			actualSessions=0;
		else actualSessions=Integer.parseInt(list.get(list.size()-1).getSessionNumber());
		if(actualSessions<sessions)
			System.out.println("Total Number Of Sessions In Database Are Less Than Entered!");
		else {
			int indx=list.size()-1;
			while(indx>=0 && Integer.parseInt(list.get(indx).getSessionNumber())>=actualSessions-sessions+1)
				indx--;
			indx++;
			BufferedWriter out=new BufferedWriter(new FileWriter(filepath));
			String str="Session Number, Operation Type, Start Timestamp, End Timestamp, Date 1, Date 2, Days, Weeks";
			str+=", Months, Natural Language Phrase, Result of Operation, Error\n";
			out.write(str);
			for(int i=indx;i<list.size();i++) {
				out.write(list.get(i).toString());
			}
			out.close();
		}
		session.getTransaction().commit();
        session.close();
	}
	private String takeInput() {
		String str=in.nextLine();
		return str;
	}
	public void calculate(int n,OperationTable row) {
		String ans="";
		if(n==1) {
			Calendar date1=DateOperations.stringToDate(takeInput(),row,1);
			Calendar date2=DateOperations.stringToDate(takeInput(),row,2);
			String oper=takeInput();
			if(oper.equals("+"))
				ans=DateOperations.dateToString(DateOperations.addDates(date1,date2,row));
			else 
				ans=DateOperations.substractDates(date1,date2,row);
		}
		else if(n==2) {
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			int days=Integer.parseInt(takeInput());
			row.setDays(String.valueOf(days));
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
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			int weeks=Integer.parseInt(takeInput());
			row.setWeeks(String.valueOf(weeks));
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
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			int months=Integer.parseInt(takeInput());
			row.setMonths(String.valueOf(months));
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
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			row.setType("Find Weekday Given Date");
			ans=DateOperations.findWeekday(date);
		}
		else if(n==6) {
			Calendar date=DateOperations.stringToDate(takeInput(),row,1);
			row.setType("Find Week Number Given Date");
			ans=DateOperations.findWeekNumber(date);
		}
		else if(n==7) {
			String phrase=takeInput();
			row.setPhrase(phrase);
			row.setType("Natural Language Phrases Conversion");
			try {
				ans=DateOperations.dateToString(NaturalPhraseProcessor.convertNaturalPhrase(phrase));
			} catch(ParseException e) {
				row.setError("Not Able To Parse Your Phrase Into Date");
			}
		}
		else if(n==0) {
			row.setType("Exit Session");
			row.setResult("Session Exited!");
		}
		else {
			row.setError("Invalid Input!");
		}
		if(n>=1 && n<=7) {
			if(row.getError().equals("N.A")) {
				row.setResult(ans);
			}
		}
	}
}