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
	
	public static BitSet getRand(int size) {
		int numLongs = size/64+1;
		long[] longs = new long[numLongs];
		for (int i = 0; i<numLongs; i++) {
			longs[i] = rand.nextLong();
		}
		return BitSet.valueOf(longs);
	}
	
	public static BitSet[] verifRequest(SysEqn eqns) {
		int numVars = eqns.getNumVars();
		BitSet[] wanted = new BitSet[611];	//have one for each size?
		int assEncSize = (int) Math.pow(2, numVars);
		int crossEncSize = (int) Math.pow(2, numVars*numVars);
		// for linearity of ass encoding
		for (int i = 0; i<300; i+=3) {
			wanted[i] = getRand(assEncSize);
			wanted[i+1] = getRand(assEncSize);
			wanted[i+2] = (BitSet) wanted[i].clone();
			wanted[i+2].xor(wanted[i]);
		}
		// for linearity of ass encoding
		for (int i = 300; i<600; i+=3) {
			wanted[i] = getRand(crossEncSize);
			wanted[i+1] = getRand(crossEncSize);
			wanted[i+2] = (BitSet) wanted[i].clone();
			wanted[i+2].xor(wanted[i]);
		}
		// for rel testing
		for (int i = 600; i<609; i+=3) {
			wanted[i] = getRand(assEncSize);
			wanted[i+1] = getRand(assEncSize);
			wanted[i+2] = doCross(wanted[i],wanted[i+1], assEncSize);
		}
		// ass testing
		for (int i = 609; i<611; i++) {
			Eqn newEqn = new Eqn(numVars);
			BitSet newCoeffs = newEqn.getCoeffs();
			BitSet eqnCoeffs = null;
			for (Eqn eqn : eqns.getEqns()) {
				if (rand.nextBoolean()) {
					eqnCoeffs = eqn.getCoeffs();
					for (int j = 0; j< numVars*numVars; j++) {
						newCoeffs.set(j,newCoeffs.get(j)^eqnCoeffs.get(j));
					}
					newEqn.setRhs(newEqn.getRhs()^eqn.getRhs());
				}
			}
			wanted[i] = newCoeffs;	//might be wrong
		}
		return wanted;
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
