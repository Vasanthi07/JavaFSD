package ibm.vasanthi.com.bo.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;
import ibm.vasanthi.com.bo.service.CustomerServiceInterface;
import ibm.vasanthi.com.bo.service.CustomerServices;
import ibm.vasanthi.com.bo.ui.Ui;

public class App {
	public static void main(String[] args) {
		// load the context
		ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("appConfig.xml");
		CustomerDetails theCustomer = theContext.getBean("theCustomer", CustomerDetails.class);
		CustomerServiceInterface service = theContext.getBean("theService", CustomerServices.class);
		TransactionDetails transaction = theContext.getBean("theTransaction", TransactionDetails.class);
		Ui ui = theContext.getBean("ui",Ui.class);
		ui.mainUi();
		
		// create a scanner ref
			}
}
