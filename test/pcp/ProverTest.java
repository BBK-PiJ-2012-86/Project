package pcp;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Test;

import pcp.Proof;
import pcp.Prover;
import pcp.WalshHadamard;
import prob.Assignment;

public class ProverTest {

	@Test
	public void testConstructProofA() {
		Assignment ass = new Assignment(2);
		ass.setAssSet(2);
		Proof proof = Prover.constructProof(ass);
		
		BitSet expectedAssEnc = Ut.make(1,3);
		BitSet expectedCrossEnc = Ut.make(1,3,5,7,9,11,13,15);

		assertEquals(expectedAssEnc,proof.getAssEnc());
		assertEquals(expectedCrossEnc,proof.getCrossEnc());
	}
	
	@Test
	public void testConstructProofB() {
		Assignment ass = new Assignment(3);
		ass.setAssSet(1,2);
		Proof proof = Prover.constructProof(ass);
		
		BitSet expectedAssEnc = Ut.make(2,3,4,5);
		BitSet expectedCrossEnc = WalshHadamard.encode(Ut.make(0,1,3,4),9);

		assertEquals(expectedAssEnc,proof.getAssEnc());
		assertEquals(expectedCrossEnc,proof.getCrossEnc());
	}

}
