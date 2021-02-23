package com.ibm.eis.ui;

import java.util.Scanner;

import com.ibm.eis.bean.Employee;
import com.ibm.eis.service.EmployeeService;
import com.ibm.eis.service.EmployeeServiceInterface;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Employee employee = new Employee();
		EmployeeServiceInterface service = new EmployeeService();
		int id;
		String name;
		int salary;
		String designation;
		String scheme = "";
		for (;;) {
			System.out.println("1.To enter user details: \n 2:Get scheme by comparing \n 3:Display Employee Details");
			int a = sc.nextInt();
			if (a == 1) {
				//enter the details of employees except scheme
				System.out.println("Enter Employee Details:");
				System.out.println("Enter id:");
				id = sc.nextInt();
				System.out.println("Enter name: ");
				sc.nextLine();
				name = sc.nextLine();
				System.out.println("Enter salary:");
				salary = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Designation:");
				designation = sc.nextLine();
				// sc.nextLine();
				//set methods from employee class
				employee.setId(id);
				employee.setName(name);
				employee.setSalary(salary);
				employee.setDesignation(designation);
			} else if (a == 2) {
				System.out.println("Set Insurance Scheme:");
				designation=employee.getDesignation();
				salary = employee.getSalary();
				//System.out.println(designation);
				//System.out.println(salary);
				//get insurance scheme of an employee ..
				//validate using service reference and use service class methods
				scheme = employee.setInsuranceScheme(service.validateScheme(designation, salary));
				service.storeIntoTable(employee);
			} else if (a == 3) {
				//call service display method which in-turn returns dao method display method
				System.out.println("Display employees Details: ");
				service.displayTable();
			}
			else {
				break;
			}
		}

	}
}
