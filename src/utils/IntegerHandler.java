package utils;

import java.sql.ResultSet;

public class IntegerHandler implements ResultSetHandler {

	public Object handler(ResultSet rs) {
		try{
			if(!rs.next()){
				return 0;
			}
			return rs.getObject(1);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
