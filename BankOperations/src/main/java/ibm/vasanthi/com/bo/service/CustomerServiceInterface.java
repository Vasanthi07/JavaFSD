package ibm.vasanthi.com.bo.service;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;

public interface CustomerServiceInterface {
	boolean checkPhoneNo(String phoneNo);
	void storeIntoTable(CustomerDetails theCustomer);
	void displayTable();
	int checkBalance(int accountNo);
	void updateAmmount(int amount1, int depositMoney, int accountNo);
	void withdrawAmmount(int amount1, int withdrawMoney, int accountNo);
	boolean checkAccount(int accountNo);
	void fundTransfer(int accountNo, int anotherAccountNo, int amountToTransfer);
	void storeIntoTransactionTable(TransactionDetails transaction);
	void displayTransactionTable(int accNo);
}
