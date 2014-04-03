package utils.algorithm.extract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.algorithm.processing.PreProcessing;

import domain.ProcessingQuery;
import domain.Query;

public class Extract {
	public static ProcessingQuery extractTopicPart(Query query) throws IOException{
		YearSensitiveRules ysr=new YearSensitiveRules();
		String content=query.getContent();
		content=" "+content+" ";
		List<String>RemoveList=new ArrayList<String>();
		
		RemoveList.addAll(ysr.Filter8figure(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterMonthDay4Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterMonthDay2Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterLetterMonthDay4Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterLetterMonthDay2Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterLetterMonth4Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterLetterMonth2Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterLetterMonthDay(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.Filter4Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.Filter2Year(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		RemoveList.addAll(ysr.FilterDictionary(content));
		content=RemoveList.get(RemoveList.size()-1);
		RemoveList.remove(RemoveList.size()-1);
		
		if(RemoveList.size()==0){
			return null;
		}
		String removequery="";
		for(String remove:RemoveList){
			removequery+=remove+" ";
		}
		removequery.trim();//timePart
		
		ProcessingQuery pquery=new ProcessingQuery();
		pquery.setContent(query.getContent());
		pquery.setQueryID(query.getId());
		pquery.setpQuery(PreProcessing.preProcessingFunction(content.replaceAll("①", " ")));
		pquery.setTimePart(removequery);
		pquery.setTopicPart(content);
		pquery.setSessionID(query.getSessionID());
		return pquery;
	}
}
