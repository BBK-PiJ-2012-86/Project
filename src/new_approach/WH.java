package new_approach;

import java.util.BitSet;

public class WH {

	public static boolean encodeBit(BitSet input, BitSet position) {
		position.and(input);
		return position.cardinality()%2==1;
	}
	
	public static BitSet makeCross(BitSet input, int length) {
		BitSet result = new BitSet(length*length);
		for (int i = 0; i< length; i++) {
			if(input.get(i)) {
				for (int j = 0; j<length; j++) {
					if (input.get(j)) {
						result.set(i*length+j);
					}
				}
			}
		}
		return result;
	}
}
