package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.Cluster;
import domain.ProcessingQuery;
import domain.Sim;



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
			for(int i=0;params!=null&&i<params.length;i++){
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
			for(int i=0;params!=null&&i<params.length;i++){
				st.setObject(i+1, params[i]);
			}
			rs = st.executeQuery();
			return rsh.handler(rs);
			
		}finally{
			release(conn, st, rs);
		}
	}
	public static ResultSet query(String sql,Object params[]) throws SQLException{
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for(int i=0;params!=null&&i<params.length;i++){
				st.setObject(i+1, params[i]);
			}
			rs = st.executeQuery();
			return rs;
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
	public static void addSims(List<Sim>sims) throws SQLException{
		Connection conn = null;
		conn = getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("insert into sims (sim,queryID1,queryID2,querySim,urlSim,sessionSim,probaseSim) values (?,?,?,?,?,?,?)");
		for(int i=0;i<sims.size();i++){
			Sim sim=sims.get(i);
			st.clearParameters();
			st.setDouble(1, sim.getSim());
			st.setString(2, sim.getQueryID1());
			st.setString(3, sim.getQueryID2());
			st.setDouble(4, sim.getQuerySim());
			st.setDouble(5, sim.getUrlSim());
			st.setDouble(6, sim.getSessionSim());
			st.setDouble(7, sim.getProbaseSim());
			st.execute();
		}
		
		conn.commit();
		st.close();
		conn.close();
	}
	public static void addClusters(List<Cluster> clusters) throws SQLException{
		Connection conn = null;
		conn = getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("insert into clusters " +
				"(clusterID,content,queryID,pQuery,topicPart,timePart,sessionID) values (?,?,?,?,?,?,?)");
		for(Cluster c:clusters){
			st.clearParameters();
			st.setLong(1, c.getClusterID());
			st.setString(2, c.getContent());
			st.setString(3, c.getQueryID());
			st.setString(4, c.getpQuery());
			st.setString(5, c.getTopicPart());
			st.setString(6, c.getTimePart());
			st.setString(7, c.getSessionID());
			st.execute();
		}
		
		conn.commit();
		st.close();
		conn.close();
	}
	public static void addClustersFilter(List<Cluster> clusters) throws SQLException{
		Connection conn = null;
		conn = getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("insert into clusters_filter " +
				"(clusterID,content,queryID,pQuery,topicPart,timePart,sessionID) values (?,?,?,?,?,?,?)");
		for(Cluster c:clusters){
			st.clearParameters();
			st.setLong(1, c.getClusterID());
			st.setString(2, c.getContent());
			st.setString(3, c.getQueryID());
			st.setString(4, c.getpQuery());
			st.setString(5, c.getTopicPart());
			st.setString(6, c.getTimePart());
			st.setString(7, c.getSessionID());
			st.execute();
		}
		
		conn.commit();
		st.close();
		conn.close();
	}
	public static void addSessionID(HashMap data) throws SQLException{
		
		Connection conn =null;
		//Connection conn=DriverManager.getConnection(null);
		conn = getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("insert into sessions (sessionID,content) values (?, ?)");
		for (Object obj : data.keySet()) {
			String key = (String) obj;
			String value = (String) data.get(key);
			st.setString(1, key);
			st.setString(2, value);
			st.addBatch();
		}
		st.executeBatch();
		conn.commit();
		st.clearBatch();
		st.close();
		conn.close();
	}
}













