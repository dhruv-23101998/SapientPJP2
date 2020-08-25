package com.java.assignment;

import java.io.IOException;

public class Executable {
	public static void main(String[] args) throws IOException {
		IncomeDataSorter ids=new IncomeDataSorter("incomeData.csv");
		ids.generateSortedFile("SummaryReport-incomeData.csv");
		IncomeDataSorter ids1=new IncomeDataSorter("Sample input file Assignment 3.csv");
		ids1.generateSortedFile("SummaryReport-Sample input file Assignment 3.csv");
	}
}