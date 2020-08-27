package com.sapient.assignment;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateOperations {
	public static Calendar addDates(Calendar date1,Calendar date2) {
		Calendar addedDate=(Calendar)date1.clone();
		addedDate.add(Calendar.YEAR,date2.get(Calendar.YEAR));
		addedDate.add(Calendar.MONTH,date2.get(Calendar.MONTH)+1);
		addedDate.add(Calendar.DATE, date2.get(Calendar.DATE));
		return addedDate;
	}
	public static String substractDates(Calendar date1,Calendar date2) {
		LocalDate localDate1 = LocalDateTime.ofInstant(date1.toInstant(), date1.getTimeZone().toZoneId()).toLocalDate();
		LocalDate localDate2 = LocalDateTime.ofInstant(date2.toInstant(), date2.getTimeZone().toZoneId()).toLocalDate();
		double daysBetween=ChronoUnit.DAYS.between(date1.before(date2) ? date1.toInstant() : date2.toInstant(),date1.before(date2) ? date2.toInstant() : date1.toInstant());
		double weeksBetween=ChronoUnit.WEEKS.between(date1.before(date2) ? localDate1 : localDate2,date1.before(date2) ? localDate2 : localDate1);
		double monthsBetween=ChronoUnit.MONTHS.between(date1.before(date2) ? localDate1 : localDate2,date1.before(date2) ? localDate2 : localDate1);
		return "Days Between "+Double.toString(daysBetween)+", Weeks Between "+Double.toString(weeksBetween)+", Months Between "+Double.toString(monthsBetween);
	}
	public static Calendar addDaysToDate(Calendar date,int days) {
		date.add(Calendar.DAY_OF_MONTH, days);
		return date;
	}
	public static Calendar addWeeksToDate(Calendar date,int weeks) {
		date.add(Calendar.WEEK_OF_YEAR, weeks);
		return date;
	}
	public static Calendar addMonthsToDate(Calendar date,int months) {
		date.add(Calendar.MONTH, months);
		return date;
	}
	public static String findWeekday(Calendar date) {
		int weekday=date.get(Calendar.DAY_OF_WEEK);
		if(weekday==1) return "SUNDAY";
		else if(weekday==2) return "MONDAY";
		else if(weekday==3) return "TUESDAY";
		else if(weekday==4) return "WEDNESDAY";
		else if(weekday==5) return "THURSDAY";
		else if(weekday==6) return "FRIDAY";
		else return "SATURDAY";
	}
	public static String findWeekNumber(Calendar date) {
		int ans=date.get(Calendar.WEEK_OF_YEAR);
		return String.valueOf(ans);
	}
	public static Calendar stringToDate(String s) throws InvalidParameterException {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
			sdf.setLenient(false);
			Date date=sdf.parse(s);
			Calendar ans=Calendar.getInstance();
			ans.setTime(date);
			return ans;
		} catch(ParseException e) {
			throw new InvalidParameterException("Incorrect Date");
		}
	}
	public static String dateToString(Calendar ans) {
		Date date=ans.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String strDate=sdf.format(date);
		return strDate;
	}
}