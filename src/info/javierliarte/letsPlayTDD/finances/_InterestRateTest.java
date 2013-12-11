package info.javierliarte.letsPlayTDD.finances;

import static org.junit.Assert.*;

import org.junit.Test;

public class _InterestRateTest {

	@Test
	public void interestRate() {
		InterestRate interestRate = new InterestRate(0);
		assertEquals (new Dollars(0), interestRate.interestOn(new Dollars(1000)));
	}
	
	@Test
	public void interest() {
		InterestRate interestRate = new InterestRate(10);
		assertEquals (new Dollars(100), interestRate.interestOn(new Dollars(1000)));
	}
	
	@Test
	public void valueObject() {
		InterestRate rate1a = new InterestRate(33);
		InterestRate rate1b = new InterestRate(33);
		InterestRate rate2 = new InterestRate(40);
		
		assertEquals ("33.0%", rate1a.toString());
		assertTrue ("Same rates are the same", rate1a.equals(rate1b));
		assertFalse ("Diferent rates are not equal", rate1a.equals(rate2));
		assertTrue ("Same rates has same hash code", rate1a.hashCode() == rate1b.hashCode());
	}

}
