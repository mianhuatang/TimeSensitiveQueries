package dao.impl;

import java.util.List;

import utils.BeanListHandler;
import utils.IntegerHandler;
import utils.JdbcUtils;

import domain.Click;
import domain.Query;
import exception.DaoException;

public class QueryDaoJdbcImpl {
	public Query find(String content){
		return null;
	}
	//获取分页数据
//	public List<Query> getPageData(int startindex,int pagesize){
//		try{
//			String sql = "select * from queries limit ?,?";
//			Object params[] = {startindex,pagesize};
//			return (List<Query>) JdbcUtils.query(sql, params, new BeanListHandler(Query.class));
//		}catch (Exception e) {
//			 throw new DaoException(e);
//		}
//	}
	
//	public List<Click> getPageData(int startindex,int pagesize,String id){
//		try{
//			String sql = "select * from clicks where queryID='"+id+"' limit ?,?";
//			Object params[] = {startindex,pagesize};
//			return (List<Click>) JdbcUtils.query(sql, params, new BeanListHandler(Query.class));
//		}catch (Exception e) {
//			 throw new DaoException(e);
//		}
//	}
//	public List<Click> getPageData(int startindex,int pagesize,String sql){
//		try{
//			
//			Object params[] = {startindex,pagesize};
//			return (List<Click>) JdbcUtils.query(sql, params, new BeanListHandler(Query.class));
//		}catch (Exception e) {
//			 throw new DaoException(e);
//		}
//	}
	public List getPageData(int startindex,int pagesize,String sql){
		try{
			
			Object params[] = {startindex,pagesize};
			if(sql.contains("queries"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Query.class));
			if(sql.contains("clicks"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Click.class));
			return null;
		}catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	//得到总记录数
//	public int getTotalrecord(){
//		try{
//			String sql = "select count(*) from queries";
//			Object params[] = {};
//			long l =  (Long) JdbcUtils.query(sql,params,new IntegerHandler());
//			return (int)l;
//		}catch (Exception e) {
//			 throw new DaoException(e);
//		}
//	}
	public int getTotalrecord(String sql){
		try{
			Object params[] = {};
			long l =  (Long) JdbcUtils.query(sql,params,new IntegerHandler());
			return (int)l;
		}catch (Exception e) {
			 throw new DaoException(e);
		}
	}
}
