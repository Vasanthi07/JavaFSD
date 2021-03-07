package com.ibm.eis.service;

import com.ibm.test.dao.BankDao;
import com.ibm.test.dao.BankInterface;

public class BankService implements BankInterface{
	static BankDao dao;
	public static void connectToDb() {
		dao.connectToDb();
	}
	
	
	@Override
	public boolean createAccount(Bank bank) {
		return dao.createAccount(bank);
	}
	@Override
	public boolean lowBalance(Bank bank) {
		return dao.lowBalance(bank);
	}
	@Override
	public boolean deposit(Bank bank) {
		return dao.deposit(bank);
	}
	@Override
	public boolean withdrawl(Bank bank) {
		return dao.withdrawl(bank);
	}
	@Override
	@Override
	public boolean fundTransfer(Bank bank) {
		return dao.fundTransfer(bank);
	}
	
	
	public boolean printTransactions(Bank bank) {
		return dao.printTransactions(bank);
	}

}
