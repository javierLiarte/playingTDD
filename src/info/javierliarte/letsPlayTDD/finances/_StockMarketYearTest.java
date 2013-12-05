package info.javierliarte.letsPlayTDD.finances;

import static org.junit.Assert.*;

import org.junit.Test;

public class _StockMarketYearTest {

	@Test
	public void startingValues() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals("starting Balance", 10000, year.startingBalance());
		assertEquals("Starting principal", 3000, year.startingPrincipal());
		assertEquals(10, year.interestRate());
		assertEquals("total withdrawn default", 0, year.totalWithdrawn(25));
	}
	
	@Test
	public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals (7000, year.startingCapitalGains());
	}

	@Test
	public void endingPrincipalConsidersWithdrawals() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(2000);
		assertEquals("Ending principal", 1000, year.endingPrincipal());
	}

	@Test
	public void endingPrincipalNeverGoesBelowZero() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(4000);
		assertEquals("ending principal", 0, year.endingPrincipal());
	}
	
	@Test
	public void interestEarnedIsStartingBalanceCombinedWithInterestRate() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		assertEquals(1000,year.interestEarned(25));
	}
	
	@Test
	public void withdrawingFundsDoNotEarnInterest() {
		StockMarketYear year = newAccount();
		year.withdraw(1000);
		assertEquals(900, year.interestEarned(25));
	}
	
	@Test
	public void totalWithdrawnIncludingCapitalGains() {
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		year.withdraw(1000);
		assertEquals("capital gains tax", 333, year.capitalGainsTaxIncurred(25));
		assertEquals("total withdrawn", 1333, year.totalWithdrawn(25));
	}
	
	@Test
	public void capitalGainsTaxesDoNotEarnInterest() {
		StockMarketYear year = new StockMarketYear(10000, 0, 10);
		year.withdraw(1000);
		assertEquals("capital gains withdrawn", 1000, year.capitalGainsWithdrawn());
		assertEquals("capital gains tax", 333, year.capitalGainsTaxIncurred(25));
		assertEquals("total withdrawn", 1333, year.totalWithdrawn(25));
		assertEquals("interest earned", 866, year.interestEarned(25));
	}

	@Test
	public void endingBalanceAppliesInterestRate() {
		assertEquals(11000, newAccount().endingBalance(25));
	}

	@Test
	public void multipleWithdrawalsInAYearAreTotaled() {
		StockMarketYear year = newAccount();
		year.withdraw(1000);
		year.withdraw(2000);
		assertEquals(3000, year.totalWithdrawn(25));
	}

	@Test
	public void withdrawingMoreThanPrincipalTakesFromCapitalGains() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(1000);
		assertEquals(0, year.capitalGainsWithdrawn());
		year.withdraw(3000);
		assertEquals(1000, year.capitalGainsWithdrawn());
	}
	
	@Test
	public void capitalGainsTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(2000, year.capitalGainsWithdrawn());
		assertEquals(666, year.capitalGainsTaxIncurred(25));
	}
	
	@Test
	public void capitalGainsTaxIsIncludedInEndingBalance() {
		StockMarketYear year = new StockMarketYear(10000, 3000, 10);
		year.withdraw(5000);
		assertEquals(666, year.capitalGainsTaxIncurred(25));
		assertEquals(10000 - 5000 - 666 + 433, year.endingBalance(25));
	}

	@Test
	public void nextYear() {
		StockMarketYear thisYear = newAccount();
		StockMarketYear nextYear = thisYear.nextYear(25);
		assertEquals("starting balance ", thisYear.endingBalance(25), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingPrincipal(), nextYear.startingPrincipal());
		assertEquals("interest", thisYear.interestRate(), nextYear.interestRate());
	}

	private StockMarketYear newAccount() {
		StockMarketYear account = new StockMarketYear(10000, 10000, 10);
		return account;
	}

}
