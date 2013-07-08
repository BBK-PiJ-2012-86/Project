package utilities;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

/*
 * Deterministic testing of the WH encoding
 */
public class WHTest {

	private BitSet test1;
	private BitSet test2;
	private BitSet test3;

	@Before
	public void setUp() throws Exception {
		test1 = BSM.make(1);
		test2 = BSM.make();
		test3 = BSM.make(0,2,3);
	}

	@Test
	public void testEncode() {
		BitSet result1 = BSM.make(1,3);
		BitSet result2 = BSM.make();
		BitSet result3 = BSM.make(1, 2, 5, 6, 8, 11, 12, 15);
		assertEquals(result1, WH.encode(test1, 2));
		assertEquals(result2, WH.encode(test2, 3));
		assertEquals(result3, WH.encode(test3, 4));
		
		try {
			assertEquals(result2, WH.encode(test1, 0));
			fail("should throw here");
		}
		catch(IllegalArgumentException ex) {
			//correct
		}
		
	}

}
