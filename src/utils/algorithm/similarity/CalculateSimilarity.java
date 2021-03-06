package utils.algorithm.similarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import service.impl.BusinessService;
import domain.Click;
import domain.ProcessingQuery;
import domain.Query;
import domain.Sim;


public class CalculateSimilarity {
	public static int simSize=1000;
	public static HashMap<String, HashMap<String, Double>>sessionSimMap=new HashMap<String,HashMap<String,Double>>();
	
	public static void CalSim(List<ProcessingQuery> pQuerys){
		List<Sim>sims=new ArrayList<Sim>();
		BusinessService service=new BusinessService();
		int pQuerysSize=pQuerys.size();
		for(int i=0;i<pQuerysSize-1;i++){
			for(int j=i+1;j<pQuerysSize;j++){
				double []simArray=CalSim(pQuerys.get(i),pQuerys.get(j));
				Sim sim=new Sim();
				sim.setQueryID1(pQuerys.get(i).getQueryID());
				sim.setQueryID2(pQuerys.get(j).getQueryID());
				sim.setSim(simArray[0]);
				sim.setQuerySim(simArray[1]);
				sim.setUrlSim(simArray[2]);
				sim.setSessionSim(simArray[3]);
				sim.setProbaseSim(simArray[4]);
				sims.add(sim);
				if(sims.size()==simSize){
					service.addSims(sims);
					sims.clear();
				}
			}
		}
		service.addSims(sims);
	}
	public static double[] CalSim(ProcessingQuery p1,ProcessingQuery p2){
		BusinessService service=new BusinessService();
		double[]sims=new double[5];
		double querySim=CalQueryDiceSim(p1.getpQuery(),p2.getpQuery());
		sims[1]=querySim;
		
		String sql1="select * from clicks where queryID='"+p1.getQueryID()+"'";
		String sql2="select * from clicks where queryID='"+p2.getQueryID()+"'";
		List<Click> clicks1=service.getData(null,  sql1);
		List<Click> clicks2=service.getData(null,  sql2);
		
		double urlSim=CalUrlDiceSim(clicks1, clicks2);
		sims[2]=urlSim;
		
		double sessionSim=0;
		double probaseSim=0;
		sims[0]=0.93*querySim+0.13*urlSim+0.46*sessionSim+0.97*probaseSim;
		return sims;
	}
	public static List<Click> findClicks(String queryID){
		BusinessService service=new BusinessService();
		String sql="select * from clicks where queryID='"+queryID+"'";
		List<Click> clicks=service.getData(null,  sql);
		return clicks;
	}
	public static String findPQuery(String queryID){
		BusinessService service=new BusinessService();
		String sql="select * from processing where queryID='"+queryID+"'";
		List<ProcessingQuery> queries=service.getData(null,  sql);
		if(queries!=null)
			return queries.get(1).getpQuery();
		else
			return null;
	}
	public static double CalQueryDiceSim(String pquery,String qquery){
		double sim=0;
		String []pqueryArray=pquery.split(" ");
		String []qqueryArray=qquery.split(" ");
		double sameCount=0;
		for(int i=0;i<pqueryArray.length;i++){
			for(int j=0;j<qqueryArray.length;j++){
				if(pqueryArray[i].equals(qqueryArray[j]))
					sameCount++;
			}
		}
		sim=2*sameCount/(pqueryArray.length+qqueryArray.length);
		return sim;
	}
	public static double CalUrlDiceSim(List<Click> clicks1,List<Click> clicks2){
		double sim=0;
		double sameCount=0;
		if(clicks1!=null&&clicks2!=null){
			int size1=clicks1.size();
			int size2=clicks2.size();
			for(int i=0;i<size1;i++){
				for(int j=0;j<size2;j++){
					if(clicks1.get(i).getUrl().equals(clicks2.get(j).getUrl()))
						sameCount++;
				}
			}
			sim=2*sameCount/(size1+size2);
		}
		return sim;
	}
	public double CalSessionDocSim(List<ProcessingQuery>queries1 ,List<ProcessingQuery>queries2){
		double sim=0;
		return sim;
	}
	public double CalQuerySim(String pquery,String qquery) throws Exception {
		double sim=0;
		String []pqueryArray=pquery.split(" ");
		String []qqueryArray=qquery.split(" ");
		double samecount=0;
		for(int i=0;i<pqueryArray.length;i++){
			for(int j=0;j<qqueryArray.length;j++){
				if(pqueryArray[i].equals(qqueryArray[j]))
						samecount++;
			}
		}
		sim=samecount/((pqueryArray.length>qqueryArray.length)?pqueryArray.length:qqueryArray.length);
		
		return sim;
	}
	public static double CalSessionSim(String session1,String session2){
		if(session1.equals(session2))
			return 1;
		else
			return 0;
	}
	public static double CalSessionDocumentSim(String sessionDocument1,String sessionDocument2){
		return 0;
	}
	public static double CalProbaseSession(String content1,String content2){
		return 0;
	}
}
