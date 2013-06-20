package walsh_hadamard;

import java.util.BitSet;
import java.util.Random;

public class AssignmentTester {

	private static Random rand = new Random();
	
	private static boolean singleTest(SystemEqn eqns, BitSet input) {
		int numVars = eqns.getNumVars();
		Eqn newEqn = new Eqn(numVars);
		BitSet newCoeffs = newEqn.getCoeffs();
		for (Eqn eqn : eqns.getEqns()) {
			int i = rand.nextInt(2);
			if (i==1) {
				BitSet eqnCoeffs = eqn.getCoeffs();
				for (int j = 0; j< numVars*numVars; j++) {
					newCoeffs.set(j,newCoeffs.get(j)^eqnCoeffs.get(j));
				}
				newEqn.setRhs(newEqn.getRhs()^eqn.getRhs());
			}
		}

		boolean result = input.get(asInt(newCoeffs,numVars*numVars))==newEqn.getRhs();	//query g at input coeffs..
		return result;
	}
	
	private static int asInt(BitSet bitSet, int size) {
		int result = 0;
		for (int i = 0; i<size; i++) {
			if (bitSet.get(i)) {
				result+=Math.pow(2, size-i-1);
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	private static boolean satisfied(Eqn eqn, BitSet input) {	//put somewhere else..
		int numVars = eqn.getNumVars();
		BitSet coeffs = eqn.getCoeffs();
		int i, j;
		boolean lhs = false;
		for (int n = 0; n< numVars*numVars; n++) {
			if(coeffs.get(n)) {
				i = n/numVars+1;
				j = n%numVars+1;
				if (i<=j) {
					lhs = lhs ^ (input.get(i)&&input.get(j));
				}
			}
		}
		return lhs==eqn.getRhs();
	}

	public static boolean test(SystemEqn eqns, BitSet input, int repetitions) {
		for (int i = 0; i<repetitions; i++) {
			if (!singleTest(eqns, input)) {
				return false;
			}
		}
		return true;
	}
}
