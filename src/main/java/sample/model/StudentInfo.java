package sample.model;

public class StudentInfo {

	public StudentInfo() {
		System.out.println("StudentInfo 생성자");
	}
	
	private String studentId = "";
	private String studentName = "";
	private String studentAddress = "";
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	
}
