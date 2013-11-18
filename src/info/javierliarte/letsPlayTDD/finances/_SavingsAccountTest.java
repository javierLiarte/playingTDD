package info.javierliarte.letsPlayTDD.finances;

import static org.junit.Assert.*;

import org.junit.Test;

public class _SavingsAccountTest {

	@Test
	public void depositAndWithdrawal() {
		SavingsAccount account = new SavingsAccount();
		account.deposit(100);
		assertEquals("After deposit", 100, account.balance());
		account.whitdraw(50);
		assertEquals("After withdrawal", 50, account.balance());
	}
	
	@Test
	public void negativeBalanceShouldBeOk() {
		SavingsAccount account = new SavingsAccount();
		account.whitdraw(75);
		assertEquals("After withdrawal in a new account", -75, account.balance());
	}
	
	@Test
	public void nextYear() {
		SavingsAccount account = new SavingsAccount();
		account.deposit(10000);
		SavingsAccount nextYear = account.nextYear(10);
		assertEquals("After a year with a 10% interest Rate", 11000, nextYear.balance());
	}

}
