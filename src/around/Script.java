package around;

import java.io.IOException;

import pcp_old.Proof;
import pcp_old.Prover;
import pcp_old.VResult;
import pcp_old.Verifier;
import pcp_old.VerifyFast;



public class Script {
	
	private static long time;

	public static void main(String[] args) throws IOException {
		Object moo = System.in.read();
		
		time = System.currentTimeMillis();
		log("start");
		
		SysEqnAss quadeq = Generator.makeQuadeq(5, 5);	// 6 or more vars too big..
		System.out.println(quadeq.sysEqn);
		System.out.println(quadeq.ass);
		log("made problem");
		
		
		Proof proof = Prover.constructProof(quadeq.ass);
		log("made proof");
		
		VResult result = Verifier.verify(proof, quadeq.sysEqn);
		System.out.println("Result: "+result.message);
		log("verified");
		
		VResult result2 = VerifyFast.verify(proof, quadeq.sysEqn);	//working on efficiency...
		System.out.println("Result: "+result2.message);
		log("verified faster?");
		
		System.out.println(Checker.satisfies(quadeq.ass, quadeq.sysEqn));
		log("aliter");
	}
	
	private static void log(String message) {	//change to use an aspect..
		System.out.println("    BOOM   "+message+":" +(System.currentTimeMillis()-time));
	}
	

}
