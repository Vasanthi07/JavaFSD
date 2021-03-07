package com.ibm.test.ui;

import com.ibm.test.bean.BankTest;

public class AllMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("parallelAssignment.xml"k);
		BankTest bankClass = theContext.getBean("bankClass", BankTest.class);
		(bankClass).list1();
	}

}
