package sample.model;

public class ReqInfo {

	public ReqInfo() {
		System.out.println("ReqInfo 생성자");
	}
	
	private String uri = "";
	private long startTime = 0L;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
}
