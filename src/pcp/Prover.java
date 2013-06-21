package pcp;

import java.util.BitSet;

import prob.Assignment;

public class Prover {

	public static Proof constructProof(Assignment ass) {
		int numVars = ass.getNumVars();
		BitSet assSet = ass.getAssSet();
		
		BitSet assEnc = WalshHadamard.encode(assSet, numVars);
		
		BitSet cross = new BitSet(numVars*numVars);
		for (int i = 0; i< numVars; i++) {
			if(assSet.get(i)) {
				for (int j = 0; j<numVars; j++) {
					if (assSet.get(j)) {
						cross.set(i*numVars+j);
					}
				}
			}
		}
		BitSet crossEnc = WalshHadamard.encode(cross, numVars*numVars);
		
		return new Proof(numVars,assEnc,crossEnc);
	}

}
