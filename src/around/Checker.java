package around;

import java.util.BitSet;

import new_approach.WH;

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
		
		BitSet cross = WH.doCross(assSet, assSet, numVars);
		cross.and(coeffs);
		boolean lhs = cross.cardinality()%2==1;
		//int i, j;
		/*for (int n = 0; n< numVars*numVars; n++) {
			if(coeffs.get(n)) {
				i = n/numVars;
				j = n%numVars;
				if (true) {
					lhs = lhs ^ (assSet.get(i)&&assSet.get(j));
				}
			}
		}*/
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
