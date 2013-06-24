package structure;

import static org.junit.Assert.*;

import org.junit.Test;

import pcp_old.Ut;

public class AssignmentTest {

	@Test
	public void testToString() {
		Assignment ass = new Assignment(4, Ut.make(2,3));
		assertEquals("x1 = 0, x2 = 0, x3 = 1, x4 = 1", ass.toString() );
		
	}

}
