package domain;

import java.util.Date;
import java.util.List;

public class Query {
	private Date time;
	private String content;
	private String id;
	private String sessionID;
	private int resultCount;
//	private List<Click> clicks;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	public int getResultCount() {
		return resultCount;
	}
	
}
