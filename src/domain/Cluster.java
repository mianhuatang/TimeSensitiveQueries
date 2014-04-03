package domain;

public class Cluster {
	private String content;
	private String queryID;
	private String pQuery;
	private String topicPart;
	private String timePart;
	private String sessionID;
	private int clusterID=0;
	private Boolean isVisited=false;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQueryID() {
		return queryID;
	}
	public void setQueryID(String queryID) {
		this.queryID = queryID;
	}
	public String getpQuery() {
		return pQuery;
	}
	public void setpQuery(String pQuery) {
		this.pQuery = pQuery;
	}
	public String getTopicPart() {
		return topicPart;
	}
	public void setTopicPart(String topicPart) {
		this.topicPart = topicPart;
	}
	public String getTimePart() {
		return timePart;
	}
	public void setTimePart(String timePart) {
		this.timePart = timePart;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public int getClusterID() {
		return clusterID;
	}
	public void setClusterID(int clusterID) {
		this.clusterID = clusterID;
	}
	public Boolean getIsVisited() {
		return isVisited;
	}
	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}
}
