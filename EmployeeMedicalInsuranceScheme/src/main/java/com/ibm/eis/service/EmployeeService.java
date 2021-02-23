package com.ibm.eis.service;

import com.ibm.eis.bean.Employee;
import com.ibm.eis.dao.DaoClass;

public class EmployeeService implements EmployeeServiceInterface {
	DaoClass dao = new DaoClass();
	Employee employee = new Employee();

	@Override
	public String validateScheme(String designation, int salary) {
		//salary = employee.getSalary();
		//designation = employee.getDesignation();
		//compare salary and designation to get the insurance scheme and sets scheme value to required schemeS
		if(salary>=5000 && salary<20000 && designation.equals("System Associate")) {
			return "Scheme C";
		}
		else if(salary>=20000 && salary<40000 && designation.equals("Programmer")) {
			return "Scheme B";
		}
		else if(salary>=40000 && designation.equals("Manager")) {
			return "Scheme A";
		}
		else if(salary<5000 && designation.equals("Clerk")) {
			return "No Scheme";
		}
		return "out of scheme";
	}

	@Override
	public void storeIntoTable(Employee employee) {
		//updated details of employee with scheme will be sent from main and we have to send these details to store
		//in employee table which will be inserted in dao class
		dao.storeIntoTable(employee);
	}
	@Override
	public void displayTable() {
		//updated employee table will be displayed by calling dao displaytable..
		 dao.displayTable();
	}
}
