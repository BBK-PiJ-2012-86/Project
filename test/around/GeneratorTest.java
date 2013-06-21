package around;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneratorTest {

	@Test (expected = IllegalArgumentException.class)
	public void testMakeSysEqnTooMany() {
		Generator.makeSysEqn(2, 17);
	}
	
	@Test
	public void testMakeSysEqn() {
		int actual = Generator.makeSysEqn(2, 16).getEqns().size();
		int expected = 16;
		assertEquals(expected,actual);
	}

}
