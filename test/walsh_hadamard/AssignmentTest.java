package walsh_hadamard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AssignmentTest {

	@Test
	public void test() {
		Assignment assignment = new Assignment(3);
		assignment.setAss(2,3);
		String actual = assignment.toString();
		String expected = "Ass: x1 = 0, x2 = 1, x3 = 1";
		assertEquals(expected, actual);
	}

}
