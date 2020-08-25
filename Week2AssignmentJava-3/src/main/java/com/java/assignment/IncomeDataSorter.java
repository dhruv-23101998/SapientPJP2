package com.java.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IncomeDataSorter {
	private ArrayList<IncomeData> incomeData;
	private HashMap<String,Double> hm;
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
			for(int i=0;i<row.length;i++)
				row[i]=row[i].replaceAll("\\s+"," ").trim();
			IncomeData id=new IncomeData();
			id.setCity(row[0]);
			id.setCountry(row[1]);
			id.setGender(row[2]);
			id.setCurrency(row[3]);
			id.setAmount(row[4]);
			incomeData.add(id);
		}
		in.close();
		hm=new HashMap<String,Double>();
		HashMap<String,Integer> count=new HashMap<String,Integer>();
		for(int i=0;i<incomeData.size();i++)
		{
			String loc="";
			if(incomeData.get(i).getCountry()==null || incomeData.get(i).getCountry().isEmpty())
				loc=incomeData.get(i).getCity()+","+incomeData.get(i).getGender();
			else loc=incomeData.get(i).getCountry()+","+incomeData.get(i).getGender();
			if(hm.get(loc)==null) {
				hm.put(loc,Double.parseDouble(incomeData.get(i).avgIncomeInDollars()));
				count.put(loc,1);
			}
			else {
				int cnt=count.get(loc);
				double val=hm.get(loc);
				count.replace(loc,cnt+1);
				hm.replace(loc,(val*(double)cnt+Double.parseDouble(incomeData.get(i).avgIncomeInDollars()))/(cnt+1));
			}
		}
	}
	public void generateSortedFile(String filename) {
		String ans="Country/City, Gender, Average Income In USD\n";
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter(filename));
			out.write(ans);
			TreeMap<String,Double> sorted=new TreeMap<>();
			sorted.putAll(hm);
			for(Map.Entry<String,Double> element:sorted.entrySet()) {
				String loc=element.getKey()+","+String.valueOf(element.getValue())+"\n";
				out.write(loc);
			}
			out.close();
		} catch(IOException e) {
			System.out.println("IOException Happened!");
		}
	}
}