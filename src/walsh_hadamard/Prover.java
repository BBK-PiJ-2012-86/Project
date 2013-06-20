package walsh_hadamard;

import java.util.BitSet;

public class Prover {

	public static Proof constructProof(Assignment ass) {
		int numVars = ass.getNumVars();
		BitSet assSet = ass.getAss();
		
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
	
	public static void main(String[] args) {
		Assignment ass = new Assignment(2);
		ass.setAss(2);
		System.out.println(ass);
		Proof proof = Prover.constructProof(ass);
		System.out.println(proof.getAssEnc());
		System.out.println(proof.getCrossEnc());
	}
}
