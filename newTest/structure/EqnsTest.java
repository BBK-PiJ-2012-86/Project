package structure;

import static org.junit.Assert.*;

import org.junit.Test;

import pcp_old.Ut;

public class EqnsTest {

	@Test
	public void test() {
		Eqns eqns = new Eqns(3);
		eqns.add(new Eqn(3, Ut.make(1,5), false));
		eqns.add(new Eqn(3, Ut.make(0,1,5), false));
		String result = eqns.toString();
		System.out.println(result);
		
		assertTrue(result.contains("x1x2 + x2x3 = 0"));
		assertTrue(result.contains("x1x1 + x1x2 + x2x3 = 0"));
		assertEquals(41,result.length());
	}



}
