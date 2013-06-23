package pcp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.BitSet;

import org.junit.Test;

public class WalshHadamardTest {

	@Test
	public void testEncodeBit() {
		BitSet input = Ut.make(0);	//i.e. 01
		BitSet position = Ut.make(1);	//i.e. 2
		boolean result = WalshHadamard.encodeBit(input, 2, position);
		//encoding 0101
		assertFalse( result);
	}

	@Test
	public void testEncode5() {
		BitSet input = Ut.make(0,2);
		BitSet codeword = WalshHadamard.encode(input, 3);
		BitSet expected = Ut.make(1,3,4,6);
		assertEquals(expected, codeword);
	}
	
	@Test
	public void testEncode2() {
		BitSet input = Ut.make(0);
		BitSet codeword = WalshHadamard.encode(input, 2);
		BitSet expected = Ut.make(2,3);
		assertEquals(expected, codeword);
	}
	
	
	@Test
	public void testEncode18() {
		BitSet input = Ut.make(0,3);
		BitSet codeword = WalshHadamard.encode(input, 5);
		BitSet expected = Ut.make(2,3,6,7,10,11,14,15,16,17,20,21,24,25,28,29);
		assertEquals(expected, codeword);
	}

	

	@Test
	public void testDecodeInverseEncode2() {
		BitSet input = Ut.make(0);
		BitSet actual = WalshHadamard.decode(WalshHadamard.encode(input, 2), 4);
		assertEquals(input, actual);
	}
	
	@Test
	public void testDecodeInverseEncode18() {
		BitSet input = Ut.make(0,3);
		BitSet actual = WalshHadamard.decode(WalshHadamard.encode(input, 5), (int)Math.pow(2, 5));
		assertEquals(input, actual);
	}
	
	@Test
	public void testRecoverBitAtPosition7() {	// also need to test on corrupted input...
		BitSet encoding = WalshHadamard.encode(Ut.make(0,1,2),3);
		BitSet results = new BitSet(8);
		for (int i = 0; i<8; i++) {
			if (WalshHadamard.recoverBitAtPosition(encoding, 3, i)) {
				results.set(i);
			}
		}
		assertEquals(encoding, results);
		
	}
	
	@Test
	public void testRecoverBitAtPosition2() {
		BitSet encoding = WalshHadamard.encode(Ut.make(0),2);
		BitSet results = new BitSet(4);
		for (int i = 0; i<4; i++) {
			if (WalshHadamard.recoverBitAtPosition(encoding, 2, i)) {
				results.set(i);
			}
		}
		assertEquals(encoding, results);
		
	}
	
	@Test
	public void testBinlog() {
		int actual = WalshHadamard.binlog(8);
		int expected = 3;
		assertEquals(expected, actual);
		
		actual = WalshHadamard.binlog(1024);
		expected = 10;
		assertEquals(expected, actual);
		
		actual = WalshHadamard.binlog(1);
		expected = 0;
		assertEquals(expected, actual);
	}

}
