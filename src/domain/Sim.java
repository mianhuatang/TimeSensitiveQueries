package domain;

public class Sim {
	private double sim;
	private String queryID1;
	private String queryID2;
	private double querySim;
	private double urlSim;
	private double sessionSim;
	private double probaseSim;
	public double getSim() {
		return sim;
	}
	public void setSim(double sim) {
		this.sim = sim;
	}
	public String getQueryID1() {
		return queryID1;
	}
	public void setQueryID1(String queryID1) {
		this.queryID1 = queryID1;
	}
	public String getQueryID2() {
		return queryID2;
	}
	public void setQueryID2(String queryID2) {
		this.queryID2 = queryID2;
	}
	public double getQuerySim() {
		return querySim;
	}
	public void setQuerySim(double querySim) {
		this.querySim = querySim;
	}
	public double getUrlSim() {
		return urlSim;
	}
	public void setUrlSim(double urlSim) {
		this.urlSim = urlSim;
	}
	public double getSessionSim() {
		return sessionSim;
	}
	public void setSessionSim(double sessionSim) {
		this.sessionSim = sessionSim;
	}
	public double getProbaseSim() {
		return probaseSim;
	}
	public void setProbaseSim(double probaseSim) {
		this.probaseSim = probaseSim;
	}
}
