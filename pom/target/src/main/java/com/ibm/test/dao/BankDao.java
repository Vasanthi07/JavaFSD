package com.ibm.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDao implements BankInterface{
	Connection dbCon;
	Statement  preparedStatement;
	String queryToExecute;
	
	
	
//	public void connectToDbpublic BankDao(Connection dbCon, Statement preparedStatement, String queryToExecute) {
//		
//		this.dbCon = dbCon;
//		this.preparedStatement = preparedStatement;
//		this.queryToExecute = queryToExecute;
      String UrlToConnect = "jdbc:mysql://localhost:3306/banking";
		String userName = "root";
		String userPassword = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon  = DriverManager.getConnection(UrlToConnect, userName, userPassword);
			try {
				preparedStatement = dbCon.createStatement();
			}catch(SQLException e) {
				System.out.println("Issues while getting statement:" + e.getMessage());
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("can't load the driver:" +e.getMessage());
		}catch(SQLException e) {
			System.out.println("can't connect to data base:" +e.getMessage());
		}
	}
	public BankDao(Connection dbCon, Statement preparedStatement, String queryToExecute) {
		super();
		this.dbCon = dbCon;
		this.preparedStatement = preparedStatement;
		this.queryToExecute = queryToExecute;
	}
	@Override
	public boolean createAccount(Bank bank) {
		queryToExecute = "Enter into bank account(name,number,address) values(?,?,?)";
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(queryToExecute);
			thePreparedStatement.setString(1,bank.getName());
			thePreparedStatement.setString(2,bank.getNumber());
			thePreparedStatement.setString(3,bank.getAddress());
			return thePreparedStatement.executeUpdate()>0;
			}catch(SQLException e) {
				System.out.println("Issues while loading the content:" +e.getMessage());
			}
		return false;
	}
@Override
public boolean deposit(Bank bank) {
	queryToExecute = "Deposit the amount into the account(accNumber) values(?)";
	try {
		PreparedStatement thePreparedStatement = dbCon.prepareStatement(queryToExecute);
		thePreparedStatement.setString(1, bank.getAccNumber());
	}catch(SQLException e) {
		System.out.println("Issues while entering the number:" + e.getMessage());
	}
	  return false;
}
@Override
public boolean withdrawl(Bank bank) {
	queryToExecute = "withdrawl the amount into the account(accNumber) values(?)";
	try {
		PreparedStatement thePreparedStatement = dbCon.prepareStatement(queryToExecute);
		thePreparedStatement.setString(1, bank.getAccNumber());
	}catch(SQLException e) {
		System.out.println("Issues while entering the number:" + e.getMessage());
	}
	  return false;
}
@Override
public boolean fundTransfer(Bank bank) {
	queryToExecute = "Transfer the amount from the account(accNumber,balance) values(?,?)";
	try {
		PreparedStatement thePreparedStatement = dbCon.prepareStatement(queryToExecute);
		thePreparedStatement.setString(1, bank.getAccNumber());
		thePreparedStatement.setString(2, bank.getBalance());
		}catch(SQLException e) {
		System.out.println("Issues while transferring the amount:" + e.getMessage());
	}
	  return false;
}
@Override
public boolean printTransactions(Bank bank) {
	queryToExecute = "Print the transactions of the account(accNumber) values(?)";
	try {
		PreparedStatement thePreparedStatement = dbCon.prepareStatement(queryToExecute);
		thePreparedStatement.setString(1, bank.getAccNumber());
		
		}catch(SQLException e) {
		System.out.println("Issues while getting  the transactions:" + e.getMessage());
	}
	  return false;

}
	
	
	
	
	
	
	
}

