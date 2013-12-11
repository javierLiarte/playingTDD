package info.javierliarte.letsPlayTDD.finances;

public class Dollars {

	private int amount;

	public Dollars(int amount) {
		this.amount = amount;
	}
	
	public Dollars add(Dollars dollars) {
		return new Dollars(this.amount + dollars.amount);
	}

	public Dollars substract(Dollars dollars) {
		return new Dollars(this.amount - dollars.amount);
	}
	
	public int amount() {
		return this.amount; // TODO: delete me
	}

	public Dollars substractToZero(Dollars dollars) {
		int result = this.amount - dollars.amount;
		return new Dollars(Math.max(0, result));
	}

	@Override
	public String toString() {
		return "$" + amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
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
		Dollars other = (Dollars) obj;
		if (amount != other.amount)
			return false;
		return true;
	}

}
