package com.sapient.assignment;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OperationTable {
	@Id
	private String id;
	private String sessionNumber;
	private String type;
	private String st,ed;
	private String days,weeks,months,phrase;
	private String date1,date2;
	private String result;
	private String error;
	OperationTable() {
		this.type="N.A";
		this.error="N.A";
		this.days="N.A";
		this.weeks="N.A";
		this.months="N.A";
		this.phrase="N.A";
		this.result="N.A";
		this.date1="N.A";
		this.date2="N.A";
	}
	@Override
	public String toString() {
		String ans=this.sessionNumber+","+this.type+","+this.st+","+this.ed+","+this.date1+","+this.date2+",";
		ans+=this.days+","+this.weeks+","+this.months+","+this.phrase+","+this.result+","+this.error+"\n";
		return ans;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSessionNumber() {
		return sessionNumber;
	}
	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}