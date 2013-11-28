package info.javierliarte.letsPlayTDD.finances;

public class SavingsAccountYear {

	private int startingBalance = 0;
	private int interestRate = 0;
	
	public SavingsAccountYear(int startingBalance, int interestRate) {
		this.startingBalance = startingBalance;
		this.interestRate = interestRate;
	}
	
	public int startingBalance() {
		return startingBalance;
	}

	public SavingsAccountYear() {
		// TODO get rid of this constructor
	}

	public void deposit(int amount) {
		startingBalance += amount;
	}

	public int balance() {
		return startingBalance;
	}

	public SavingsAccountYear nextYear() {
		return new SavingsAccountYear(this.endingBalance(),this.interestRate);
	}

	public int endingBalance() {
		return balance() * (100 + interestRate) /100;
	}

	public int interestRate() {
		return interestRate;
	}

}
