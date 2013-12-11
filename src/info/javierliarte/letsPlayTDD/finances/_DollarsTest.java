package info.javierliarte.letsPlayTDD.finances;

import static org.junit.Assert.*;

import org.junit.Test;

public class _DollarsTest {

	@Test
	public void addition() {
		assertEquals (new Dollars(40), new Dollars(10).add(new Dollars(30)));
	}
	
	@Test
	public void substraction() {
		assertEquals ("positive result", new Dollars(20), new Dollars(50).substract(new Dollars(30)));
		assertEquals ("negative result", new Dollars(-10), new Dollars(40).substract(new Dollars(50)));
	}
	
	@Test
	public void substractToZero() {
		assertEquals ("positive result", new Dollars(20), new Dollars(50).substractToZero(new Dollars(30)));
		assertEquals ("no negative result--return zero instead", new Dollars(0), new Dollars(40).substractToZero(new Dollars(50)));
	}
	
	@Test
	public void valueObject() {
		Dollars dollars1a = new Dollars(10);
		Dollars dollars1b = new Dollars(10);
		Dollars dollars2 = new Dollars(20);
		
		assertEquals ("$10", dollars1a.toString());
		assertTrue ("Dollars with same amount should be the same", dollars1a.equals(dollars1b));
		assertFalse ("Dollars with different amounts should not be equal", dollars1a.equals(dollars2));
		assertTrue ("equal dollars should have same hash code", dollars1a.hashCode() == dollars1b.hashCode());
	}

}
