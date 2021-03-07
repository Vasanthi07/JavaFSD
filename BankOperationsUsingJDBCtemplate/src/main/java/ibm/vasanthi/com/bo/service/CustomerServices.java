package ibm.vasanthi.com.bo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;
import ibm.vasanthi.com.bo.dao.CustomerDaoClass;

@Component("theService")
public class CustomerServices implements CustomerServiceInterface {

	@Autowired
	CustomerDaoClass dao;
	@Override
	public boolean checkPhoneNo(String phoneNo) {
		boolean isValid=dao.checkPhoneNo(phoneNo);
		if(isValid) {
			return true;
		}
		return false;
	}
	@Override
	public void storeIntoTable(CustomerDetails theCustomer) {
		dao.storeIntoTable(theCustomer);
		
	}
	@Override
	public List<CustomerDetails> displayTable() {
		return dao.displayTable();
		
	}
	@Override
	public int checkBalance(int accountNo) {
		
		return dao.checkBalance(accountNo);

	}
	@Override
	public void updateAmmount(int amount1, int depositMoney, int accountNo) {
		dao.updateAmmount(amount1,depositMoney,accountNo);
		
	}
	@Override
	public void withdrawAmmount(int amount1, int withdrawMoney, int accountNo) {

		dao.withdrawAmmount(amount1,withdrawMoney,accountNo);
		
	}
	@Override
	public boolean checkAccount(int accountNo) {
		return dao.checkAccount(accountNo);
	}
	
	@Override
	public void storeIntoTransactionTable(TransactionDetails transaction) {
		
		dao.storeIntoTransactionTable(transaction);
	}
	@Override
	public void displayTransactionTable(int accNo) {
		
		dao.displayTransactionTable(accNo);
		
	}
	@Override
	public void fundTransfer(int accountNo, int anotherAccountNo, int amountToTransfer, int amount1, int amount2) {
		
		dao.fundTransfer(accountNo,anotherAccountNo,amountToTransfer,amount1,amount2);
		
	}

}
