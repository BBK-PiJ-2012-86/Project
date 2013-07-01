package pcp_non_interactive;

import java.util.BitSet;
import java.util.Random;

import structure.Eqn;
import structure.Eqns;
import utilities.Rand;

public class Verifier {
	private static Random rand = new Random();
	
	public static boolean verify(Proof proof, Eqns eqns) {
		int numVars = proof.getNumVars();
		BitSet assEnc = proof.getAssEnc();
		BitSet crossEnc = proof.getCrossEnc();
		int assEncSize = (int) Math.pow(2,numVars);
		int crossEncSize = (int) Math.pow(2,numVars*numVars);
				
		
		for (int i = 0; i<100; i++) {
			int x = rand.nextInt(assEncSize);
			int y = rand.nextInt(assEncSize);
			if( assEnc.get(x)^assEnc.get(y) != assEnc.get(x^y)) {return false;};
		}
		for (int i = 0; i<100; i++) {
			int x = rand.nextInt(crossEncSize);
			int y = rand.nextInt(crossEncSize);
			if( crossEnc.get(x)^crossEnc.get(y) != crossEnc.get(x^y)) {return false;};
		}
		for (int i = 0; i<3; i++) {
			int x = rand.nextInt(numVars);
			int y = rand.nextInt(numVars);
			int xCrossy = 0;	//need a better way of calculating this..
			int curr = x;
			for (int j = 0; j< numVars; j++) {
				if (curr%2 == 1) {
					xCrossy+=Math.pow(2, numVars*j)*y;
				}
				curr = curr/2;
			}
			
			if((assEnc.get(x)&&assEnc.get(y)) != crossEnc.get(xCrossy)) {
				return false;
			}
		}
		
		for (int i = 0; i<2; i++) {
			int numEqns = eqns.size();
			BitSet random = Rand.make(numEqns);
			BitSet newCoeffs = new BitSet(crossEncSize);
			Boolean rhs = false;
			int k = 0;
			for (Eqn eqn : eqns) {
				if (random.get(k)) {
					newCoeffs.xor( eqn.getCoeffs());
					rhs = rhs^eqn.getRhs();
				}
				k++;
			}
			if(crossEnc.get(asInt(newCoeffs,numVars*numVars))!=rhs) {
				return false;
			}
		}
		
		return true;
	}
	
	private static int asInt(BitSet bitSet, int size) {	// need to do better
		int result = 0;
		for (int i = 0; i<size; i++) {
			if (bitSet.get(i)) {
				result+=Math.pow(2, size-i-1);
			}
		}
		return result;
	}

}
