package ibm.vasanthi.com.bo.bean;


import org.springframework.stereotype.Component;

@Component("theTransaction")
public class TransactionDetails {

	private int transAccount;
	private int toAccount;
	private String status;
	private int ammount;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTransAccount() {
		return transAccount;
	}
	public void setTransAccount(int transAccount) {
		this.transAccount = transAccount;
	}
	public int getToAccount() {
		return toAccount;
	}
	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}
	public int getAmmount() {
		return ammount;
	}
	@Override
	public String toString() {
		return "TransactionDetails [transAccount=" + transAccount + ", toAccount=" + toAccount + ", status=" + status
				+ ", ammount=" + ammount + "]";
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
}
