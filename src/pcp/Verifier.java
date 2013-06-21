package pcp;

import prob.SysEqn;

public class Verifier {
	
	public static VResult verify(Proof proof, SysEqn eqns) {
		VResult result = new VResult();
		int numVars = proof.getNumVars();
		if(!LinearityTester.test(proof.getAssEnc(), (int)Math.pow(2, numVars), 0.99)) {
			result.pass = false;
			result.message = "f not linear";
			return result;
		}
		if(!LinearityTester.test(proof.getCrossEnc(), (int)Math.pow(2, numVars*numVars), 0.99)) {
			result.pass = false;
			result.message = "g not linear";
			return result;
			
		}
		if(!RelatedTester.test(proof, 3)) {
			result.pass = false;
			result.message = "f, g not in correct relationship";
			return result;
		}
		if(!AssignmentTester.test(eqns, proof.getCrossEnc(), 2)) {
			result.pass = false;
			result.message = "assignment not satisfying";
			return result;
		}
		result.pass = true;
		result.message = "a pass (prob>=0.5 all ok)";
		return result;
	}

}
