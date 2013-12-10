package info.javierliarte.letsPlayTDD.finances;

import static org.junit.Assert.*;

import org.junit.Test;

public class _TaxRateTest {

	@Test
	public void test() {
		TaxRate taxRate = new TaxRate(0);
		assertEquals(0, taxRate.simpleTaxFor(1000));
		assertEquals(0, taxRate.compoundTaxFor(1000));
	}
	
	@Test
	public void simpleTaxJustAppliesTaxRateToAmmount() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(250, taxRate.simpleTaxFor(1000));
	}
	
	@Test
	public void compoundTaxIsTheAmountOfTaxThatIsIncurredIfYouAlsoPayTaxOnTheTax() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(333, taxRate.compoundTaxFor(1000));
	}
	
	@Test
	public void valueObject() {
		TaxRate rate1a = new TaxRate(33);
		TaxRate rate1b = new TaxRate(33);
		TaxRate rate2 = new TaxRate(40);

		assertEquals("33.0%", rate1a.toString());
		assertTrue("same values should be equal", rate1a.equals(rate1b));
		assertFalse("different values should be false", rate1a.equals(rate2));
		assertTrue("same values has same hash code", rate1a.hashCode() == rate1b.hashCode());
	}
	
}
