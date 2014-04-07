package utils.algorithm.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import service.impl.BusinessService;

import domain.Cluster;
public class ClusterFilterNoises {
	public static Map<Integer,List<Cluster>> clusterMap=new HashMap<Integer, List<Cluster>>();
	public static double ratioValue=0.5;
	public static int sessionValue=2;
	public static void filter(){
		String sql="select * from clusters order by clusterID";
		BusinessService service=new BusinessService();
		List<Cluster> clusters=service.getData(null, sql);
		for(int i=0;clusters!=null&&i<clusters.size();i++){
			if(clusterMap.containsKey(clusters.get(i).getClusterID())){
				List<Cluster> list=clusterMap.get(clusters.get(i).getClusterID());
				list.add(clusters.get(i));
				clusterMap.put(clusters.get(i).getClusterID(), list);
			}
			else{
				List<Cluster> list=new ArrayList<Cluster>();
				list.add(clusters.get(i));
				clusterMap.put(clusters.get(i).getClusterID(), list);
			}
		}
		for(int key:clusterMap.keySet()){
			if(key==-1)
				continue;
			List<Cluster>list=clusterMap.get(key);
			int year2=0;
			int year4=0;
			Set<String>sessionSet=new HashSet<String>();
			if(list!=null){
				for(Cluster c:list){
					sessionSet.add(c.getSessionID());
					String[]timeArray=c.getTimePart().split("#");
					if(timeArray!=null){
						for(String time:timeArray){
							if(time.contains("y"))
								year2++;
							else if(time.contains("Y"))
								year4++;
						}
					}
				}
			}
			double ratio=1;
			if(year2!=0){
				ratio=year4*1.0/(year4+year2);
			}
			if(ratio>=ratioValue&&sessionSet.size()>=sessionValue){
				service.addClustersFilter(clusterMap.get(key));
			}
		}
	}

}
