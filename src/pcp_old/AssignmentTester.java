package pcp_old;

import java.util.BitSet;
import java.util.Random;

import prob.Eqn;
import prob.SysEqn;

public class AssignmentTester {

	private static Random rand = new Random();
	
	private static boolean singleTest(SysEqn eqns, BitSet input) {
		int numVars = eqns.getNumVars();
		Eqn newEqn = new Eqn(numVars);
		BitSet newCoeffs = newEqn.getCoeffs();
		for (Eqn eqn : eqns.getEqns()) {
			if (rand.nextBoolean()) {
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



	public static boolean test(SysEqn eqns, BitSet input, int repetitions) {
		for (int i = 0; i<repetitions; i++) {
			if (!singleTest(eqns, input)) {
				return false;
			}
		}
		return true;
	}
}
