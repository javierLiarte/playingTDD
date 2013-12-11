package info.javierliarte.letsPlayTDD.finances;

public class StockMarketYear {

	private Dollars startingBalance;
	private Dollars startingPrincipal;
	private InterestRate interestRate;
	private TaxRate capitalGainsTaxRate;
	private Dollars totalWithdrawals;
	
	public StockMarketYear(Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTax) {
		this.startingBalance = startingBalance;
		this.startingPrincipal = startingPrincipal;
		this.interestRate = interestRate;
		this.capitalGainsTaxRate = capitalGainsTax;
		this.totalWithdrawals = new Dollars(0);
	}
	
	public Dollars startingBalance() {
		return startingBalance;
	}

	public Dollars startingPrincipal() {
		return startingPrincipal;
	}

	public InterestRate interestRate() {
		return interestRate;
	}

	public TaxRate capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}

	public void withdraw(Dollars amount) {
		// TODO: convert amount to Dollars
		this.totalWithdrawals = totalWithdrawals.add(amount);
	}

	private Dollars capitalGainsWithdrawn() {
		return totalWithdrawals.substractToZero(startingPrincipal);
	}

	public int capitalGainsTaxIncurred() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn().amount());
	}

	public Dollars totalWithdrawn() {
		return totalWithdrawals.add(new Dollars(capitalGainsTaxIncurred()));
	}

	public int interestEarned() {
		return interestRate.interestOn(startingBalance.amount() - totalWithdrawn().amount());
	}
	
	public Dollars endingBalance() {
		return startingBalance.substract(totalWithdrawn()).add(new Dollars(interestEarned()));
	}

	public int endingPrincipal() {
		return startingPrincipal.substractToZero(totalWithdrawals).amount();
	}

	public StockMarketYear nextYear() {
		return new StockMarketYear(this.endingBalance(), new Dollars(this.endingPrincipal()), this.interestRate(), this.capitalGainsTaxRate());
	}

}
