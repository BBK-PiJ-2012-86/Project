package utilities;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

public class ManipTest {
	BitSet set1;
	BitSet set2;
	BitSet set3;

	@Before
	public void setUp() throws Exception {
		set1 = BSM.make(1);
		set2 = BSM.make(0,3);
		set3 = BSM.make(1,2,4);
	}

	@Test
	public void testCross() {
		BitSet oneCrossTwo = BSM.make(4, 7);
		assertEquals(oneCrossTwo, Manip.cross(set1, set2, 4));
		
		BitSet twoCrossTwo = BSM.make(0,3,12,15);
		assertEquals(twoCrossTwo, Manip.cross(set2, set2, 4));
		
		assertEquals(BSM.make(2), Manip.cross(set1, set2, 2));
		
		BitSet threeCrossThree = BSM.make(6, 7, 9, 11, 12, 14, 21, 22, 24);
		assertEquals(threeCrossThree, Manip.cross(set3, set3, 5));
	}

}
