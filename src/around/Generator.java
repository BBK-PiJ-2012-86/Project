package around;

import java.util.Random;

import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class Generator {
	private static Random rand = new Random();
	
	public static Eqn makeEqn(int numVars) {
		Eqn eqn = new Eqn(numVars);
		
		for (int i = 1; i<=numVars; i++) {
			for (int j = i; j<=numVars; j++) {
				eqn.setCoeff(i, j, rand.nextBoolean());
			}
		}
		eqn.setRhs(rand.nextBoolean());
		return eqn;
	}
	
	public static SysEqn makeSysEqn(int numVars, int numEqns) {
		if(numEqns>Math.pow(2,numVars*(numVars+1)/2+1)) {
			throw new IllegalArgumentException("Can't have "+numEqns+" eqns with "+numVars+" vars!");
		}
		SysEqn eqns = new SysEqn(numVars);
		int count = 0;
		while (count<numEqns) {
			if(eqns.addEqn(makeEqn(numVars))) {
				count++;
			}
		}
		return eqns;
	}
	
	
	public static Assignment makeAss(int numVars) {
		Assignment ass = new Assignment(numVars);
		for (int i = 1; i<=numVars; i++) {
			ass.setVal(i, rand.nextBoolean());
		}
		return ass;
	}
	
}
