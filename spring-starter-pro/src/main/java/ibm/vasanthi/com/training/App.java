package ibm.vasanthi.com.training;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("appConfig.xml");
    	SoftwareEngineer engine = theContext.getBean("softwareEngineer",SoftwareEngineer.class);
    	//SoftwareEngineer anotherEngine = theContext.getBean("softwareEngineer",SoftwareEngineer.class);
    	//System.out.println(engine==anotherEngine);
    	engine.doWork();
    }
}
