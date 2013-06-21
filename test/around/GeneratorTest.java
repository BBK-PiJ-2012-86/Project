package around;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneratorTest {

	@Test (expected = IllegalArgumentException.class)
	public void testMakeSysEqnTooMany() {
		Generator.makeSysEqn(2, 15);
	}
	
	@Test
	public void testMakeSysEqn() {
		int actual = Generator.makeSysEqn(2, 14).getEqns().size();
		int expected = 14;
		assertEquals(expected,actual);
	}

}
