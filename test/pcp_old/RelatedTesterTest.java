package pcp_old;

import static org.junit.Assert.*;

import org.junit.Test;

import pcp_old.Proof;
import pcp_old.Prover;
import pcp_old.RelatedTester;
import prob.Assignment;

public class RelatedTesterTest {

	@Test
	public void testTestValidA() {
		Assignment ass = new Assignment(2);
		ass.setAssSet(1,2);
		Proof proof = Prover.constructProof(ass);
		assertTrue(RelatedTester.test(proof, 6));
	}
	
	@Test
	public void testTestValidB() {
		Assignment ass = new Assignment(5);
		ass.setAssSet(2,4,5);
		Proof proof = Prover.constructProof(ass);
		assertTrue(RelatedTester.test(proof, 3));
	}
	
	@Test
	public void testTestInvalid() {
		Assignment ass1 = new Assignment(2);
		ass1.setAssSet(1,2);
		Proof proof1 = Prover.constructProof(ass1);
				
		Assignment ass2 = new Assignment(2);
		ass2.setAssSet(1);
		Proof proof2 = Prover.constructProof(ass2);
				
		proof1.setCrossEnc(proof2.getCrossEnc());
		
		int count = 0;
		for (int i = 0; i<10; i++) {
			if(RelatedTester.test(proof1, 3)) {count++;};
		}
		assertTrue(count<8);	// could fail, v. small prob
	}

}
