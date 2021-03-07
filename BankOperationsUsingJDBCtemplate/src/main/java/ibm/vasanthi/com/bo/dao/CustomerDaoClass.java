package ibm.vasanthi.com.bo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;

@Component("theDao")
public class CustomerDaoClass implements CustomerDaoInterface {

	String theQuerytoExecute;
	ResultSet resultSet;
	Connection dbcon;
	PreparedStatement preparedStatement;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameter;

	@Autowired
	void setDataSource(DataSource theDataSource) {
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(theDataSource);
		namedParameter = new NamedParameterJdbcTemplate(theDataSource);
	}

	@Override
	public boolean checkPhoneNo(String phoneNo) {
		theQuerytoExecute = "select * from bankcustomers";
		return jdbcTemplate.queryForObject(theQuerytoExecute, (res, num) -> {
			while (res.next()) {
				if (phoneNo.equals(res.getString("phoneNo"))) {
					return true;
				}
			}
			return false;

		});
	}

	@Override
	public void storeIntoTable(CustomerDetails theCustomer) {
		// insert records into the database table employees based on the user queries
		KeyHolder primaryKey = new GeneratedKeyHolder();
		String queryToExecute = "insert into bankcustomers(cutsomerName,amount,phoneNo) values(:customerName,:amount,:phoneNo)";

		SqlParameterSource theSource = new MapSqlParameterSource()
				.addValue("customerName", theCustomer.getCustomerName()).addValue("amount", theCustomer.getAmount())
				.addValue("phoneNo", theCustomer.getPhoneNo());
		namedParameter.update(queryToExecute, theSource, primaryKey);
		theCustomer.setAccountNo(primaryKey.getKey().intValue());
	}

	@Override
	public List<CustomerDetails> displayTable() {
		// display the table employees
		String dispQuery = "select * from bankcustomers";
		return namedParameter.query(dispQuery, (res, num) -> {
			CustomerDetails theCustomer = new CustomerDetails();
			theCustomer.setAccountNo(res.getInt("accountNo"));
			theCustomer.setAmount(res.getInt("amount"));
			theCustomer.setCustomerName(res.getString("cutsomerName"));
			theCustomer.setPhoneNo(res.getString("phoneNo"));
			return theCustomer;
		});
	}

	@Override
	public int checkBalance(int accountNo) {
		theQuerytoExecute = "select * from bankcustomers";
		return jdbcTemplate.queryForObject(theQuerytoExecute, (res, num) -> {
			int amount = 0;
			boolean flag = false;
			while (res.next()) {
				if (res.getInt("accountNo") == accountNo) {
					flag = true;
					amount = res.getInt("amount");
				}
			}
			if (flag == false) {
				System.out.println("please check your account number..");
			}
			return amount;

		});
	}

	@Override
	public void updateAmmount(int amount1, int depositMoney, int accountNo) {
		int amount = amount1 + depositMoney;
		String dispQuery = "update bankcustomers set amount = :amount where accountNo = :accountNo";
		SqlParameterSource theSource = new MapSqlParameterSource().addValue("amount", amount).addValue("accountNo",
				accountNo);
		namedParameter.update(dispQuery, theSource);

	}

	public void withdrawAmmount(int amount1, int withdrawMoney, int accountNo) {
		int amount = amount1 - withdrawMoney;
		String dispQuery = "update bankcustomers set amount = :amount where accountNo = :accountNo";
		SqlParameterSource theSource = new MapSqlParameterSource().addValue("amount", amount).addValue("accountNo",
				accountNo);
		namedParameter.update(dispQuery, theSource);
	}

	@Override
	public boolean checkAccount(int accountNo) {

		String disQuery = "select * from bankcustomers";
		return jdbcTemplate.queryForObject(disQuery, (res, num) -> {
			boolean flag = false;
			while (res.next()) {
				if (res.getInt("accountNo") == accountNo) {
					flag = true;
					return true;
				}
			}
			if (flag == false) {
				System.out.println("please check your account number..");
			}
			return flag;

		});

	}
	@Override
	public void fundTransfer(int accountNo, int anotherAccountNo, int amountToTransfer, int amount1, int amount2) {
		
		int amountDebited = amount1-amountToTransfer;
		int amountCredited = amount2-amountToTransfer;
		String dispQuery4 = "update bankcustomers set amount = :amount where accountNo = :accountNo";
		SqlParameterSource theSource = new MapSqlParameterSource().addValue("amount", amountDebited).addValue("accountNo",
				accountNo);
		namedParameter.update(dispQuery4, theSource);		
		System.out.println("Deducted amount from your account:" + amountToTransfer
							+ "\n availble balance is: " + amountDebited);
		String dispQuery5 = "update bankcustomers set amount = :amount where accountNo = :anotherAccountNo";
		SqlParameterSource theSource2 = new MapSqlParameterSource().addValue("amount", amountCredited).addValue("accountNo",
				anotherAccountNo);
		namedParameter.update(dispQuery5, theSource);		
		System.out.println("credited to another account:" + amountToTransfer + "\n availble balance is :"
							+ amountCredited);
				
		
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
			System.out.println("cant find db,....in store into transaction table" + e.getMessage());
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
							+ " amount :" + resultSet.getInt("amount") + " " + resultSet.getDate("date") + " "
							+ resultSet.getTime("time"));
				}
			}
		} catch (SQLException e) {
			System.out.println("cant load db from displayTransaction table.." + e.getMessage());
		} finally {
			try {
				dbcon.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
}
