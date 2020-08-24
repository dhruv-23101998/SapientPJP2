package com.java.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IncomeDataSorter {
	private ArrayList<IncomeData> incomeData;
	class CustomSort implements Comparator<IncomeData> {
		public int compare(IncomeData id1,IncomeData id2) {
			if(id1.getCountry().equalsIgnoreCase(id2.getCountry())) {
				if(id1.getCity().equalsIgnoreCase(id2.getCity())) {
					if(id1.getGender().equalsIgnoreCase(id2.getGender())) {
						if(Double.parseDouble(id1.avgIncomeInDollars())<Double.parseDouble(id2.avgIncomeInDollars()))
							return -1;
						else return 1;
					}
					else {
						if(id1.getGender().equalsIgnoreCase("M")) return -1;
						else return 1;
					}
				}
				else return id1.getCity().compareTo(id2.getCity());
			}
			else return id1.getCountry().compareTo(id2.getCountry());
		}
	}
	IncomeDataSorter(String filepath) throws IOException {
		incomeData=new ArrayList<IncomeData>();
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
			IncomeData id=new IncomeData();
			id.setCity(row[0]);
			id.setCountry(row[1]);
			id.setGender(row[2]);
			id.setCurrency(row[3]);
			id.setAmount(row[4]);
			incomeData.add(id);
		}
		in.close();
		Collections.sort(incomeData,new CustomSort());
	}
	public void generateSortedFile(String filename) {
		String ans="Country, City, Gender, Average Income In USD\n";
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter(filename));
			out.write(ans);
			for(int i=0;i<incomeData.size();i++) {
				String loc=incomeData.get(i).getCountry()+","+incomeData.get(i).getCity()+",";
				loc+=incomeData.get(i).getGender()+","+incomeData.get(i).avgIncomeInDollars()+"\n";
				out.write(loc);
			}
			out.close();
		} catch(IOException e) {
			System.out.println("IOException Happened!");
		}
	}
}