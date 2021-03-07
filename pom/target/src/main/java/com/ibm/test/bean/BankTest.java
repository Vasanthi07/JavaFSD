package com.ibm.test.bean;

import java.util.Scanner;

import com.ibm.eis.service.BankService;
@Component("bankClass")
public class BankTest {
	Scanner scan = new Scanner(System.in);
	@Autowired
	BankService service;
	@Autowired
	Bank bank;
	public BankTest() {
		
	}
	public void list() {
		BankService.connectToDb();
		while(true)
			System.out.println("Enter your choice");
		System.out.println("1.create account");
		System.out.println("2.Deposit");
		System.out.println("3.Withdrawl");
		System.out.println("4.Fund transfer");
		System.out.println("5. print transaction");
		System.out.println("6. Exit");
		int ch = scan.nextInt();
		scan.nextLine();
		ch(ch);
	}
	 }
void ch(int ch) {
	switch(ch) {
	case 1: createTheAccount();
	        break;
	case 2: deposittheAmount();
	        break;
	case 3: withdrawlTheAmount();
	        break;
	case 4: fundTransfer();
	         break;
	case 5: printTransactions();
	        break;
	case 6: System.exit(0);       
	}
}
	
	
	

private void printTransactions() {
	// TODO Auto-generated method stub
	
}
private void fundTransfer() {
	// TODO Auto-generated method stub
	
}
private void withdrawlTheAmount() {
	// TODO Auto-generated method stub
	
}
private void deposittheAmount() {
	// TODO Auto-generated method stub
	
}
private void createTheAccount() {
	// TODO Auto-generated method stub
	
}
public void list1() {
	// TODO Auto-generated method stub
	
}

 
