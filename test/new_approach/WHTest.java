package new_approach;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

import pcp.Ut;

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

	
	
}
