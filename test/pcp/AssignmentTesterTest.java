package pcp;

import static org.junit.Assert.*;

import org.junit.Test;

import pcp.AssignmentTester;
import pcp.Proof;
import pcp.Prover;
import prob.Assignment;
import prob.Eqn;
import prob.SystemEqn;

public class AssignmentTesterTest {

	@Test
	public void testTestValidA() {
		
		SystemEqn eqns = new SystemEqn(2);
		
		Eqn eqn1 = new Eqn(2);
		eqn1.setCoeff(1, 2, true);
		eqn1.setCoeff(2, 2, true);
		
		
		Eqn eqn2 = new Eqn(2);
		eqn2.setCoeff(1, 1, true);
		eqn2.setRhs(true);

		eqns.addEqn(eqn1);
		eqns.addEqn(eqn2);
		
		Assignment ass = new Assignment(2);
		ass.setAss(1,2);
		Proof proof = Prover.constructProof(ass);

		assertTrue(AssignmentTester.test(eqns, proof.getCrossEnc(), 10));
	}
		
		
	@Test
	public void testTestB() {
		
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
		Proof proof = Prover.constructProof(ass);
		assertTrue(AssignmentTester.test(eqns, proof.getCrossEnc(), 1));
		
		ass.setAss();
		assertFalse(AssignmentTester.test(eqns, ass.getAss(), 10));	//unlikely to fail..
		
		ass.setAss(2,3);
		assertFalse(AssignmentTester.test(eqns, ass.getAss(), 10));	//unlikely to fail..
		
	}

}
