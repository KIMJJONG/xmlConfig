package sample.model;

public class SoliderInfo {

	public SoliderInfo() {
		System.out.println("SoliderInfo 생성자");
	}
	
	private String soliderId = "";
	private String soliderName = "";
	private String soliderAddress = "";
	
	public String getSoliderId() {
		return soliderId;
	}
	public void setSoliderId(String soliderId) {
		this.soliderId = soliderId;
	}
	public String getSoliderName() {
		return soliderName;
	}
	public void setSoliderName(String soliderName) {
		this.soliderName = soliderName;
	}
	public String getSoliderAddress() {
		return soliderAddress;
	}
	public void setSoliderAddress(String soliderAddress) {
		this.soliderAddress = soliderAddress;
	}
	
}
