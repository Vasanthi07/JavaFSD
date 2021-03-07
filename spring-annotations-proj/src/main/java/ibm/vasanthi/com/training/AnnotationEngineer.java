package ibm.vasanthi.com.training;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component("anEngineer")
public class AnnotationEngineer implements Engineer {

	//@Autowired
	
	//@Resources
	Address theAddress;
	@Autowired
	List<String> nameOfEngineers;
//	@Autowired
//	public AnnotationEngineer(Address theAddress) {
//		this.theAddress = theAddress;
//	}
//	public Address getTheAddress() {
//		return theAddress;
//	}
//	@Autowired //first looks byType ,if multiple beans found of the same type then looks by name
//	@Qualifier("secondAddress")//looks for the exact matching
//	public void setTheAddress(Address theAddress) {
//		this.theAddress = theAddress;
//	}

	@Override
	public void workForWages() {
		System.out.println("Engineers do work here");
		System.out.println(theAddress.getLocation() + "," + theAddress.getPincode());
		System.out.println("list of engineers currently available");
		for(String nameOfEngineer:nameOfEngineers) {
			System.out.println(nameOfEngineer);
		}

	}

}
