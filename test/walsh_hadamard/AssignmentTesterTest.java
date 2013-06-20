package walsh_hadamard;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssignmentTesterTest {

	@Test
	public void testTestValid() {
		SystemEqn eqns = new SystemEqn(3);
		
		Eqn eqn1 = new Eqn(3);
		eqn1.setCoeff(1, 2, true);
		eqn1.setCoeff(2, 3, true);
		eqn1.setRhs(true);
		
		Eqn eqn2 = new Eqn(3);
		eqn1.setCoeff(1, 1, true);
		eqn1.setCoeff(2, 2, true);
		
		Eqn eqn3 = new Eqn(3);
		eqn1.setCoeff(1, 3, true);

		eqns.addEqn(eqn1);
		eqns.addEqn(eqn2);
		eqns.addEqn(eqn3);
		
		Assignment ass = new Assignment(3);
		ass.setAss(1,2);
		assertTrue(AssignmentTester.test(eqns, ass.getAss(), 10));
		
		ass.setAss();
		assertFalse(AssignmentTester.test(eqns, ass.getAss(), 10));	//unlikely to fail..
		
		ass.setAss(2,3);
		assertFalse(AssignmentTester.test(eqns, ass.getAss(), 10));	//unlikely to fail..

	}

}
