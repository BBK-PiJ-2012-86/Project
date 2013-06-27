package pcp;

import java.io.IOException;

import structure.Problem;

public class ScriptSplit {
	private static long time;

	public static void main(String[] args) throws IOException {
		System.in.read();
		log("starting");
		Problem prob = Problem.makeQ(1300, 3000);
		log("made");
		VerifierSplit v = new VerifierSplit();
		boolean result1 = v.verify1(ProverSplit.proverInfo1(prob.getAss(), v.request1(prob.getEqns())));
		System.out.println(result1);
		log("done first");
		boolean result2 = v.verify2(ProverSplit.proverInfo2(prob.getAss(), v.request2(prob.getEqns())));
		System.out.println(result2);
		log("done");
		System.out.println(prob.isCorrect());
		log("naive");

	}
	
	public static void log(String message) {
		System.out.println(message+": "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		
	}

}
