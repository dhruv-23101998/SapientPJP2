package com.sapient.assignment;

import java.security.InvalidParameterException;
import java.util.Calendar;

public class NaturalPhraseProcessor {
	public static Calendar convertNaturalPhrase(String phrase) throws InvalidParameterException {
		Calendar ans=Calendar.getInstance();;
		String s=phrase.replaceAll("\\s+"," ").trim();
		if(s.equalsIgnoreCase("TODAY")) {
		}
		else if(s.equalsIgnoreCase("TOMORROW")) {
			DateOperations.addDaysToDate(ans,1);
		}
		else if(s.equalsIgnoreCase("DAY AFTER TOMORROW")) {
			DateOperations.addDaysToDate(ans,2);
		}
		else if(s.equalsIgnoreCase("YESTERDAY")) {
			DateOperations.addDaysToDate(ans,-1);
		}
		else if(s.equalsIgnoreCase("DAY BEFORE YESTERDAY")) {
			DateOperations.addDaysToDate(ans,-2);
		}
		else if(s.equalsIgnoreCase("LAST WEEK")) {
			DateOperations.addWeeksToDate(ans,-1);
		}
		else if(s.equalsIgnoreCase("PREVIOUS WEEK")) {
			DateOperations.addWeeksToDate(ans,-1);
		}
		else if(s.equalsIgnoreCase("NEXT WEEK")) {
			DateOperations.addWeeksToDate(ans,1);
		}
		else if(s.equalsIgnoreCase("NEXT MONTH")) {
			DateOperations.addMonthsToDate(ans,1);
		}
		else if(s.equalsIgnoreCase("NEXT YEAR")) {
			ans.add(Calendar.YEAR,1);
		}
		else if(s.equalsIgnoreCase("LAST MONTH")) {
			DateOperations.addMonthsToDate(ans,-1);
		}
		else if(s.equalsIgnoreCase("LAST YEAR")) {
			ans.add(Calendar.YEAR,-1);
		}
		else if(s.equalsIgnoreCase("MONTH AFTER")) {
			DateOperations.addMonthsToDate(ans,1);
		}
		else if(s.equalsIgnoreCase("MONTH BEFORE")) {
			DateOperations.addMonthsToDate(ans,-1);
		}
		else {
			String[] words=s.split(" ");
			if(words.length==0) {
				throw new InvalidParameterException("Not Able To Parse Your Phrase Into Date");
			}
			boolean isNumeric=words[0].chars().allMatch(Character::isDigit);
			if(isNumeric) {
				String str="";
				for(int i=1;i<words.length;i++) {
					str+=words[i];
					if(i!=words.length-1)
						str+=" ";
				}
				int n=Integer.parseInt(words[0]);
				if(str.equalsIgnoreCase("WEEKS FROM NOW")) {
					DateOperations.addWeeksToDate(ans,n);
				}
				else if(str.equalsIgnoreCase("DAYS FROM NOW")) {
					DateOperations.addDaysToDate(ans,n);
				}
				else if(str.equalsIgnoreCase("MONTHS FROM NOW")) {
					DateOperations.addMonthsToDate(ans,n);
				}
				else if(str.equalsIgnoreCase("YEARS FROM NOW")) {
					ans.add(Calendar.YEAR,n);
				}
				else if(str.equalsIgnoreCase("DAYS EARLIER")) {
					DateOperations.addDaysToDate(ans,-n);
				}
				else if(str.equalsIgnoreCase("WEEKS EARLIER")) {
					DateOperations.addWeeksToDate(ans,-n);
				}
				else if(str.equalsIgnoreCase("MONTHS EARLIER")) {
					DateOperations.addMonthsToDate(ans,-n);
				}
				else if(str.equalsIgnoreCase("YEARS EARLIER")) {
					ans.add(Calendar.YEAR,-n);
				}
				else {
					throw new InvalidParameterException("Not Able To Parse Your Phrase Into Date");
				}
			}
			else {
				throw new InvalidParameterException("Not Able To Parse Your Phrase Into Date");
			}
		}
		return ans;
	}
}