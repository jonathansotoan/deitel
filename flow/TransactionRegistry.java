package deitel.flow;

public class TransactionRegistry {
	private int accountNumber;
	private double amount;
	
	public TransactionRegistry () {
	}
	
	public TransactionRegistry (int accountNumber, double amount) {
		setAccountNumber (accountNumber);
		setAmount (amount);
	}
	
	public final int getAccountNumber () {
		return accountNumber;
	}
	
	public final double getAmount () {
		return amount;
	}
	
	public final void setAccountNumber (int number) {
		accountNumber = number > 0 ? number : 0;
	}
	
	public final void setAmount (double newAmount) {
		amount = newAmount > 0 ? newAmount : 0;
	}
	
	public String toString () {
		return String.format ("Transaction registry with %s: %d and %s %.2f",
										"account number", getAccountNumber (),
										"amount", getAmount ());
	}
}
