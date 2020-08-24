package com.java.assignment;

import java.io.IOException;

public class Executable {
	public static void main(String[] args) throws IOException {
		IncomeDataSorter ids=new IncomeDataSorter("incomeData.csv");
		ids.generateSortedFile("SummaryReport.csv");
	}
}