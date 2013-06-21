package pcp;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Test;

import pcp.LinearityTester;
import pcp.WalshHadamard;

public class LinearityTesterTest {
	
	@Test
	public void testSingleTestOnValidA() {
		BitSet input = WalshHadamard.encode(Ut.make(0,2),3);
		for (int i = 0 ;i<10; i++) {
			assertTrue(LinearityTester.singleTest(input, 8));
		}
	}
	
	@Test
	public void testSingleTestOnValidB() {
		BitSet input = WalshHadamard.encode(Ut.make(1,2),4);
		for (int i = 0 ;i<10; i++) {
			assertTrue(LinearityTester.singleTest(input, 16));
		}
	}
	
	@Test
	public void testSingleTestOnInvalid() {
		BitSet input = Ut.make(0,2);
		for (int i = 0 ;i<10; i++) {
			assertFalse(LinearityTester.singleTest(input, 4));
		}
	}
	
	@Test
	public void testTestValid() {
		BitSet input = WalshHadamard.encode(Ut.make(1,2),4);
		assertTrue(LinearityTester.test(input, 16, 0.99));
	}
	
	@Test
	public void testTestInvalid() {
		BitSet input = Ut.make(0,2);
		assertFalse(LinearityTester.test(input, 4, 0.99));
	}

}
