package pcp_non_interactive;

import structure.Problem;

public class Script {
	public static void main(String[] args) {
		Problem prob = Problem.makeQ(5, 5);
		Proof proof = Prover.constructProof(prob.getAss());
		boolean result = Verifier.verify(proof, prob.getEqns());
		System.out.println(result);
		System.out.println(prob.isCorrect());
	}
}
