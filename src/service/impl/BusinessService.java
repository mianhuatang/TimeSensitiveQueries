package service.impl;

import java.util.List;


import dao.impl.QueryDaoJdbcImpl;
import domain.Page;
import domain.Query;

public class BusinessService {

	QueryDaoJdbcImpl dao = new QueryDaoJdbcImpl();
	
//	public void addCustomer(Customer customer){
//		dao.add(customer);
//	}
	
	
	
//	public Page getPageData(String pagenum,String url){
//		int totalrecord = dao.getTotalrecord();
//		if(pagenum==null){
//			//代表用户想看第一页的数据
//			Page page = new Page(totalrecord,1);  //算出了总页数，以及用户想看的页从数据库哪个地方开始取
//			List list = dao.getPageData(page.getStartindex(), page.getPagesize());
//			page.setList(list);
//			page.setUrl(url);
//			return page;
//		}else{
//			//代表用户想看指定的页
//			Page page = new Page(totalrecord,Integer.parseInt(pagenum)); 
//			List list = dao.getPageData(page.getStartindex(), page.getPagesize());
//			page.setList(list);
//			page.setUrl(url);
//			return page;
//		}
//	}
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
//	public Page getPageData(String pagenum,String url,String id){
//		int totalrecord = dao.getTotalrecord();
//		if(pagenum==null){
//			//代表用户想看第一页的数据
//			Page page = new Page(totalrecord,1);  //算出了总页数，以及用户想看的页从数据库哪个地方开始取
//			List list = dao.getPageData(page.getStartindex(), page.getPagesize(),id);
//			page.setList(list);
//			page.setUrl(url);
//			return page;
//		}else{
//			//代表用户想看指定的页
//			Page page = new Page(totalrecord,Integer.parseInt(pagenum)); 
//			List list = dao.getPageData(page.getStartindex(), page.getPagesize(),id);
//			page.setList(list);
//			page.setUrl(url);
//			return page;
//		}
//	}
//	public Query findQuery(String content) {
//		return dao.find(content);
//	}

//	public void updateCustomer(Customer c) {
//		dao.update(c);
//	}
//
//	public void deleteCustomer(String id) {
//		dao.delete(id);
//		
//	}
	
}
