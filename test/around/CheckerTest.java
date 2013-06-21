package around;

import static org.junit.Assert.*;

import org.junit.Test;

import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class CheckerTest {

	@Test
	public void testSatisfiesEqnTrue() {
		Eqn eqn = new Eqn(2);
		eqn.setCoeff(1, 2, true);
		eqn.setCoeff(2, 2, true);
		
		Assignment ass = new Assignment(2);
		ass.setAssSet(1,2);
		
		assertTrue(Checker.satisfies(ass, eqn));
	}
	
	@Test
	public void testSatisfiesEqnFalse() {
		Eqn eqn = new Eqn(2);
		eqn.setCoeff(1, 2, true);
		eqn.setCoeff(2, 2, true);
		
		Assignment ass = new Assignment(2);
		ass.setAssSet(2);
		
		assertFalse(Checker.satisfies(ass, eqn));
	}
	
	@Test
	public void testSatisfiedSystemTrue() {
		SysEqn eqns = new SysEqn(2);
		
		Eqn eqn1 = new Eqn(2);
		eqn1.setCoeff(1, 2, true);
		eqn1.setCoeff(2, 2, true);
		
		
		Eqn eqn2 = new Eqn(2);
		eqn2.setCoeff(1, 1, true);
		eqn2.setRhs(true);

		eqns.addEqn(eqn1);
		eqns.addEqn(eqn2);
		
		Assignment ass = new Assignment(2);
		ass.setAssSet(1,2);
		
		assertTrue(Checker.satisfies(ass, eqns));
	}
	
	@Test
	public void testSatisfiedSystemFalse() {
		SysEqn eqns = new SysEqn(2);
		
		Eqn eqn1 = new Eqn(2);
		eqn1.setCoeff(1, 2, true);
		eqn1.setCoeff(2, 2, true);
		
		
		Eqn eqn2 = new Eqn(2);
		eqn2.setCoeff(1, 1, true);
		eqn2.setRhs(true);

		eqns.addEqn(eqn1);
		eqns.addEqn(eqn2);
		
		Assignment ass = new Assignment(2);
		ass.setAssSet();
		
		assertFalse(Checker.satisfies(ass, eqns));
	}

}
