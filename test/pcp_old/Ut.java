package pcp_old;

import java.util.BitSet;

public class Ut {

		public static BitSet make(int... ones) {
			BitSet result = new BitSet();
			for (int i : ones) {
				result.set(i);
			}
			return result;
		}
}
