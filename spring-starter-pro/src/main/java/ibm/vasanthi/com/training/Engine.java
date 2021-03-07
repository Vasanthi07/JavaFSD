package ibm.vasanthi.com.training;

import java.util.List;

interface Engineer{
	void doWork();
}
//public class Engine {
//	private String engName;
//	private int engId;
//	private List<Address> listOfAddress;
//	
//	
////public Engine(String engName, int engId, List<Address> listOfAddress) {
////		this.engName = engName;
////		this.engId = engId;
////		this.listOfAddress = listOfAddress;
////	}
//
////public Engine(String engName, int engId, Address address) {
////		this.engName = engName;
////		this.engId = engId;
////		this.address = address;
////	}
//
//public List<Address> getListOfAddress() {
//		return listOfAddress;
//	}
//
//	public void setListOfAddress(List<Address> listOfAddress) {
//		this.listOfAddress = listOfAddress;
//	}
//
//	//	public Engine(String engName, int engId) {
////		this.engName = engName;
////		this.engId = engId;
////	}
////
////	public Address getAddress() {
////	return address;
////}
////
////public void setAddress(Address address) {
////	this.address = address;
////}
////
//	public String getEngName() {
//		return engName;
//	}
//
//	public void setEngName(String engName) {
//		this.engName = engName;
//	}
//
//	public int getEngId() {
//		return engId;
//	}
//
//	public void setEngId(int engId) {
//		this.engId = engId;
//	}
//
//	public void doWork() {
//		System.out.println("Engineer Name: "+this.getEngName()+" with Id: "+this.getEngId());
//		System.out.println("list of addresses: ");
//		for(Address address: listOfAddress) {
//			System.out.println("Location :"+address.getLocation()+",pincode :"+address.getPincode());
//		}
//	}
//}
