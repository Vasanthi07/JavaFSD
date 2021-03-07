package ibm.vasanthi.com.training;

public class Employee {


	private int empid;
	private String empname;
	private String address;
	public Employee(String empname, String address) {
		this.empname = empname;
		this.address = address;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", address=" + address + "]";
	}
	public Employee(String empname, String address,int empid) {
		this.empname = empname;
		this.address = address;
		this.empid=empid;
	}
	
	public Employee() {}
}
