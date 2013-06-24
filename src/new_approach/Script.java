package new_approach;

import java.util.BitSet;

import around.Checker;
import around.Generator;
import around.SysEqnAss;

public class Script {
	
	static long time = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log("starting...");
		
		int toChange = 700;
		SysEqnAss it = Generator.makeQuadeqEff(toChange, (int) (toChange*2));
		log("made");
		
		BitSet[][] req = WH.verifRequest(it.sysEqn);
		
		BitSet[] info = WH.proverInfo(it.ass, req);
		
		System.out.println(WH.verifIt(it.sysEqn, info));
		log("verified funny");
		
		Checker.satisfies(it.ass, it.sysEqn);
		log("verified standard");

	}


	private static void log(String str) {
		System.out.println(str+": "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
	}

}
