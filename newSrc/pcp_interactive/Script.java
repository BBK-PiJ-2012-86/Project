package pcp_interactive;

import structure.Problem;

public class Script {
	private static long time;

	public static void main(String[] args) {
		log("starting");
		Problem prob = Problem.makeQ(400, 800);
		log("made");
		Verifier v = new Verifier();
		boolean result = v.verify(Prover.proverInfo(prob.getAss(), v.request(prob.getEqns())));
		System.out.println(result);
		log("done");
		System.out.println(prob.isCorrect());
		log("naive");

	}
	
	public static void log(String message) {
		System.out.println(message+": "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		
	}

}
