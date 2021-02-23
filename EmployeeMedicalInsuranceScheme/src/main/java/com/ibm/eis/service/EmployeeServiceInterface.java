package com.ibm.eis.service;

import com.ibm.eis.bean.Employee;

public interface EmployeeServiceInterface {
	String validateScheme(String designation,int salary);
	void storeIntoTable(Employee employee);
	void displayTable();
	
}
