package utilities;

import java.util.BitSet;
import java.util.Random;

public class Rand {
	private Random random;
	
	public Rand(Random rand) {
		this.random = rand;
	}

	public BitSet makeRandomBitset(int n) {
		int numLongs = n/64+1;
		long[] longs = new long[numLongs];
		for (int i = 0; i< numLongs; i++) {
			longs[i]=random.nextLong();
		}
		BitSet result = BitSet.valueOf(longs);
		result.clear(n,n+64);
		return result;
	}
	
	public boolean getNextBoolean() {
		return random.nextBoolean();
	}
	
	//Static instance, for backwards compatibility
	
	private static Rand instance = new Rand(new Random());
	
	public static BitSet make(int n) {
		return instance.makeRandomBitset(n);
	}
	
	public static boolean nextBoolean() {
		return instance.getNextBoolean();
	}
	
	public static void setRandom(Random instance) {
		Rand.instance = new Rand(instance);
	}
}
