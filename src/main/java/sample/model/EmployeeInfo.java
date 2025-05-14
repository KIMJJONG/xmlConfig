package sample.model;

public class EmployeeInfo {

	public EmployeeInfo() {
		System.out.println("EmployeeInfo 생성자");
	}
	
	private String employeeId = "";
	private String employeeName = "";
	private String employeeAddress = "";
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	
}
