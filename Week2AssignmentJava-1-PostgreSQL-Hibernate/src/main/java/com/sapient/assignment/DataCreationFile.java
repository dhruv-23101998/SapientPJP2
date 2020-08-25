package com.sapient.assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DataCreationFile {
	public static void main(String[] args) throws IOException {
		BufferedWriter out=new BufferedWriter(new FileWriter("BulkOperationsFile.txt"));
		Random rand=new Random();
		ArrayList<String> phrases=new ArrayList<>();
		phrases.add("TODAY");
		phrases.add("TOMORROW");
		phrases.add("DAY AFTER TOMORROW");
		phrases.add("YESTERDAY");
		phrases.add("DAY BEFORE YESTERDAY");
		phrases.add("LAST WEEK");
		phrases.add("PREVIOUS WEEK");
		phrases.add("NEXT WEEK");
		phrases.add("NEXT MONTH");
		phrases.add("NEXT YEAR");
		phrases.add("LAST MONTH");
		phrases.add("LAST YEAR");
		phrases.add("MONTH AFTER");
		phrases.add("MONTH BEFORE");
		phrases.add("WEEKS FROM NOW");
		phrases.add("DAYS FROM NOW");
		phrases.add("MONTHS FROM NOW");
		phrases.add("YEARS FROM NOW");
		phrases.add("DAYS EARLIER");
		phrases.add("WEEKS EARLIER");
		phrases.add("MONTHS EARLIER");
		phrases.add("YEARS EARLIER");
		for(int i=0;i<200;i++) {
			for(int j=0;j<10;j++) {
				int n=rand.nextInt(8)+1;
				out.write(String.valueOf(n));
				out.write("\n");
				if(n==1) {
					String date1=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date1+=String.valueOf(rand.nextInt(2000)+1000);
					String date2=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date2+=String.valueOf(rand.nextInt(2000)+1000);
					out.write(date1+"\n");
					out.write(date2+"\n");
					int flag=rand.nextInt(2);
					if(flag==0) out.write("+\n");
					else out.write("-\n");
				}
				else if(n==2) {
					String date=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date+=String.valueOf(rand.nextInt(2000)+1000);
					out.write(date+"\n");
					out.write(String.valueOf(rand.nextInt(500)));
					out.write("\n");
					int flag=rand.nextInt(2);
					if(flag==0) out.write("+\n");
					else out.write("-\n");
				}
				else if(n==3) {
					String date=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date+=String.valueOf(rand.nextInt(2000)+1000);
					out.write(date+"\n");
					out.write(String.valueOf(rand.nextInt(500)));
					out.write("\n");
					int flag=rand.nextInt(2);
					if(flag==0) out.write("+\n");
					else out.write("-\n");
				}
				else if(n==4) {
					String date=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date+=String.valueOf(rand.nextInt(2000)+1000);
					out.write(date+"\n");
					out.write(String.valueOf(rand.nextInt(500)));
					out.write("\n");
					int flag=rand.nextInt(2);
					if(flag==0) out.write("+\n");
					else out.write("-\n");
				}
				else if(n==5) {
					String date=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date+=String.valueOf(rand.nextInt(2000)+1000);
					out.write(date+"\n");
				}
				else if(n==6) {
					String date=String.valueOf(rand.nextInt(32)+1)+"/"+String.valueOf(rand.nextInt(13)+1)+"/";
					date+=String.valueOf(rand.nextInt(2000)+1000);
					out.write(date+"\n");
				}
				else if(n==7) {
					int x=rand.nextInt(phrases.size());
					String phrase=phrases.get(x);
					if(x>=14)
						phrase=String.valueOf(rand.nextInt(500))+" "+phrase;
					out.write(phrase);
					out.write("\n");
				}
			}
			out.write("0\n");
		}
		out.close();
	}
}