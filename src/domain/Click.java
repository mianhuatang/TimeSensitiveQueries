package domain;

import java.util.Date;

public class Click {
	private Date time;
	private String queryID;
	private String url;
	private int position;
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getQueryID() {
		return queryID;
	}
	public void setQueryID(String queryID) {
		this.queryID = queryID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
