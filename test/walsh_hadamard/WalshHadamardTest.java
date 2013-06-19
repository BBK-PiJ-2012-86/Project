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
	public void testEncrypt() {
		byte[] input = { 5 };
		BitSet codeword = WalshHadamard.encode(BitSet.valueOf(input), 3);
		byte[] smoo = {90};
		BitSet expected = BitSet.valueOf(smoo);
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
