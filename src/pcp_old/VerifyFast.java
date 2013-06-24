package pcp_old;

import java.util.BitSet;
import java.util.Random;

import prob.Eqn;
import prob.SysEqn;

public class VerifyFast {
	private static Random rand = new Random();
	
	public static VResult verify(Proof proof, SysEqn eqns) {
		VResult result = new VResult();
		int numVars = proof.getNumVars();
		BitSet assEnc = proof.getAssEnc();
		BitSet crossEnc = proof.getCrossEnc();
		int assEncSize = (int) Math.pow(2,numVars);
		int crossEncSize = (int) Math.pow(2,numVars*numVars);
				
		
		for (int i = 0; i<100; i++) {
			int x = rand.nextInt(assEncSize);
			int y = rand.nextInt(assEncSize);
			if( assEnc.get(x)^assEnc.get(y) != assEnc.get(x^y)) {return result;};
		}
		for (int i = 0; i<100; i++) {
			int x = rand.nextInt(crossEncSize);
			int y = rand.nextInt(crossEncSize);
			if( crossEnc.get(x)^crossEnc.get(y) != crossEnc.get(x^y)) {return result;};
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
				return result;
			}
		}
		
		
		for (int i = 0; i<2; i++) {
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

			if(crossEnc.get(asInt(newCoeffs,numVars*numVars))!=newEqn.getRhs()) {
				return result;
			}
		}
		
		result.pass = true;
		result.message = "pass";
		return result;
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
