package ibm.vasanthi.com.training;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("appConfig.xml");

		DaoData dao = theContext.getBean("daoData",DaoData.class);
		// System.out.println("No of records are: "+dao.countNoOfRecords());

		NamedparamedDemo named = theContext.getBean("namedParam", NamedparamedDemo.class);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee id:");
		int empNo = sc.nextInt();
		sc.nextLine();

		System.out.println("enter employee address:");
		String address = sc.nextLine();
		
		System.out.println("Enter name:");
		String name = sc.nextLine();

		// System.out.println(empNo+" "+address);
		// System.out.println("employee name of "+empNo+"
		// is"+dao.nameOfEmployee(empNo));
		// System.out.println("employee name of "+empNo+" and address at"+address+"
		// is"+dao.getEmployeeNameByIdAndAddress(empNo, address));
		// Employee employee= dao.getAllRecordsById(empNo);
		// System.out.println(employee);
		// dao.updateAnEmployee(new Employee(name,address,empNo));

//		List<Employee> listOfEmployees = dao.getAllRecords();
//		System.out.println("list of employees:");
//		listOfEmployees.forEach(System.out::println);
		// System.out.println("list of employees after inserting:");

//		  dao.updateAnEmployee(new Employee(name,address,empNo));
//		  System.out.println("list of employees after inserting:");
//		  listOfEmployees.forEach(System.out::println); dao.deleteAnEmployee(empNo);
//		  System.out.println("list of employees after deleting:");
//		  listOfEmployees.forEach(System.out::println);  
		// System.out.println(named.getNameById(empNo, address));
		
		//System.out.println(name+" "+address+" "+empNo);
		//named.updateAnEmployee(new Employee(name,address,empNo));
		named.insertAnEmployee(new Employee(name,address,empNo));
		dao.updateAnEmployee(new Employee(name,address));
	}

}
