package utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class BeanHandler implements ResultSetHandler {
	private Class clazz;
	public BeanHandler(Class clazz){
		this.clazz = clazz;
	}
	public Object handler(ResultSet rs) {
		try{
			if(!rs.next()){
				return null;
			}
			Object bean = clazz.newInstance();
			
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();  //�õ�������м������
			for(int i=0;i<columnCount;i++){
				String coulmnName = metadata.getColumnName(i+1);  //�õ�ÿ�е�����
				Object coulmnData = rs.getObject(i+1);
				
				Field f = clazz.getDeclaredField(coulmnName);//��������������Ӧ������
				f.setAccessible(true);
				f.set(bean, coulmnData);
			}
			return bean;
		
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
