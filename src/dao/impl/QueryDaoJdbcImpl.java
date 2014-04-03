package dao.impl;

import java.sql.SQLException;
import java.util.List;


import utils.BeanListHandler;
import utils.IntegerHandler;
import utils.JdbcUtils;

import domain.Click;
import domain.ProcessingQuery;
import domain.Query;
import domain.Sim;
import exception.DaoException;

public class QueryDaoJdbcImpl {
	public Query find(String content){
		return null;
	}
	//获取分页数据
	public List getPageData(int startindex,int pagesize,String sql){
		try{
			
			Object params[] = {startindex,pagesize};
			if(sql.contains("queries"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Query.class));
			if(sql.contains("clicks"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Click.class));
			if(sql.contains("processing"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(ProcessingQuery.class));
			return null;
		}catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	public List getData(Object[]params,String sql){
		try{
			if(sql.contains("queries"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Query.class));
			if(sql.contains("clicks"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Click.class));
			if(sql.contains("processing"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(ProcessingQuery.class));
			if(sql.contains("Sim"))
				return (List) JdbcUtils.query(sql, params, new BeanListHandler(Sim.class));
			return null;
		}catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	public int getTotalrecord(String sql){
		try{
			Object params[] = {};
			long l =  (Long) JdbcUtils.query(sql,params,new IntegerHandler());
			return (int)l;
		}catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	public void addPquerys(List <ProcessingQuery> data){
		try {
			JdbcUtils.addPquerys(data);
		} catch (Exception e) {
			 throw new DaoException(e);
		}
	}
	public void addSims(List<double[]>sims){
		try {
			JdbcUtils.addSims(sims);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
