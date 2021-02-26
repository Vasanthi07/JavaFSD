package ibm.vasanthi.com.bo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;

@Component("theDao")
public class CustomerDaoClass implements CustomerDaoInterface {

	String theQuerytoExecute;
	ResultSet resultSet;
	Connection dbcon;
	PreparedStatement preparedStatement;

	public CustomerDaoClass() {
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

	@Override
	public boolean checkPhoneNo(String phoneNo) {
		theQuerytoExecute = "select * from bankcustomers";
		try {
			resultSet = preparedStatement.executeQuery(theQuerytoExecute);
			while (resultSet.next()) {
				if (phoneNo.equals(resultSet.getString("phoneNo"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("cant load db in check phoneno: " + e.getMessage());
		}
		return false;
	}

	public void storeIntoTable(CustomerDetails theCustomer) {
		// insert records into the database table employees based on the user queries
		theQuerytoExecute = "insert into bankcustomers(cutsomerName,amount,phoneNo) values(?,?,?)";
		try {
			preparedStatement = dbcon.prepareStatement(theQuerytoExecute, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, theCustomer.getCustomerName());
			preparedStatement.setInt(2, theCustomer.getAmount());
			preparedStatement.setString(3, theCustomer.getPhoneNo());
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("inserted succesfully");
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet != null && resultSet.next()) {
					int acc = resultSet.getInt(1);
					theCustomer.setAccountNo(acc);
				}
			} else {
				System.out.println("issues.. in storeIntoTable of dao class..");
			}

		} catch (SQLException e) {
			System.out.println("cant find db,...." + e.getMessage());
		}
	}

	public void displayTable() {
		// display the table employees
		String dispQuery = "select * from bankcustomers";
		try {
			preparedStatement = dbcon.prepareStatement(dispQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(" Name: " + resultSet.getString("cutsomerName") + " accountNo: "
						+ resultSet.getInt("accountNo") + "amount :" + resultSet.getInt("amount") + "phone number: "
						+ resultSet.getString("phoneNo"));
			}
		} catch (SQLException e) {
			System.out.println("cant load db.." + e.getMessage());
		}

	}

	@Override
	public int checkBalance(int accountNo) {
		int amount = 0;
		String dispQuery2 = "select * from bankcustomers";
		try {
			preparedStatement = dbcon.prepareStatement(dispQuery2);
			resultSet = preparedStatement.executeQuery();
			boolean flag = false;
			while (resultSet.next()) {
				if (resultSet.getInt("accountNo") == accountNo) {
					flag = true;
					amount = resultSet.getInt("amount");

				}
			}
			if (flag == false) {
				System.out.println("please check your account number..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}

	@Override
	public void updateAmmount(int amount1, int depositMoney, int accountNo) {
		String queryTOExecute = "select * from bankcustomers";
		try {
			// create a reference to a prepareStatement with overloading prepared statement
			// with result set..
			preparedStatement = dbcon.prepareStatement(queryTOExecute, resultSet.TYPE_SCROLL_SENSITIVE,
					resultSet.CONCUR_UPDATABLE);
			// execute query
			resultSet = preparedStatement.executeQuery();
			// traverse
			boolean flag = false;
			while (resultSet.next()) {
				if (resultSet.getInt("accountNo") == accountNo) {
					flag = true;
					resultSet.updateInt(3, amount1 + depositMoney);
					// commit the updated results
					resultSet.updateRow();
					System.out.println("Deposited Succesfully");
				}
			}
			if (flag == false) {
				System.out.println("Please check your account number...");
			}
		} catch (SQLException e) {
			System.out.println("cant load db,..." + e.getMessage());
		}

	}

	public void withdrawAmmount(int amount1, int withdrawMoney, int accountNo) {
		String queryTOExecute = "select * from bankcustomers";
		try {
			// create a reference to a prepareStatement with overloading prepared statement
			// with result set..
			preparedStatement = dbcon.prepareStatement(queryTOExecute, resultSet.TYPE_SCROLL_SENSITIVE,
					resultSet.CONCUR_UPDATABLE);
			// execute query
			resultSet = preparedStatement.executeQuery();
			boolean flag = false;
			// traverse
			while (resultSet.next()) {
				if (resultSet.getInt("accountNo") == accountNo) {
					flag = true;
					resultSet.updateInt(3, amount1 - withdrawMoney);
					// commit the updated results
					resultSet.updateRow();
					System.out.println("Withdrawed Succesfully");
				}
			}
			if (flag == false) {
				System.out.println("please check your account number..");
			}

		} catch (SQLException e) {
			System.out.println("cant load db,..." + e.getMessage());
		}
	}

	@Override
	public boolean checkAccount(int accountNo) {
		boolean flag = false;
		String dispQuery3 = "select * from bankcustomers";
		try {
			preparedStatement = dbcon.prepareStatement(dispQuery3);
			resultSet = preparedStatement.executeQuery();
			flag = false;
			while (resultSet.next()) {
				if (resultSet.getInt("accountNo") == accountNo) {
					flag = true;
					return true;

				}
			}
			if (flag == false) {
				System.out.println("please check your account number..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void fundTransfer(int accountNo, int anotherAccountNo, int amountTotransfer) {

		String dispQuery4 = "select * from bankcustomers";
		try {
			preparedStatement = dbcon.prepareStatement(dispQuery4, resultSet.TYPE_SCROLL_SENSITIVE,
					resultSet.CONCUR_UPDATABLE);
			// execute query
			resultSet = preparedStatement.executeQuery();
			boolean flag = false;
			// traverse
			while (resultSet.next()) {
				if (resultSet.getInt("accountNo") == accountNo) {
					flag = true;
					resultSet.updateInt(3, resultSet.getInt("amount") - amountTotransfer);
					// commit the updated results
					resultSet.updateRow();
					System.out.println("Deducted amount from your account:" + amountTotransfer
							+ "\n availble balance is: " + resultSet.getInt("amount"));
				}
				if (resultSet.getInt("accountNo") == anotherAccountNo) {
					flag = true;
					resultSet.updateInt(3, resultSet.getInt("amount") + amountTotransfer);
					// commit the updated results
					resultSet.updateRow();
					System.out.println("credited to another account:" + amountTotransfer
							+ "\n availble balance is :" + resultSet.getInt("amount"));
				}
			}
			if (flag == false) {
				System.out.println("issues in fund transfering..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeIntoTransactionTable(TransactionDetails transaction) {

		String theQuerytoExecute1 = "insert into transactions(transAccount,fromAccount,status,amount) values(?,?,?,?)";
		try {
			preparedStatement = dbcon.prepareStatement(theQuerytoExecute1, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, transaction.getTransAccount());
			preparedStatement.setInt(4, transaction.getAmmount());
			preparedStatement.setString(3, transaction.getStatus());
			preparedStatement.setInt(2, transaction.getToAccount());
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("inserted into transaction table succesfully");
			} else {
				System.out.println("issues.. in storeIntoTransactionTable of dao class..");
			}

		} catch (SQLException e) {
			System.out.println("cant find db,...." + e.getMessage());
		}
	}

	@Override
	public void displayTransactionTable(int accNo) {

		String dispQuery5 = "select * from transactions";
		try {
			preparedStatement = dbcon.prepareStatement(dispQuery5);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("transAccount") == accNo) {
					System.out.println(" Current Account: " + resultSet.getInt("transAccount") + " Transfer Account: "
							+ resultSet.getInt("fromAccount") + " Status : " + resultSet.getString("status")
							+ " amount :" + resultSet.getInt("amount")+" "+resultSet.getDate("date")+" " +resultSet.getTime("time"));
				}
			}
		} catch (SQLException e) {
			System.out.println("cant load db from displayTransaction table.." + e.getMessage());
		}

	}

}
