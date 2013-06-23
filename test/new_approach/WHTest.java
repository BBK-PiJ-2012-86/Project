package new_approach;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

import pcp.Prover;
import pcp.Ut;
import pcp.Verifier;
import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class WHTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEncodeBit() {
		BitSet toEncode = Ut.make(0, 2); //In: 101; Expected encoding: 01011010
		
		assertFalse(WH.encodeBit(toEncode, Ut.make())); //0th bit 0
		assertTrue(WH.encodeBit(toEncode, Ut.make(0))); //1st bit 1
		assertFalse(WH.encodeBit(toEncode, Ut.make(1))); //2st bit 01
		assertTrue(WH.encodeBit(toEncode, Ut.make(0, 1))); //3st bit 11
		assertTrue(WH.encodeBit(toEncode, Ut.make(2))); //4st bit 001
		assertFalse(WH.encodeBit(toEncode, Ut.make(0, 2))); //5st bit 101
		assertTrue(WH.encodeBit(toEncode, Ut.make(1, 2))); //6st bit 011
		assertFalse(WH.encodeBit(toEncode, Ut.make(0, 1, 2))); //7st bit
	}

	
	@Test
	public void testGetRand() {
		final int size = 128;
		for (int i = 0; i < 20; ++i) {
			BitSet random = WH.getRand(size);
			assertTrue(random.cardinality() <= size);
		}
	}
	
	@Test
	public void testVerifRequest() {
		SysEqn input = new SysEqn(3);
		
		BitSet[][] request = WH.verifRequest(input);
		
		assertEquals(2, request.length);
		assertEquals(306, request[0].length);
		assertEquals(305, request[1].length);
		
		//TODO: more tests
	}
	
	@Test
	public void testProverInfo() {
	
	}
	
	@Test
	public void testFullCycleCorrect() {
		//Correct assignment
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
		
		BitSet[][] req = WH.verifRequest(eqns);
		BitSet[] info = WH.proverInfo(ass, req);
		
		assertTrue(WH.verifIt(eqns, info));
	}
	
	@Test
	public void testFullCycleWrong() {
		//Correct assignment
		SysEqn eqns = new SysEqn(2);
		
		Eqn eqn1 = new Eqn(2);
		eqn1.setCoeff(1, 2, true);
		eqn1.setCoeff(2, 2, true);
		
		
		Eqn eqn2 = new Eqn(2);
		eqn2.setCoeff(1, 1, true);
		eqn2.setRhs(true);

		eqns.addEqn(eqn1);
		eqns.addEqn(eqn2);
		
		Assignment diffAss = new Assignment(2);
		diffAss.setAssSet(2);
		
		BitSet[][] req = WH.verifRequest(eqns);
		BitSet[] info = WH.proverInfo(diffAss, req);
		
		int count = 0;
		for (int i = 0; i<5; i++ ) {
			boolean res = WH.verifIt(eqns, info);
			if(res) {
				count++;
			} else {
				assertTrue(!res);
			}
		}
		assertTrue(count<8);
	}
	
}
