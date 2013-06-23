package new_approach;

import java.util.BitSet;
import java.util.Random;

import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class WH {
	
	private static Random rand = new Random();

	public static boolean encodeBit(BitSet input, BitSet position) {
		position.and(input);
		return position.cardinality()%2==1;
	}
	
	public static BitSet doCross(BitSet input1, BitSet input2, int length) {
		BitSet result = new BitSet(length*length);
		for (int i = 0; i< length; i++) {
			if(input1.get(i)) {
				for (int j = 0; j<length; j++) {
					if (input2.get(j)) {
						result.set(i*length+j);
					}
				}
			}
		}
		return result;
	}
	
	public static BitSet getRand(int size) {	// size is number of bits, index up to 2^size, index encoding of up to size
		int numLongs = size/64+1;
		long[] longs = new long[numLongs];
		for (int i = 0; i<numLongs; i++) {
			longs[i] = rand.nextLong();
		}
		return BitSet.valueOf(longs);
	}
	
	public static BitSet[][] verifRequest(SysEqn eqns) {
		BitSet[][] result = new BitSet[2][];
		result[0] = new BitSet[306];
		result[1] = new BitSet[305];
		int assSize = eqns.getNumVars();
		int crossSize = assSize* assSize;
		// for linearity of ass encoding
		for (int i = 0; i<300; i+=3) {
			result[0][i] = getRand(assSize);
			result[0][i+1] = getRand(assSize);
			result[0][i+2] = (BitSet) result[0][i].clone();
			result[0][i+2].xor(result[0][i+1]);
		}
		// for linearity of cross encoding
		for (int i = 0; i<300; i+=3) {
			result[1][i] = getRand(crossSize);
			result[1][i+1] = getRand(crossSize);
			result[1][i+2] = (BitSet) result[1][i].clone();
			result[1][i+2].xor(result[1][i+1]);
		}
		// for rel testing
		for (int i = 0; i<4; i++) {
			result[0][300+2*i] = getRand(assSize);
			result[0][300+2*i+1] = getRand(assSize);
			result[1][300+i] = doCross(result[0][300+2*i],result[0][300+2*i+1], assSize);
		}
		// ass testing
		for (int i = 0; i<2; i++) {
			Eqn newEqn = new Eqn(assSize);
			BitSet newCoeffs = newEqn.getCoeffs();
			BitSet eqnCoeffs = null;
			for (Eqn eqn : eqns.getEqns()) {
				if (rand.nextBoolean()) {
					eqnCoeffs = eqn.getCoeffs();
					for (int j = 0; j< crossSize; j++) {
						newCoeffs.set(j,newCoeffs.get(j)^eqnCoeffs.get(j));
					}
					newEqn.setRhs(newEqn.getRhs()^eqn.getRhs());
				}
			}
			result[1][304+i] = newCoeffs;	//might be wrong
		}
		return result;
	}
	
	public static BitSet proverInfo(Assignment assignment, BitSet[] request) {
		int numVars = assignment.getNumVars();
		BitSet ass = assignment.getAssSet();
		BitSet cross = doCross(ass, ass, numVars);
		BitSet result = new BitSet(611);
		//  etc
		
		return result;
		
	}
}
