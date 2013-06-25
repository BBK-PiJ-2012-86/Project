package structure;

import java.util.BitSet;

import utilities.Manip;
import utilities.Rand;

public class Problem extends Sized {

	private Eqns eqns;
	private Assignment ass;
	
	public Problem(int numVars, Eqns eqns, Assignment ass) {
		super(numVars);
		setEqns(eqns);
		setAss(ass);
	}
	
	public Eqns getEqns() {
		return eqns;
	}
	public void setEqns(Eqns eqns) {
		if(eqns.getNumVars()!=getNumVars()) {
			throw new IllegalArgumentException("Number of variables do not match");
		}
		this.eqns = eqns;
	}
	public Assignment getAss() {
		return ass;
	}
	public void setAss(Assignment ass) {
		if(ass.getNumVars()!=getNumVars()) {
			throw new IllegalArgumentException("Number of variables do not match");
		}
		this.ass = ass;
	}

	@Override
	public String toString() {
		return "---\r\n"+eqns.toString()+"\r\n"+ass.toString()+"\r\n---";
	}
	
	public static Problem makeQ(int numVars, int numEqns) {
		if (numEqns>3*numVars) {
			throw new IllegalArgumentException("Do you really need "+numEqns+" eqns with "+numVars+" vars?");
		}
		int crossSize = numVars*numVars;
		
		Assignment ass = Assignment.make(numVars);
		BitSet cross = Manip.cross(ass.getAssSet(), ass.getAssSet(), numVars);
		
		Eqns eqns = new Eqns(numVars);
		
		int foundEqns = 0;
		BitSet lhs, check;
		Eqn eqn = null;
		while(foundEqns<numEqns) {
			lhs = Rand.make(crossSize);
			check = (BitSet) lhs.clone();
			check.and(cross);
			eqn = new Eqn(numVars, lhs, check.cardinality()%2==1);
			if (eqns.add(eqn)) {foundEqns++;}
		}
		return new Problem(numVars, eqns, ass);
	}
	
	public static Problem make(int numVars, int numEqns) {
		Eqns eqns = new Eqns(numVars);
		int foundEqns = 0;
		while(foundEqns<numEqns) {
			if (eqns.add(Eqn.make(numVars))) {foundEqns++;}
		}
		return new Problem(numVars, eqns, Assignment.make(numVars));
	}
	
	public boolean isCorrect() {	//naive
		BitSet cross = Manip.cross(ass.getAssSet(), ass.getAssSet(), getNumVars());
		BitSet check;
		for (Eqn eqn : eqns) {
			check = (BitSet) eqn.getCoeffs().clone();
			check.and(cross);
			if((check.cardinality()%2==1) != eqn.getRhs()) {
				return false;
			}
		}
		return true;
	}

}
