package com.sapient.assignment;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.*;
public class Executable {

	public static void main(String[] args) throws ParseException, IOException {
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		System.out.println("Enter 1-Want To Do Few Operations On Console");
		System.out.println("Enter 2-Want Bulk Processing Of Operations From Data File");
		String input=in.nextLine();
		input.trim();
		if(input.equals("1")) {
			DateTimeCalculator calculator=new DateTimeCalculator();
			calculator.initialize();
		}
		else if(input.equals("2")) {
			DateTimeCalculatorBulkProcessing calculator=new DateTimeCalculatorBulkProcessing("BulkOperationsFile.txt");
			calculator.initialize();
			calculator.retriveDataOfLastNSessions("RetrivedData.csv",200);
		}
		else {
			throw new InvalidParameterException("Invalid Input!");
		}
	}
}