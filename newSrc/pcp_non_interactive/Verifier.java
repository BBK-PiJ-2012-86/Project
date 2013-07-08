package pcp_non_interactive;

import java.util.BitSet;
import java.util.Random;

import structure.Eqn;
import structure.Eqns;
import utilities.Rand;

public class Verifier {
	private static Random rand = new Random();
	private static Rand randUtil = new Rand(rand);
	
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
			int x = rand.nextInt(assEncSize);
			int y = rand.nextInt(assEncSize);
			int xCrossy = 0;	//need a better way of calculating this..
			/*int curr = x;
			for (int j = 0; j< numVars; j++) {
				if (curr%2 == 1) {
					xCrossy+=Math.pow(2, numVars*j)*y;
				}
				curr = curr/2;
			}*/
			for (int j = 0; j< numVars; j++) {
				xCrossy = (xCrossy << numVars) | (((x & 1<<(numVars - j - 1)) == 0) ? 0 : y); 
			}
			System.out.println("x=" + x + ",y=" + y + ",cross=" + xCrossy);
			
			if((assEnc.get(x)&&assEnc.get(y)) != crossEnc.get(xCrossy)) {
				System.out.println("cross problem");
				return false;
			}
		}
		
		for (int i = 0; i<2; i++) {
			int numEqns = eqns.size();
			BitSet random = randUtil.make(numEqns);
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
		}
		
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
