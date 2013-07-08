package structure;

import static org.junit.Assert.*;

import java.util.BitSet;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.BSM;
import utilities.Rand;

public class EqnTestDeterministic {

	@BeforeClass
	public static void setUp() throws Exception {
		Rand.setRandom(new Random(123456));
	}

	@Test
	public void testMake() {
		BitSet coeffs = BSM.make(1, 2, 3, 5, 6);
		Eqn result1 = new Eqn(3, coeffs, true);
		assertEquals(result1, Eqn.make(3));
	}

}
