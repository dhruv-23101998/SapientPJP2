package com.java.assignment;

public class IncomeData {
	private String country;
	private String city;
	private String gender;
	private String amount;
	private String currency;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String avgIncomeInDollars() {
		double ans=Double.parseDouble(amount);
		if(this.currency.equalsIgnoreCase("INR"))
			ans=ans/66.0;
		else if(this.currency.equalsIgnoreCase("GBP"))
			ans=ans/0.67;
		else if(this.currency.equalsIgnoreCase("SGD"))
			ans=ans/1.5;
		else if(this.currency.equalsIgnoreCase("HKD"))
			ans=ans/8.0;
		String income=Double.toString(ans);
		return income;
	}
}