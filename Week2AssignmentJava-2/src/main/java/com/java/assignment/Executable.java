package com.java.assignment;

import java.io.IOException;

public class Executable {
	public static void main(String[] args) throws IOException {
		ProcessTransactions pt=new ProcessTransactions("TransactionHistory.csv");
		pt.calculateProcessingFee();
		pt.createSummaryReport("SummaryReport-TransactionHistory.csv");
		ProcessTransactions ptn=new ProcessTransactions("Sample_Data_Fee_Calculator.csv");
		ptn.calculateProcessingFee();
		ptn.createSummaryReport("SummaryReport-Sample_Data_Fee_Calculator.csv");
	}
}