package utils.algorithm.cluster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import service.impl.BusinessService;
import utils.algorithm.index.queryIndex;
import utils.algorithm.similarity.CalculateSimilarity;

public class indexCluster {
	private static int clusterID=0;
	private double querySimWeight=0.93;
	private double urlSimWeight=0.13;
	private double sessionSimWeight=0.46;
	private double probaseSimWeight=0.97;
	private static double minSim=0.65;
	
	public indexCluster(double querySimWeight,double urlSimWeight,double sessionSimWeight,double probaseSimWeight){
		this.querySimWeight=querySimWeight;
		this.urlSimWeight=urlSimWeight;
		this.sessionSimWeight=sessionSimWeight;
		this.probaseSimWeight=probaseSimWeight;
	}
	public void cluster(){
		BusinessService service=new BusinessService();
		int currentIndex=0;
		int size=10000;
		String sql="select id,sessions.sessionID, content from queries,sessions where queries.sessionID=sessions.sessionID limit ?,?";
		Object[]params={currentIndex,size};
		ResultSet re=service.query(sql, params);
		try {
			while(re.next()){
				String queryID=re.getString(1);
				String sessionID=re.getString(2);
				String sessionDocument=re.getString(3);
				
				queryIndex qindex=new queryIndex();
				List<String> queryIDs=qindex.queryIDs(sessionDocument);
				setClusterID(queryID,queryIDs);
				
				while(re.next()){
					queryID=re.getString(1);
					sessionID=re.getString(2);
					sessionDocument=re.getString(3);
					queryIDs=qindex.queryIDs(sessionDocument);
					setClusterID(queryID,queryIDs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setClusterID(String queryID,List<String> queryIDs){
		if(queryIDs==null){
			setClusterID(queryID,clusterID);
			clusterID++;
		}
		else{
			double maxSim=0;
			String maxQueryID=null;
			
			for(String id:queryIDs){
				String pQuery1=CalculateSimilarity.findPQuery(queryID);
				String pQuery2=CalculateSimilarity.findPQuery(id);
				
				double querySim=CalculateSimilarity.CalQueryDiceSim(pQuery1,pQuery2);
				double urlSim=CalculateSimilarity.CalUrlDiceSim(CalculateSimilarity.findClicks(queryID), CalculateSimilarity.findClicks(id));
				
				queryIndex qindex=new queryIndex();
				String sessionDocument1=qindex.queryDocuments(pQuery1).get(0);
				String sessionDocument2=qindex.queryDocuments(pQuery1).get(0);
				
				double sessionSim=CalculateSimilarity.CalSessionDocumentSim(sessionDocument1,sessionDocument2);
				double probaseSim=CalculateSimilarity.CalProbaseSession(pQuery1, pQuery2);
				
				double sim=0.93*querySim+0.13*urlSim+0.46*sessionSim+0.97*probaseSim;
				if(sim>maxSim){
					maxSim=sim;
					maxQueryID=id;
				}
			}
			if(maxSim<minSim){
				setClusterID(queryID,clusterID);
				clusterID++;
			}
			else{
				int maxClusterID=getClusterID(maxQueryID);
				setClusterID(queryID,maxClusterID);
			}
		}
	}
	public void setClusterID(String queryID,int clusterID){
		String sql="insert into query_cluster(queryID,clusterID) values(?,?}";
		BusinessService service=new BusinessService();
		Object[]params={queryID,clusterID};
		service.update(sql, params);
	}
	public int getClusterID(String queryID){
		String sql="select * from query_cluster where queryID="+queryID;
		BusinessService service=new BusinessService();
		ResultSet rs=service.query(sql, null);
		try {
			if(rs.next())
				return rs.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
