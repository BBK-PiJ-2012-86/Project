package pcp_non_interactive;

import static org.junit.Assert.*;

import java.util.BitSet;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import structure.Eqn;
import structure.Eqns;
import utilities.Manip;
import utilities.WH;

public class Verifier2Test {
	private Verifier2 ver;
	private BitSet linearEncoding;
	private BitSet nonLinearEncoding;
	private BitSet validCross;
	private BitSet invalidCross;
	private BitSet testAss;
	private Eqns testEqns;

	@Before
	public void setUp() throws Exception {
		ver = new Verifier2(new Random(123456));
		
		testAss = new BitSet();
		testAss.set(1);
		
		testEqns = new Eqns(2);
		BitSet coeffs1 = new BitSet();
		coeffs1.set(0);
		coeffs1.set(3);
		BitSet coeffs2 = new BitSet();
		coeffs2.set(0);
		coeffs2.set(1);
		testEqns.add(new Eqn(2, coeffs1, true));
		testEqns.add(new Eqn(2, coeffs2, false));
		
		
		//A linear encoding
		linearEncoding = new BitSet();
		linearEncoding.set(2);
		linearEncoding.set(3);
		
		//A non-linear encoding
		nonLinearEncoding = new BitSet();
		nonLinearEncoding.set(2);
		
		validCross = Manip.cross(testAss, testAss, 2);
		invalidCross = (BitSet) validCross.clone();
		//invalidCross.set(0);
		invalidCross.set(1);
	}

	@Test
	public void testVerify() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestAss() {
		assertTrue(ver.testAss(testEqns, 2, WH.encode(validCross, 4), 16));
		
		assertFalse(ver.testAss(testEqns, 2, WH.encode(invalidCross, 4), 16));
	}

	@Test
	public void testTestCross() {
		//Valid encoding
		assertTrue(ver.testCross(2, WH.encode(testAss, 2), WH.encode(validCross, 4), 4));
		
		//Invalid assignment
		assertFalse(ver.testCross(2, WH.encode(testAss, 2), WH.encode(invalidCross, 4), 4));
		
	}

	@Test
	public void testTestLinearity() {
		assertTrue(ver.testLinearity(linearEncoding, 4));
		
		assertFalse(ver.testLinearity(nonLinearEncoding, 4));
		
		try {
			ver.testLinearity(null, 1);
			fail("Should throw NPE");
		}
		catch(NullPointerException ex) {
			//Correct
		}
		
		try {
			ver.testLinearity(linearEncoding, 0);
			fail("Should throw");
		}
		catch(IllegalArgumentException ex) {
			//Correct
		}
		
	}

}
