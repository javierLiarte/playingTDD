package info.javierliarte.letsPlayTDD.finances;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class _SavingsAccountYearTest {

	@Test
	public void startingBalanceMatchesConstructor() {
		assertEquals(10000, newAccount().startingBalance());
	}
	
	@Test
	public void startingPrincipalMatchesConstructor() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertEquals("Starting principal", 3000, year.startingPrincipal());
	}

	@Test
	public void interestRateMatchesConstructor() {
		assertEquals(10, newAccount().interestRate());
	}
	
	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertEquals (7000, year.startingCapitalGains());
	}

	@Test
	public void endingPrincipalConsidersWithdrawals() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(2000);
		assertEquals("Ending principal", 1000, year.endingPrincipal());
	}

	@Test
	public void endingPrincipalNeverGoesBelowZero() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(4000);
		assertEquals("ending principal", 0, year.endingPrincipal());
	}
	
	@Test
	public void interestEarnedIsStartingBalanceCombinedWithInterestRate() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertEquals(1000,year.interestEarned(25));
	}
	
	@Test
	public void withdrawingFundsDoNotEarnInterest() {
		SavingsAccountYear year = newAccount();
		year.withdraw(1000);
		assertEquals(900, year.interestEarned(25));
	}
	
	@Test
	public void totalWithdrawnIncludingCapitalGains() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 0, 10);
		year.withdraw(1000);
		assertEquals("capital gains tax", 333, year.capitalGainsTaxIncurred(25));
		assertEquals("total withdrawn", 1333, year.totalWithdrawn(25));
	}
	
	@Test
	public void capitalGainsTaxesDoNotEarnInterest() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 0, 10);
		year.withdraw(1000);
		assertEquals("capital gains withdrawn", 1000, year.capitalGainsWithdrawn());
		assertEquals("capital gains tax", 333, year.capitalGainsTaxIncurred(25));
//		assertEquals("total withdrawn", 1333, year.totalWithdrawnIncludingCapitalGains());
		assertEquals("interest earned", 866, year.interestEarned(25));
	}

	@Test
	@Ignore
	public void endingCapitalGainsIncludesInterestEarned() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		assertEquals(7000, year.startingCapitalGains());
		assertEquals(3000+1000, year.endingCapitalGains());
	}

	@Test
	public void endingBalanceAppliesInterestRate() {
		assertEquals(11000, newAccount().endingBalance(25));
	}

	@Test
	public void multipleWithdrawalsInAYearAreTotaled() {
		SavingsAccountYear year = newAccount();
		year.withdraw(1000);
		year.withdraw(2000);
		assertEquals(3000, year.totalWithdrawnExceptCapitalGainsTax());
	}

	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}
	
	@Test
	public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncurred(25));
	}
	
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance() {
		SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(666, year.capitalGainsTaxIncurred(25));
		assertEquals(10000 - 5000 - 666 + 433, year.endingBalance(25));
	}

	@Test
	public void nextYearsStartingBalanceEqualsThisYearsEndingBalance() {
		SavingsAccountYear thisYear = newAccount();
		assertEquals(thisYear.endingBalance(25), thisYear.nextYear(25)
				.startingBalance());
	}

	@Test
	public void nextYearsInterestRateEqualsThisYearsInterestRate() {
		SavingsAccountYear thisYear = newAccount();
		assertEquals(thisYear.interestRate(), thisYear.nextYear(25)
				.interestRate());
	}

	private SavingsAccountYear newAccount() {
		SavingsAccountYear account = new SavingsAccountYear(10000, 10000, 10);
		return account;
	}

}
