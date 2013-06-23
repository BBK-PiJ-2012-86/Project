package new_approach;

import java.util.BitSet;

public class WH {

	public static boolean encodeBit(BitSet input, BitSet position) {
		boolean result = false;
		for (int i = 0; i<input.length(); i++) {
			result = result^(input.get(i)&&position.get(i));
		}
		return result;
	}
}
