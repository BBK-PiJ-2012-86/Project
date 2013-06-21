package prob;

import static org.junit.Assert.*;

import org.junit.Test;

import prob.Eqn;
import prob.SystemEqn;

public class SystemEqnTest {

	@Test
	public void test() {
		SystemEqn equations = new SystemEqn(3);
		
		Eqn eqn1 = new Eqn(3);
		eqn1.setCoeff(2, 3, true);
		eqn1.setCoeff(2, 2, true);
		equations.addEqn(eqn1);
		
		Eqn eqn2 = new Eqn(3);
		eqn2.setCoeff(1, 3, true);
		eqn2.setRhs(true);
		equations.addEqn(eqn2);
		
		String result = equations.toString();

		assertTrue(result.contains("Eqn: x2*x2 + x2*x3 = 0"));
		assertTrue(result.contains("Eqn: x1*x3 = 1"));
		assertTrue(result.startsWith("-----"));
		assertTrue(result.endsWith("-----"));
		assertEquals(52,result.length());
	}

}
