package ibm.vasanthi.com.training;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SoftwareEngineer implements Engineer {
	private Address theAddress;

	public Address getTheAddress() {
		return theAddress;
	}

	public void setTheAddress(Address theAddress) {
		this.theAddress = theAddress;
	}

	public SoftwareEngineer(Address theAddress) {
		this.theAddress = theAddress;
	}
	public SoftwareEngineer() {
		
	}

	@Override
	public void doWork() {
		System.out.println("Im in software engineer class..:");
		System.out.println("Location is : " + this.getTheAddress().getLocation() + ", pincode is: "
				+ this.getTheAddress().getPincode());
	}
	public void callAtBeginning() {
		System.out.println("initializing bean..");
	}
	
	public void callAtEnd() {
		System.out.println("end of bean..");
	}

//	@Override
//	public void afterPropertiesSet() throws Exception {
//		System.out.println("intitalizing bean....");
//		
//	}
//
//	@Override
//	public void destroy() throws Exception {
//		System.out.println("destroying bean..");
//		
//	}
}
