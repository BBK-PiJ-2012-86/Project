package pcp_non_interactive;

import java.util.BitSet;
import java.util.Random;

import structure.Eqn;
import structure.Eqns;
import utilities.Rand;

public class Verifier2 {
	private final Random rand;
	
	public Verifier2(Random rand) {
		this.rand = rand;
	}
	
	public boolean verify(Proof proof, Eqns eqns) {
		int numVars = proof.getNumVars();
		BitSet assEnc = proof.getAssEnc();
		BitSet crossEnc = proof.getCrossEnc();
		int assEncSize = (int) Math.pow(2,numVars);
		int crossEncSize = (int) Math.pow(2,numVars*numVars);
				
		
		for (int i = 0; i<100; i++) {
			if(!testLinearity(assEnc, assEncSize)) return false;
		}
		for (int i = 0; i<100; i++) {
			if(!testLinearity(crossEnc, crossEncSize)) return false;
		}
		for (int i = 0; i<3; i++) {
			if(!testCross(numVars, assEnc, crossEnc, assEncSize)) return false;
		}
		
		for (int i = 0; i<2; i++) {
			if(!testAss(eqns, numVars, crossEnc, crossEncSize)) return false;
		}
		
		return true;
	}

	boolean testAss(Eqns eqns, int numVars, BitSet crossEnc, int crossEncSize) {
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
		//if(crossEnc.get(asInt(newCoeffs,numVars*numVars))!=rhs) {
		System.out.println("newCoeffs=" + newCoeffs);
		int val = newCoeffs.cardinality() > 0 ? (int)reverse(newCoeffs,numVars*numVars).toLongArray()[0] : 0;
		System.out.println("newCoeffsVal=" + val);
		if(crossEnc.get(val) !=rhs) {
			System.out.println("ass problem");
			return false;
		}
		
		return true;
	}

	boolean testCross(int numVars, BitSet assEnc, BitSet crossEnc, int assEncSize) {
		int x = rand.nextInt(assEncSize);
		int y = rand.nextInt(assEncSize);
		int xCrossy = 0;	//need a better way of calculating this..
		
		for (int j = 0; j< numVars; j++) {
			xCrossy = (xCrossy << numVars) | (((x & 1<<(numVars - j - 1)) == 0) ? 0 : y); 
		}
		System.out.println("x=" + x + ",y=" + y + ",cross=" + xCrossy);
		
		if((assEnc.get(x)&&assEnc.get(y)) != crossEnc.get(xCrossy)) {
			System.out.println("cross problem");
			return false;
		}
		return true;
	}

	boolean testLinearity(BitSet assEnc, int assEncSize) {
		int x = rand.nextInt(assEncSize);
		int y = rand.nextInt(assEncSize);
		if( assEnc.get(x)^assEnc.get(y) != assEnc.get(x^y)) {return false;};
		return true;
	}
	
	private static BitSet reverse(BitSet bs, int numVars) { //needs revisiting
		BitSet result = new BitSet(numVars);
		
		for (int i = 0; i < numVars; i++) {
			result.set(i, bs.get(numVars - i - 1));
		}
		
		return result;
	}
	
	/*private static int asInt(BitSet bitSet, int size) {	// need to do better
		int result = 0;
		for (int i = 0; i<size; i++) {
			if (bitSet.get(i)) {
				result+=Math.pow(2, size-i-1);
			}
		}
		return result;
	}*/

}
