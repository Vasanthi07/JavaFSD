package ibm.vasanthi.com.bo.dao;

import java.util.List;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;

public interface CustomerDaoInterface {
	boolean checkPhoneNo(String phoneNo);
	void storeIntoTable(CustomerDetails theCustomer);
	List<CustomerDetails> displayTable();
	int checkBalance(int accountNo);
	void withdrawAmmount(int amount1, int withdrawMoney, int accountNo); 
	void updateAmmount(int amount1, int depositMoney, int accountNo);
	boolean checkAccount(int accountNo);
	void fundTransfer(int accountNo, int anotherAccountNo,int amountToTransfer,int amount1,int amount2);
	void storeIntoTransactionTable(TransactionDetails transaction);
	void displayTransactionTable(int accNo);
}
