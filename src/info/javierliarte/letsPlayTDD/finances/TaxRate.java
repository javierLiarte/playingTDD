package info.javierliarte.letsPlayTDD.finances;

public class TaxRate {

	private double rate;

	public TaxRate(double rateAsPercentage) {
		this.rate = rateAsPercentage / 100.0;
	}
	
	public Dollars simpleTaxFor(Dollars dollars) {
		return new Dollars((int)(rate * dollars.toInt()));
	}

	public Dollars compoundTaxFor(Dollars dollars) {
		int amountAsInt = dollars.toInt();
		return new Dollars((int)((amountAsInt / (1 - rate)) - amountAsInt));
	}
	
	public String toString() {
		return (rate * 100) + "%";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxRate other = (TaxRate) obj;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate))
			return false;
		return true;
	}

}
