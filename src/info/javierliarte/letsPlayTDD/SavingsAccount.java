package info.javierliarte.letsPlayTDD;

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

}
