package utils;

import java.sql.SQLException;
import java.util.*;

import service.impl.BusinessService;
import utils.algorithm.extract.Extract;
import domain.Page;
import domain.ProcessingQuery;
import domain.Query;


public class Session {

	public static void main(String[] args) throws SQLException{
		HashMap<String,String> list=new HashMap<String,String> ();
		BusinessService service = new BusinessService();
		JdbcUtils jdbc=new JdbcUtils();
		int currentIndex=0;
		int size=10000;
		String totalSql="select count(*) from queries ";
		String sql="select * from queries  limit ?,?";
		int totalRecord=service.getTotalrecord(totalSql);
		//totalRecord=1000;
		int count=0;
		while(currentIndex<totalRecord){
			count++;
			System.out.println(currentIndex);
			List <Query> data=service.getData(currentIndex, size, sql);
			
			for(int i=0;i<data.size();i++){
				String id=data.get(i).getSessionID();
				String con=data.get(i).getContent();			
				String value = (String) list.get(id);
				if(value==null){
					list.put(id, con);
				}else{
					list.put(id, value+" "+con);
				}
			}
			
			if(count%10==0){
//				String sessionID=data.get(data.size()-1).getSessionID();
//				String v=list.get(sessionID);
//				list.remove(sessionID);
				jdbc.addSessionID(list);
				list=new HashMap<String,String>();
//				list.put(sessionID,v);
			}
			currentIndex+=size;
		}
		System.out.println("hello");
		
		jdbc.addSessionID(list);
		System.out.println("hello");

	}

}
