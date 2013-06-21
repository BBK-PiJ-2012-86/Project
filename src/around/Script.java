package around;

import pcp.Proof;
import pcp.Prover;
import pcp.VResult;
import pcp.Verifier;



public class Script {
	
	private static long time;

	public static void main(String[] args) {
		
		time = System.currentTimeMillis();
		log("start");
		
		SysEqnAss quadeq = Generator.makeQuadeq(4, 4);
		System.out.println(quadeq.sysEqn);
		System.out.println(quadeq.ass);
		log("made problem");
		
		
		Proof proof = Prover.constructProof(quadeq.ass);
		log("made proof");
		
		VResult result = Verifier.verify(proof, quadeq.sysEqn);	//to work on efficiency!
		System.out.println("Result: "+result.message);
		log("verified");
		
		System.out.println(Checker.satisfies(quadeq.ass, quadeq.sysEqn));
		log("aliter");
	}
	
	private static void log(String message) {	//change to use an aspect..
		System.out.println("    BOOM   "+message+":" +(System.currentTimeMillis()-time));
	}
	

}
