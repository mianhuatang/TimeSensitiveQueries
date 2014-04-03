package utils.algorithm.extract;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimeDictionary {
	public static String[] timedictionary;

	public static String[] getTimedictionary() {
		return timedictionary;
	}

	public static void setTimedictionary(String[] timedictionary) {
		TimeDictionary.timedictionary = timedictionary;
	}
	public TimeDictionary() {
		// TODO Auto-generated constructor stub
	}
	public  Boolean ContainsTimeDictionary(String query){
		String patterncontent="\\bday\\b|\\bweek\\b|\\bweekly\\b|\\bsennight\\b|" +
				"\\bsunday\\b|\\bmonday\\b|\\btuesday\\b|\\bwednesday\\b|\\bthursday\\b|\\bfriday\\b|\\bsaturday\\b|" +
				"\\bfortnight\\b|\\blunar month\\b|\\bmonth\\b|\\bjanuary\\b|\\bfebruary\\b|\\bmarch\\b|\\bapril\\b|" +
				"\\bmay\\b|\\bjune\\b|\\bjuly\\b|\\baugust\\b|\\bseptember\\b|\\boctober\\b|\\bnovember\\b|\\bdecember\\b|" +
				"\\bjan\\b|\\bfeb\\b|\\bmar\\b|\\bapi\\b|\\bmay\\b|\\bjul\\b|\\bjun\\b|\\baug\\b|\\bsep\\b|\\boct\\b|" +
				"\\bnov\\b|\\bdec\\b|\\bmon\\b|\\btue\\b|\\bwed\\b|\\bthur\\b|\\bfri\\b|\\bsat\\b|\\bsun\\b|" +
				"\\bquarter\\b|\\byear\\b|\\byearly\\b|\\bbiennium\\b|\\bOlympiad\\b|\\blustrum\\b|" +
				"\\bdecade\\b|\\bindiction\\b|\\bjubilee \\b|\\bbiblical\\b|\\bcentury\\b|\\bdaily\\b|\\bmonthly\\b|" +
				"\\bbi-monthly\\b|\\bquarterly\\b|\\bemi-annually\\b|\\bannually";
		Pattern pattern = Pattern.compile(patterncontent,
				Pattern.DOTALL);
		Matcher matcher = pattern.matcher(query);
		if(matcher.find()){
			return true;
		}	
		return false;
	}
	
}
