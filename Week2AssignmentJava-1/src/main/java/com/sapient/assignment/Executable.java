package com.sapient.assignment;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.*;
public class Executable {

	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		DateTimeCalculator calculator=new DateTimeCalculator();
		ArrayList<String> menu=calculator.getMenu();
		System.out.println("----------------------------Menu---------------------------");
		for(int i=0;i<menu.size();i++)
			System.out.println(menu.get(i));
		System.out.println("-------------------------Menu Ends-------------------------");
		while(true) {
			String s=in.nextLine();
			boolean isNumeric=s.chars().allMatch(Character::isDigit);
			if(!isNumeric)
				throw new InvalidParameterException("Invalid Input!");
			int n=Integer.parseInt(s);
			calculator.calculate(n);
			if(n==0) break;
		}
	}
}