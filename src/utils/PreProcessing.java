package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import utils.algorithm.extract.Extract;

import domain.ProcessingQuery;
import domain.Query;


public class PreProcessing {
	public static void main(String[]args) throws IOException{
//		char x=96;
//		System.out.println(x);
		String inFile="F:\\research\\111\\time-sensitive query\\MS Data Set\\srfp20060501-20060531.queries.txt";
		String outFile="F:\\毕设\\preProcessing.txt";
		int pos=1;//query content position
		FileReader fr = new FileReader(inFile);
		BufferedReader input =new BufferedReader(fr, 20 * 1024 * 1024);
		FileWriter output = new FileWriter(outFile);
		String line = null;
		int count=0;
		while((line=input.readLine())!=null){
			if(count==0){
				count++;
				continue;
			}
			System.out.println(count);
			String []linearray=line.split("\t");
			Query query=new Query();
			query.setTime((linearray[0]));
			query.setContent(linearray[1]);
			query.setId(linearray[2]);
			query.setSessionID(linearray[3]);
			query.setResultCount(Integer.parseInt(linearray[4]));
			ProcessingQuery pQuery=Extract.extractTopicPart(query);
			
			String outLine="";
			outLine+=pQuery.getQueryID()+"\t"+pQuery.getSessionID()+"\t"+pQuery.getContent()+"\t"+
			pQuery.getpQuery()+"\t"+pQuery.getTopicPart()+"\t"+pQuery.getTimePart();
			
			output.append(outLine + "\n");
			count++;
			if(count%10000==0)
				output.flush();
		
		}
		output.flush();
		output.close();
		output = null;
		input.close();
	}
}
