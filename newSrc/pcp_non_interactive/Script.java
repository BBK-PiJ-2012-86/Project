package pcp_non_interactive;

import java.util.Random;

import structure.Problem;

public class Script {
	private static long time;

	public static void main(String[] args) {
		Problem prob = Problem.makeQ(2, 2);
		Proof proof = Prover.constructProof(prob.getAss());
		log("doing");
		boolean result = new Verifier2(new Random()).verify(proof, prob.getEqns());
		log("done");
		System.out.println(result);
		
		System.out.println(prob.isCorrect());
		log("n done");
	}
	
	public static void log(String message) {
		System.out.println(message+": "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		
	}
}
