package around;

import java.util.BitSet;

import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class Checker {

	public static boolean satisfies(Assignment ass, Eqn eqn) {
		int numVars = eqn.getNumVars();
		if (numVars!=ass.getNumVars()) {
			return false;
		}
		BitSet assSet = ass.getAssSet();
		BitSet coeffs = eqn.getCoeffs();
		
		int i, j;
		boolean lhs = false;
		for (int n = 0; n< numVars*numVars; n++) {
			if(coeffs.get(n)) {
				i = n/numVars;
				j = n%numVars;
				if (i<=j) {
					lhs = lhs ^ (assSet.get(i)&&assSet.get(j));
				}
			}
		}
		return lhs==eqn.getRhs();
	}

	public static boolean satisfies(Assignment ass, SysEqn eqns) {
		for (Eqn eqn : eqns.getEqns()) {
			if(!satisfies(ass,eqn)) {
				return false;
			}
		}
		return true;
	}
	
}
