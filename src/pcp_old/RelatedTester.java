package pcp_old;

import java.util.Random;

public class RelatedTester {
	private static Random rand = new Random();


	private static boolean singleTest(Proof proof) {
		int size = proof.getNumVars();
		int x = rand.nextInt(size);
		int y = rand.nextInt(size);
		
		int xCrossy = 0;	//need a better way of calculating this..
		int curr = x;
		for (int i = 0; i< size; i++) {
			if (curr%2 == 1) {
				xCrossy+=Math.pow(2, size*(i))*y;
			}
			curr = curr/2;
		}
		
		boolean result = (proof.getAssEnc().get(x)&&proof.getAssEnc().get(y)) == proof.getCrossEnc().get(xCrossy);
		return result;
	}
	
	public static boolean test(Proof proof, int repetitions) {
		for (int i = 0; i<repetitions; i++) {
			if (!singleTest(proof)) {
				return false;
			}
		}
		return true;
	}

}
