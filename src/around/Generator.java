package around;

import java.util.BitSet;
import java.util.Random;
import java.util.Set;

import new_approach.WH;

import prob.Assignment;
import prob.Eqn;
import prob.SysEqn;

public class Generator {
	private static Random rand = new Random();
	
	public static Eqn makeEqn(int numVars) {
		Eqn eqn = new Eqn(numVars);
		
		while(eqn.getCoeffs().isEmpty()) {
			for (int i = 1; i<=numVars; i++) {
				for (int j = i; j<=numVars; j++) {
					eqn.setCoeff(i, j, rand.nextBoolean());
				}
			}
		}
		eqn.setRhs(rand.nextBoolean());
		return eqn;
	}
	
		
	public static SysEqn makeSysEqn(int numVars, int numEqns) {
		if(numEqns>Math.pow(2,numVars*(numVars+1)/2+1)-2) {
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
	
	public static SysEqnAss makeQuadeq(int numVars, int numEqns) {
		if (numEqns>2*numVars) {
			throw new IllegalArgumentException("Do you really need "+numEqns+" eqns with "+numVars+" vars?");
		}
		SysEqnAss result = new SysEqnAss();
		Boolean sat = false;
		SysEqn eqns = null;
		Assignment ass = null;
		while(!sat) {
			eqns = Generator.makeSysEqn(numVars, numEqns);
			ass = Generator.makeAss(numVars);
			sat = Checker.satisfies(ass, eqns);
		}
		result.sysEqn = eqns;
		result.ass = ass;
		return result;
		
	}
	
	public static SysEqnAss makeQuadeqEff(int numVars, int numEqns) {//TODO: properly
		if (numEqns>3*numVars) {
			throw new IllegalArgumentException("Do you really need "+numEqns+" eqns with "+numVars+" vars?");
		}
		SysEqnAss result = new SysEqnAss();
		Eqn newEqn = null;
		SysEqn eqns = new SysEqn(numVars);
		Assignment ass = makeAss(numVars);
		int count = 0;
		while(count<numEqns) {
			newEqn = makeEqn(numVars);
			if (Checker.satisfies(ass, newEqn)) {
				eqns.addEqn(newEqn);
				count++;
			}
		}
		result.sysEqn = eqns;
		result.ass = ass;
		return result;
		
	}
	
	public static SysEqnAss makeQuadeqEff2(int numVars, int numEqns) {// hmm about xixj=xjxi
		if (numEqns>3*numVars) {
			throw new IllegalArgumentException("Do you really need "+numEqns+" eqns with "+numVars+" vars?");
		}
		int crossSize = numVars*numVars;
		
		SysEqnAss result = new SysEqnAss();
		result.ass = makeAss(numVars);
		BitSet assSet = result.ass.getAssSet();
		BitSet cross = WH.doCross(assSet, assSet, numVars);
		
		result.sysEqn = new SysEqn(numVars);
		Set<Eqn> eqnSet = result.sysEqn.getEqns();
		
		int foundEqns = 0;
		BitSet lhs, check;
		Eqn eqn;
		while(foundEqns<numEqns) {
			lhs = WH.getRand(crossSize);
			check = (BitSet) lhs.clone();
			check.and(cross);
			eqn = new Eqn(numVars);
			eqn.setRhs(check.cardinality()%2==1);
			eqn.setCoeffs(lhs);
			if (eqnSet.add(eqn)) {foundEqns++;}
		}
		return result;
		
	}
	
	
}
