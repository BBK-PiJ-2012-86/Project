package pcp_non_interactive;

import static org.junit.Assert.*;

import java.util.BitSet;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class Verifier2Test {
	private Verifier2 ver;
	private BitSet assEnc;

	@Before
	public void setUp() throws Exception {
		ver = new Verifier2(new Random(123456));
		assEnc = new BitSet();
		assEnc.set(2);
		assEnc.set(3);
	}

	@Test
	public void testVerify() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestAss() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestCross() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestLinearity() {
		assertTrue(ver.testLinearity(assEnc, 4));
	}

}
