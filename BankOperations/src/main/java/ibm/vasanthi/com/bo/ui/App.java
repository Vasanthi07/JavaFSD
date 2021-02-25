package ibm.vasanthi.com.bo.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ibm.vasanthi.com.bo.bean.CustomerDetails;
import ibm.vasanthi.com.bo.bean.TransactionDetails;
import ibm.vasanthi.com.bo.service.CustomerServiceInterface;
import ibm.vasanthi.com.bo.service.CustomerServices;

public class App {
	public static void main(String[] args) {
		// load the context
		ClassPathXmlApplicationContext theContext = new ClassPathXmlApplicationContext("appConfig.xml");
		CustomerDetails theCustomer = theContext.getBean("theCustomer", CustomerDetails.class);
		CustomerServiceInterface service = theContext.getBean("theService", CustomerServices.class);
		TransactionDetails transaction = theContext.getBean("theTransaction", TransactionDetails.class);
		// create a scanner ref
		Scanner sc = new Scanner(System.in);
		StringBuilder thePrintStatement = new StringBuilder("");
		String phoneNo;
		String name;
		int accountNo;
		int anotherAccountNo;
		int c;
		int amount;
		int amount1;
		int amountToTransfer;
		int depositMoney;
		int withdrawMoney;
		int transAccount;
		int toAccount;
		int ammount;
		String status;
		// use infinite for loop till the user wants to exit from bank
		for (;;) {
			System.out.println(
					"Enter number to do following operations:\n 1:Create Account \n2.Check for balance \n3.Deposit \n4.WithDraw \n5.Fund Transfer \n6.print statement.");
			int num = sc.nextInt();
			if (num == 1) {
				service.displayTable();
				System.out.println("To create an account please ,enter the following details: ");
				System.out.println("Enter Phone Number:");
				sc.nextLine();
				phoneNo = sc.nextLine();
				boolean isValid = service.checkPhoneNo(phoneNo);
				if (isValid) {
					System.out.println("Account already exists with this number.Please, enter another phoneNo.");
				} else {
					theCustomer.setPhoneNo(phoneNo);
					System.out.println("Enter your name:");
					name = sc.nextLine();
					theCustomer.setCustomerName(name);
					System.out.println("To create an account you have to deposit some money with min of 1000..");
					System.out.println("Enter 1.to continue process for deposit 2.to abort ");
					c = sc.nextInt();
					if (c == 1) {
						System.out.println("Enter money you want to deposit:");
						amount = sc.nextInt();
						theCustomer.setAmount(amount);
					} else {
						System.out.println("without depositing we cant create an account");
						break;
					}
					service.storeIntoTable(theCustomer);
					System.out.println("You have successfully created an account and account number is : "
							+ theCustomer.getAccountNo());

				}

			} else if (num == 2) {
				System.out.println("Check Balance: ");
				System.out.println("Enter Your Account Number: ");
				accountNo = sc.nextInt();
				amount1 = service.checkBalance(accountNo);
				if (amount1 > 1000) {
					System.out.println("Available Balance is " + amount1);
				} else {
					System.out.println("LOW BALANCE ...." + amount1);
				}

			} else if (num == 3) {
				System.out.println("For Deposit Follow the steps below:");
				System.out.println("Check Balance: ");
				System.out.println("Enter Your Account Number: ");
				accountNo = sc.nextInt();
				amount1 = service.checkBalance(accountNo);
				if (amount1 > 0 && service.checkAccount(accountNo)) {
					if (amount1 > 1000) {
						System.out.println("Available Balance is " + amount1);
					} else {
						System.out.println("LOW BALANCE ...." + amount1);
					}
					System.out.println("Enter the money you want to deposit:");
					depositMoney = sc.nextInt();
					service.updateAmmount(amount1, depositMoney, accountNo);
					int availableBalance = service.checkBalance(accountNo);
					System.out.println("Available balance :" + availableBalance);
					transaction.setTransAccount(accountNo);
					transaction.setToAccount(accountNo);
					transaction.setStatus("credited");
					transaction.setAmmount(depositMoney);
					service.storeIntoTransactionTable(transaction);
					String stmt = "\n"+amount1+"deposited money into"+accountNo;
					thePrintStatement.append(stmt);
				}
			} else if (num == 4) {
				System.out.println("For withdraw Follow the steps below:");
				System.out.println("Check Balance: ");
				System.out.println("Enter Your Account Number: ");
				accountNo = sc.nextInt();
				amount1 = service.checkBalance(accountNo);
				if (amount1 > 0) {
					System.out.println("Enter the money you want to withdraw:");
					withdrawMoney = sc.nextInt();
					if (amount1 > withdrawMoney) {
						service.withdrawAmmount(amount1, withdrawMoney, accountNo);
						int availableBalance = service.checkBalance(accountNo);
						System.out.println("Available balance :" + availableBalance);
						transaction.setTransAccount(accountNo);
						transaction.setToAccount(accountNo);
						transaction.setStatus("debited");
						transaction.setAmmount(withdrawMoney);
						service.storeIntoTransactionTable(transaction);
						String stmt = "\nfrom "+accountNo+" withdrawed"+withdrawMoney+" and available balance"+availableBalance ;
						thePrintStatement.append(stmt);
					} else {
						System.out.println("LOW BALANCE ...." + amount1);
					}
				}

			} else if (num == 5) {
				System.out.println("For Fund transfer:");
				System.out.println("Enter your account number:");
				accountNo = sc.nextInt();
				amount1 = service.checkBalance(accountNo);
				System.out.println(amount1);
				boolean isValid = service.checkAccount(accountNo);
				System.out.println(isValid);
				if (isValid && amount1 > 0) {
					System.out.println("Enter account number to which you want to fund transfer:");
					anotherAccountNo = sc.nextInt();
					if (service.checkAccount(anotherAccountNo)) {
						System.out.println("Enter the ammount you want to transfer:");
						amountToTransfer = sc.nextInt();
						if (amount1 > amountToTransfer) {
							service.fundTransfer(accountNo, anotherAccountNo, amountToTransfer);
							transaction.setTransAccount(accountNo);
							transaction.setToAccount(anotherAccountNo);
							transaction.setStatus("debited");
							transaction.setAmmount(amountToTransfer);
							service.storeIntoTransactionTable(transaction);
							transaction.setTransAccount(anotherAccountNo);
							transaction.setToAccount(accountNo);
							transaction.setStatus("credited");
							transaction.setAmmount(amountToTransfer);
							service.storeIntoTransactionTable(transaction);
							String stmt = "\nfunds transfer from "+accountNo+" to "+anotherAccountNo+ "with an ammount of "+amountToTransfer;
							thePrintStatement.append(stmt);
						} else {
							System.out.println("Insufficient funds....");
						}
					}
				} else {
					System.out.println("please enter account number correctly...");
				}
			} else if (num == 6) {
				System.out.println("Enter account Number: ");
				int accNo = sc.nextInt();
				service.displayTransactionTable(accNo);
				System.out.println("print statement:"+thePrintStatement);
				

			} else {
				System.out.println("closing account...");
				System.exit(0);
			}

		}
	}
}
