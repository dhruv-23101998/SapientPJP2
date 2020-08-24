package com.java.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProcessTransactions {
	ArrayList<Transaction> transactions;
	class SortJoin implements Comparator<Transaction> {
		public int compare(Transaction t1,Transaction t2) {
			return t1.getJoin().compareTo(t2.getJoin());
		}
	}
	class SortID implements Comparator<Transaction> {
		public int compare(Transaction t1,Transaction t2) {
			return t1.getId()-t2.getId();
		}
	}
	ProcessTransactions(String filepath) throws IOException {
		transactions=new ArrayList<Transaction>();
		BufferedReader in=new BufferedReader(new FileReader(filepath));
		String line="";  
		String splitBy=",";
		boolean flag=true;
		while((line=in.readLine())!=null) {
			if(flag) {
				flag=!flag;
				continue;
			}
			String[] row=line.split(splitBy);
			for(int i=0;i<row.length;i++)
				row[i]=row[i].replaceAll("\\s+"," ").trim();
			Transaction id=new Transaction();
			id.setExternalTransactionID(row[0]);
			id.setClientID(row[1]);
			id.setSecurityID(row[2]);
			id.setTransactionType(row[3]);
			id.setTransactionDate(row[4]);
			id.setMarketValue(row[5]);
			id.setPriority(row[6]);
			transactions.add(id);
		}
		in.close();
	}
	public void calculateProcessingFee() {
		for(int i=0;i<transactions.size();i++)
			transactions.get(i).setId(i);
		for(int i=0;i<transactions.size();i++) {
			Transaction t=transactions.get(i);
			t.setJoin(t.getClientID()+t.getSecurityID()+t.getTransactionDate());
		}
		ArrayList<Transaction> buy=new ArrayList<Transaction>();
		ArrayList<Transaction> sell=new ArrayList<Transaction>();
		ArrayList<Transaction> deposit=new ArrayList<Transaction>();
		ArrayList<Transaction> withdraw=new ArrayList<Transaction>();
		for(int i=0;i<transactions.size();i++) {
			Transaction t=transactions.get(i);
			if(t.getTransactionType().equalsIgnoreCase("BUY"))
				buy.add(t);
			else if(t.getTransactionType().equalsIgnoreCase("SELL"))
				sell.add(t);
			else if(t.getTransactionType().equalsIgnoreCase("DEPOSIT"))
				deposit.add(t);
			else
				withdraw.add(t);
		}
		Collections.sort(buy,new SortJoin());
		Collections.sort(sell,new SortJoin());
		Collections.sort(deposit,new SortJoin());
		Collections.sort(withdraw,new SortJoin());
		int x=0,y=0;
		while(x<buy.size() && y<sell.size()) {
			if(buy.get(x).getJoin().compareTo(sell.get(y).getJoin())<0)
				x++;
			else if(buy.get(x).getJoin().compareTo(sell.get(y).getJoin())>0)
				y++;
			else {
				buy.get(x).setProcessingFee("10");
				sell.get(y).setProcessingFee("10");
				x++;
				y++;
			}
		}
		x=0;
		y=0;
		while(x<deposit.size() && y<withdraw.size()) {
			if(deposit.get(x).getJoin().compareTo(withdraw.get(y).getJoin())<0)
				x++;
			else if(deposit.get(x).getJoin().compareTo(withdraw.get(y).getJoin())>0)
				y++;
			else {
				deposit.get(x).setProcessingFee("10");
				withdraw.get(y).setProcessingFee("10");
				x++;
				y++;
			}
		}
		transactions.clear();
		for(int i=0;i<buy.size();i++)
			transactions.add(buy.get(i));
		for(int i=0;i<sell.size();i++)
			transactions.add(sell.get(i));
		for(int i=0;i<deposit.size();i++)
			transactions.add(deposit.get(i));
		for(int i=0;i<withdraw.size();i++)
			transactions.add(withdraw.get(i));
		Collections.sort(transactions,new SortID());
		for(int i=0;i<transactions.size();i++) {
			Transaction t=transactions.get(i);
			if(!t.getProcessingFee().isEmpty())
				continue;
			if(t.getPriority().equalsIgnoreCase("Y"))
				t.setProcessingFee("500");
			else if(t.getTransactionType().equalsIgnoreCase("WITHDRAW") || t.getTransactionType().equalsIgnoreCase("SELL"))
				t.setProcessingFee("100");
			else
				t.setProcessingFee("50");
		}
	}
	public void createSummaryReport(String filepath) {
		String ans="Client Id, Transaction Type, Transaction Date, Priority, Processing Fee\n";
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter(filepath));
			out.write(ans);
			for(int i=0;i<transactions.size();i++) {
				String loc=transactions.get(i).getClientID()+","+transactions.get(i).getTransactionType()+",";
				loc+=transactions.get(i).getTransactionDate()+","+transactions.get(i).getPriority()+",";
				loc+=transactions.get(i).getProcessingFee()+"\n";
				out.write(loc);
			}
			out.close();
		} catch(IOException e) {
			System.out.println("IOException Happened!");
		}
	}
}