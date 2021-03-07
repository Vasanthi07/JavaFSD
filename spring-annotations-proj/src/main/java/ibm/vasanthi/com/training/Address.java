package ibm.vasanthi.com.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:myPro.properties")//bind the property to the key value pair in the properties file
@Component
public class Address {
	
	@Value("${engineer.location}")
	private String location;
	@Value("${engineer.pincode}")
	private String pincode;
	public String getLocation() {
		return location;
	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
	public String getPincode() {
		return pincode;
	}
//	public void setPincode(String pincode) {
//		this.pincode = pincode;
//	}
}
