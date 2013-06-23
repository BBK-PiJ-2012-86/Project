package new_approach;

import java.util.BitSet;

public class WH {

	public static boolean encodeBit(BitSet input, BitSet position) {
		position.and(input);
		return position.cardinality()%2==1;
	}
}
