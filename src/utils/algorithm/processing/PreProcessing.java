package utils.algorithm.processing;

import java.io.IOException;


public class PreProcessing {//stemmer->remove stopwordss
	private static String stopwordFile="F:\\research\\111\\time-sensitive query\\MS Data Set\\newqueryfilter\\YearSensitive\\PreProcessing\\stopwordnew.txt";
	public static String preProcessingFunction(String topicPart) throws IOException{
		Stemmer s = new Stemmer();
		char x=0;
		
		String[]queryArray=topicPart.split(" ");
		topicPart="";
		for(int i=0;i<queryArray.length;i++){
			s.add(queryArray[i].toLowerCase().toCharArray(), queryArray[i].length());
			s.stem();
			topicPart+=s.toString()+" ";
		}
		topicPart.trim();
		RemoveStopWords rsw=new RemoveStopWords();
		rsw.IniStopWordList(stopwordFile);
		topicPart=rsw.RemoveStopWordsFunction(topicPart);
		return topicPart;
	}
}
