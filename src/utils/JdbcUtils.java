package utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.ProcessingQuery;



public class JdbcUtils {
	
	private static ComboPooledDataSource ds = null;
	static{
		try{
			ds = new ComboPooledDataSource();
			
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		
		return ds.getConnection();
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){
		
		if(rs!=null){
			try{
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;

		}
		if(st!=null){
			try{
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//替换dao中的增删改方法
	public static void update(String sql,Object params[]) throws SQLException{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				st.setObject(i+1, params[i]);
			}
			st.executeUpdate();
			
		}finally{
			release(conn, st, rs);
		}
	}
	
	//替换所有dao中的查询   策略模式
	public static Object query(String sql,Object params[],ResultSetHandler rsh) throws SQLException{
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				st.setObject(i+1, params[i]);
			}
			rs = st.executeQuery();
			return rsh.handler(rs);
			
		}finally{
			release(conn, st, rs);
		}
	}
	public static void addPquerys(List <ProcessingQuery> data) throws SQLException{
		Connection conn = null;
		conn = getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("insert into processing (content,queryID,sessionID,pQuery,topicPart,timePart) values (?, ?, ?,?, ?, ?)");
		for(ProcessingQuery pquery:data){
			st.clearParameters();
			st.setString(1, pquery.getContent());
			st.setString(2, pquery.getQueryID());
			st.setString(3,pquery.getSessionID());
			st.setString(4, pquery.getpQuery());
			st.setString(5, pquery.getTopicPart());
			st.setString(6, pquery.getTimePart());
			st.execute();
		}
		conn.commit();
		st.close();
		conn.close();
	}
	public static void addSims(List<double[]>sims) throws SQLException{
		Connection conn = null;
		conn = getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("insert into sims (sim,queryID1,queryID2,querySim,urlSim,sessionSim,probaseSim) values (?,?,?,?,?,?,?)");
		for(int i=0;i<sims.size();i++){
			double[] sim=sims.get(i);
			st.clearParameters();
			st.setDouble(1, sim[0]);
			st.setDouble(2, sim[1]);
			st.setDouble(3, sim[2]);
			st.setDouble(4, sim[3]);
			st.setDouble(5, sim[4]);
			st.setDouble(6, sim[5]);
			st.setDouble(7, sim[6]);
			st.execute();
		}
		
		conn.commit();
		st.close();
		conn.close();
	}
}













