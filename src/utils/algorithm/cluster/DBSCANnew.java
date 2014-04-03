package utils.algorithm.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import domain.Cluster;
import domain.ProcessingQuery;
import domain.Sim;

import service.impl.BusinessService;


public class DBSCANnew {
	double Eps = 0.65; // ����뾶
	int MinPts = 3; // �ܶ�
	public static int simMapSize=1000;
	public static HashMap<String, HashMap<String, Double>>similarityMap=new HashMap<String,HashMap<String,Double>>();
//	public static ArrayList<Cluster> datas= new ArrayList<Cluster>();
	
	public  void run(List<ProcessingQuery> pQueryDatas) throws Exception{
		putSimilarity();
		ArrayList<Cluster> datas=new ArrayList<Cluster>();
		for(ProcessingQuery pquery:pQueryDatas){
			Cluster c=new Cluster();
			c.setContent(pquery.getContent());
			c.setpQuery(pquery.getpQuery());
			c.setQueryID(pquery.getQueryID());
			c.setSessionID(pquery.getSessionID());
			c.setTimePart(pquery.getTimePart());
			c.setTopicPart(pquery.getTimePart());
			datas.add(c);
		}
		dbscan(datas);
	}
	public double getSimilarity(String queryID1, String queryID2) {
		double sim = 0;
		if(similarityMap.containsKey(queryID1)){
			HashMap<String,Double> secondMap=similarityMap.get(queryID1);
			if(secondMap.containsKey(queryID2))
				sim=similarityMap.get(queryID1).get(queryID2);
		}
		return sim;
	}

		public static void putSimilarity()  {
			String sql="select * from sims limit 0,"+simMapSize+"";
			BusinessService service=new BusinessService();
			List<Sim> data=service.getData(null, sql);
			for(int i=0;i<data.size();i++){
				similarityMap.put(data.get(i).getQueryID1(),(HashMap<String, Double>) new HashMap().put(data.get(i).getQueryID2(), data.get(i).getSim()));
			}
		}
		
	public Vector<Cluster> getNeighbors(Cluster p,ArrayList<Cluster> objects) throws  Exception {
		Vector<Cluster> neighbors = new Vector<Cluster>();
		Iterator<Cluster> iter = objects.iterator();
		while (iter.hasNext()) {
			Cluster q = iter.next();
			double d=0;
			if(p.getQueryID().equals(q.getQueryID()))
				d=1;
			else 
				d=getSimilarity(p.getQueryID(),q.getQueryID());
			if (d >= Eps)
				neighbors.add(q);
		}
		return neighbors;
	}
	public int dbscan(ArrayList<Cluster> datas) throws Exception{
		int clusterID = 0;
		boolean AllVisited = false;
		int i = 0;
		while (!AllVisited) {
			Iterator<Cluster> iter = datas.iterator();
			while (iter.hasNext()) {
				System.out.println("i:" + i++);
				Cluster p = iter.next();
				if (p.getIsVisited()==true)
					continue;
				AllVisited = false;
				p.setIsVisited(true); 
				Vector<Cluster> neighbors = getNeighbors(p, datas);
				if (neighbors.size() < MinPts) {
					if (p.getClusterID() <= 0)
						p.setClusterID(-1); 
				} else {
					System.out.println("the number of neighbors of " + ","
							+ neighbors.size());
					if (p.getClusterID() <= 0) {
						clusterID++;
						expandCluster(p, neighbors, clusterID, datas);
					} else {
						int iid = p.getClusterID();
						expandCluster(p, neighbors, iid, datas);
					}
				}
				AllVisited = true;
			}
		}
		return clusterID;
	}
	private void expandCluster(Cluster p, Vector<Cluster> neighbors,
			int clusterID, ArrayList<Cluster> datas) throws 
			Exception {
		p.setClusterID(clusterID);
		Iterator<Cluster> iter = neighbors.iterator();
		while (iter.hasNext()) {
			Cluster q = iter.next();
			if (!q.getIsVisited()) {
				q.setIsVisited(true);
				Vector<Cluster> qneighbors = getNeighbors(q, datas);
				if (qneighbors.size() >= MinPts) {
					Iterator<Cluster> it = qneighbors.iterator();
					while (it.hasNext()) {
						Cluster no = it.next();
						if (no.getClusterID() <= 0)
							no.setClusterID(clusterID);
					}
				}
			}
			if (q.getClusterID() <= 0) { 
				q.setClusterID(clusterID);
			}
		}
	}

}
