package info.javierliarte.letsPlayTDD.finances;

public class InterestRate {

	private double interestRate;

	public InterestRate(int interestRateAsPercentage) {
		this.interestRate = interestRateAsPercentage / 100.0;
	}

	public Dollars interestOn(Dollars dollars) {
		return new Dollars((int) (interestRate * dollars.toInt()));
	}
	
	@Override
	public String toString() {
		return (this.interestRate * 100) + "%";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(interestRate);
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
		InterestRate other = (InterestRate) obj;
		if (Double.doubleToLongBits(interestRate) != Double
				.doubleToLongBits(other.interestRate))
			return false;
		return true;
	}

}
