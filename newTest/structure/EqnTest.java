package structure;

import static org.junit.Assert.*;

import org.junit.Test;

import pcp_old.Ut;

public class EqnTest {

	@Test
	public void testToString() {

		Eqn eqn = new Eqn(2, Ut.make(1,3), true);
		assertEquals("x1x2 + x2x2 = 1", eqn.toString());
		
		eqn = new Eqn(3, Ut.make(1,3,5), false);
		assertEquals("x1x2 + x2x1 + x2x3 = 0", eqn.toString());
		
		eqn = new Eqn(3, Ut.make(0,3,5), false);
		assertEquals("x1x1 + x2x1 + x2x3 = 0", eqn.toString());
	
	}

	/*@Test
	public void testMake() {
		long time = System.currentTimeMillis();
		Eqn eqn;
		for(int i = 0; i<1000; i++) {
			eqn = Eqn.make(600);
		}
		System.out.println(System.currentTimeMillis()-time);

	}*/

}
