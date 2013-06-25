package utilities;

import java.util.BitSet;

public class Manip {

	public static BitSet cross(BitSet input1, BitSet input2, int size) {
		BitSet result = new BitSet(size*size);
		for (int i = 0; i< size; i++) {
			if(input1.get(i)) {
				for (int j = 0; j<size; j++) {
					if (input2.get(j)) {
						result.set(i*size+j);
					}
				}
			}
		}
		return result;
	}
}
