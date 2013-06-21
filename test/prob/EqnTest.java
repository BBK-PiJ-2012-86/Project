package prob;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import prob.Eqn;

public class EqnTest {

	@Test
	public void test() {
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

}
