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



public class JdbcUtils {
	
	private static ComboPooledDataSource ds = null;
	static{
		try{
			/*ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/day16");
			ds.setUser("root");
			ds.setPassword("root");
			
			ds.setInitialPoolSize(10);
			ds.setMinPoolSize(5);
			ds.setMaxPoolSize(20);*/
			
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
}













