package com.ibm.eis.dao;

import com.ibm.eis.bean.Employee;

public interface DaoInterface {
	void storeIntoTable(Employee employee);
	void displayTable();
}
