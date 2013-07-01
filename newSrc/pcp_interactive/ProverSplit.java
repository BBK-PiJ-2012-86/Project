package pcp_interactive;

import java.util.BitSet;

import structure.Assignment;
import utilities.Manip;

public class ProverSplit {
	
	public static BitSet proverInfo1(Assignment assignment, BitSet[] request) {
		BitSet ass = assignment.getAssSet();
		BitSet result = new BitSet(306);
		for (int i = 0; i<306; i++) {
			result.set(i,encodeBit(ass, request[i]));
		}
		return result;
	}
	
	public static BitSet proverInfo2(Assignment assignment, BitSet[] request) {
		int numVars = assignment.getNumVars();
		BitSet ass = assignment.getAssSet();
		BitSet cross = Manip.cross(ass, ass, numVars);
		BitSet result = new BitSet(305);
		for (int i = 0; i<305; i++) {
			result.set(i,encodeBit(cross, request[i]));
		}
		return result;
	}
	
	private static boolean encodeBit(BitSet input, BitSet position) {
		position.and(input);
		return position.cardinality()%2==1;
	}

}
