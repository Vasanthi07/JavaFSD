package com.ibm.eis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibm.eis.bean.Employee;

public class DaoClass implements DaoInterface {
	String theQuerytoExecute;
	ResultSet resultSet;
	Connection dbcon;
	PreparedStatement preparedStatement;
	public DaoClass() {
	String urlToConnect = "jdbc:mysql://localhost:3306/javafsd";
	String userName = "root";
	String password = "";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbcon = DriverManager.getConnection(urlToConnect, userName, password);

	} catch (ClassNotFoundException e) {
		System.out.println("cant load class .." + e.getMessage());
	} catch (SQLException e) {
		System.out.println("cannot find db..." + e.getMessage());
	}
	}
	public void storeIntoTable(Employee employee) {
		//insert records into the database table employees based on the user queries
	theQuerytoExecute="insert into employees1(empid,empname,salary,designation,scheme) values(?,?,?,?,?)";
	try
	{
		preparedStatement = dbcon.prepareStatement(theQuerytoExecute);
		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setInt(3, employee.getSalary());
		preparedStatement.setString(4, employee.getDesignation());
		preparedStatement.setString(5, employee.getInsuranceScheme());
		if (preparedStatement.executeUpdate() > 0) {
			System.out.println("inserted succesfully");
		} else {
			System.out.println("issues..");
		}

	}catch(SQLException e)
	{
		System.out.println("cant find db,...." + e.getMessage());
	}
	}
	public void displayTable() {
		//display the table employees
		String dispQuery = "select * from employees1";
		try {
			preparedStatement = dbcon.prepareStatement(dispQuery);
			resultSet = preparedStatement.executeQuery(dispQuery);
			while(resultSet.next()) {
				System.out.println("Id: "+resultSet.getInt("empid")+
						" Name: "+resultSet.getString("empname")+" salary: "+resultSet.getInt("salary")+
						"designation: "+resultSet.getString("scheme"));
			}
		} catch (SQLException e) {
			System.out.println("cant load db.."+e.getMessage());
		}
		
	}
}
