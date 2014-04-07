package service.impl;

import java.util.List;


import dao.impl.QueryDaoJdbcImpl;
import domain.Cluster;
import domain.Page;
import domain.ProcessingQuery;
import domain.Query;
import domain.Sim;

public class BusinessService {

	QueryDaoJdbcImpl dao = new QueryDaoJdbcImpl();
	
	public Page getPageData(String pagenum,String url,String sql,String totalRecordSql){
		int totalrecord = dao.getTotalrecord(totalRecordSql);
		if(pagenum==null){
			//代表用户想看第一页的数据
			Page page = new Page(totalrecord,1);  //算出了总页数，以及用户想看的页从数据库哪个地方开始取
			List list = dao.getPageData(page.getStartindex(), page.getPagesize(),sql);
			page.setList(list);
			page.setUrl(url);
			return page;
		}else{
			//代表用户想看指定的页
			Page page = new Page(totalrecord,Integer.parseInt(pagenum)); 
			List list = dao.getPageData(page.getStartindex(), page.getPagesize(),sql);
			page.setList(list);
			page.setUrl(url);
			return page;
		}
	}
	
	public int getTotalrecord(String sql){
		return dao.getTotalrecord(sql);
	}
	public List getData(int startIndex,int size,String sql){
		return dao.getPageData(startIndex, size, sql);
	}
	public List getData(Object[]params,String sql){
		return dao.getData(params, sql);
	}
	public void addPquerys(List<ProcessingQuery> data){
		dao.addPquerys(data);
	}
	public  void addSims(List<Sim>sims){
		dao.addSims(sims);
	}
	public void addClusters(List<Cluster>clusters){
		dao.addClusters(clusters);
	}
	public void addClustersFilter(List<Cluster>clusters){
		dao.addClustersFilter(clusters);
	}
}
