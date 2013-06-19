package walsh_hadamard;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

public class WalshHadamardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEncrypt5() {
		BitSet input = BitSet.valueOf(new byte[] {5});
		BitSet codeword = WalshHadamard.encode(input, 3);
		BitSet expected = BitSet.valueOf(new byte[] {90});
		assertEquals(expected, codeword);
	}
	
	@Test
	public void testEncrypt2() {
		BitSet input = BitSet.valueOf(new byte[] {2});
		BitSet codeword = WalshHadamard.encode(input, 2);
		BitSet expected = BitSet.valueOf(new byte[] {3});
		assertEquals(expected, codeword);
	}
	
	
	@Test
	public void testEncrypt100() {
		BitSet input = BitSet.valueOf(new byte[] {100});
		BitSet codeword = WalshHadamard.encode(input, 7);
		BitSet expected = BitSet.valueOf(new byte[] {15,15,15,15,
													-16,-16,-16,-16,
													-16,-16,-16,-16,
													15,15,15,15});
		assertEquals(expected, codeword);
	}

	@Test
	public void testRecoverBitAtPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecrypt() {
		fail("Not yet implemented");
	}

	@Test
	public void testBinlog() {
		fail("Not yet implemented");
	}

}
