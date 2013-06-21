package prob;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import prob.Eqn;

public class EqnTest {

	@Test
	public void testSet() {
		Eqn eqn = new Eqn(3);
		eqn.setCoeff(1, 1, true);
		String actual = eqn.toString();
		String expected = "Eqn: x1*x1 = 0";
		assertEquals(expected,actual);
		
		eqn.setCoeff(2, 3, true);
		actual = eqn.toString();
		expected = "Eqn: x1*x1 + x2*x3 = 0";
		assertEquals(expected,actual);
		
		eqn.setCoeff(2, 3, false);
		eqn.setCoeff(1, 3, true);
		eqn.setRhs(true);
		actual = eqn.toString();
		expected = "Eqn: x1*x1 + x1*x3 = 1";
		assertEquals(expected,actual);
	}
	
	@Test
	public void testEquals() {
		Eqn eqn1 = new Eqn(2);
		eqn1.setCoeff(1, 1, true);
		//System.out.println(eqn1);
		
		Eqn eqn2 = new Eqn(2);
		eqn2.setCoeff(1, 1, true);
		//System.out.println(eqn2);
		
		Eqn eqn3 = new Eqn(2);
		eqn3.setCoeff(1, 1, true);
		eqn3.setRhs(true);
		//System.out.println(eqn3);
		
		Eqn eqn4 = new Eqn(2);
		eqn4.setCoeff(1, 1, true);
		eqn4.setCoeff(1, 2, true);
		eqn4.setRhs(true);
		//System.out.println(eqn4);
		
		assertTrue(eqn1.equals(eqn2));
		assertFalse(eqn1.equals(eqn3));
		assertFalse(eqn3.equals(eqn4));
	}

}
