package pcp_old;

import java.util.BitSet;
import java.util.Random;

public class LinearityTester {	// do single test ~100 times to get 0.99 close to a linear
	
	private static Random rand = new Random();


	public static boolean singleTest(BitSet input, int size) {
		int x = rand.nextInt(size);
		int y = rand.nextInt(size);
		//System.out.println("x: "+x+", y: "+y);
		boolean result = input.get(x)^input.get(y) == input.get(x^y);
		return result;
	}
	
	public static boolean test(BitSet input, int size, double closeness) {	//we want closeness = 0.99
		int repetitions = (int) (1/(1-closeness))+1;
		for (int i = 0; i<repetitions; i++) {
			if (!singleTest(input, size)) {
				return false;
			}
		}
		return true;
	}

}
