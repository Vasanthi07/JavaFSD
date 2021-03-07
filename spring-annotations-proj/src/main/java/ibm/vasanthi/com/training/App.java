package ibm.vasanthi.com.training;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Configuration
public class App {
	public static void main(String[] args) {
		// load the context
		ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("appConfig.xml");
		//Engineer theEngineer = theContext.getBean("annotationEngineer", AnnotationEngineer.class);
		Engineer theEngineer = theContext.getBean("anEngineer", AnnotationEngineer.class);
		theEngineer.workForWages();
	}
	
	@Bean
	public List<String> nameOfEngineers(){
		return Arrays.asList("vasanthi","akhil");
	}
}
