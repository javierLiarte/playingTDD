package info.javierliarte.letsPlayTDD.finances;

public class SavingsAccount {

	private int balance = 0;
	
	public void deposit(int amount) {
		balance += amount;
	}

	public int balance() {
		return balance;
	}

	public void whitdraw(int amount) {
		balance -= amount;
	}

	public SavingsAccount nextYear(int interestRating) {
		SavingsAccount result = new SavingsAccount();
		result.deposit(balance() * (100 + interestRating) /100);
		return result;
	}

}
