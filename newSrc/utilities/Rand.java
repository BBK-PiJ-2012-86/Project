package utilities;

import java.util.BitSet;
import java.util.Random;

public class Rand {
	private static Random random = new Random();
	
	public static BitSet make(int n) {
		int numLongs = n/64+1;
		long[] longs = new long[numLongs];
		for (int i = 0; i< numLongs; i++) {
			longs[i]=random.nextLong();
		}
		BitSet result = BitSet.valueOf(longs);
		result.clear(n,n+64);
		return result;
	}
	
	public static boolean nextBoolean() {
		return random.nextBoolean();
	}
}
