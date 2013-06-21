package pcp;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

import pcp.Proof;
import pcp.Prover;
import pcp.VResult;
import pcp.Verifier;
import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class VerifierTest {
	private SysEqn eqns;
	private Proof proof;
	
	@Before
	public void setUp() {
		eqns = new SysEqn(2);
		
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
		proof = Prover.constructProof(ass);
		
	}

	@Test
	public void testVerifyValid() {
		VResult result = Verifier.verify(proof, eqns);
		assertTrue(result.pass);
		assertEquals("pass (prob>=0.5 [ish] all ok)",result.message);
	}
	
	@Test
	public void testVerifyFNotLinear() {
		BitSet fiddled = proof.getAssEnc();
		fiddled.set(3,true);
		proof.setAssEnc(fiddled);

		VResult result = Verifier.verify(proof, eqns);
		assertFalse(result.pass);
		assertEquals("f not linear", result.message);
	}
	
	@Test
	public void testVerifyGNotLinear() {
		BitSet fiddled = proof.getCrossEnc();
		fiddled.set(5,true);
		proof.setCrossEnc(fiddled);
		
		VResult result = Verifier.verify(proof, eqns);
		assertFalse(result.pass);
		assertEquals("g not linear", result.message);
	}
	
	@Test
	public void testVerifyRelWrong() {
		BitSet diff = Ut.make(2,3);
		proof.setAssEnc(diff);
		
		int count = 0;
		VResult result;
		for (int i = 0; i<15; i++ ) {
			result = Verifier.verify(proof, eqns);
			if(result.pass) {
				count++;
			} else {
				assertEquals("f, g not in correct relationship",result.message);
			}
		}
		assertTrue(count<13);		// v. unlikely to fail
	}
	
	@Test
	public void testVerifyAssWrong() {
		Assignment diffAss = new Assignment(2);
		diffAss.setAssSet(2);
		proof = Prover.constructProof(diffAss);
		
		int count = 0;
		VResult result;
		for (int i = 0; i<5; i++ ) {
			result = Verifier.verify(proof, eqns);
			if(result.pass) {
				count++;
			} else {
				assertEquals("assignment not satisfying", result.message);
			}
		}
		assertTrue(count<8);		// v. unlikely to fail
	}

}
