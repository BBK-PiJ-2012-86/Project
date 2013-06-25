package structure;

import java.util.BitSet;

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
	
	public static Problem make(int numVars, int numEqns) {
		if (numEqns>3*numVars) {
			throw new IllegalArgumentException("Do you really need "+numEqns+" eqns with "+numVars+" vars?");
		}
		int crossSize = numVars*numVars;
		
		Assignment ass = Assignment.make(numVars);
		BitSet assSet = ass.getAssSet();
		BitSet cross = cross(assSet, numVars);
		
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
	
	private static BitSet cross(BitSet bitSet, int size) {
		BitSet result = new BitSet(size*size);
		for (int i = 0; i< size; i++) {
			if(bitSet.get(i)) {
				for (int j = 0; j<size; j++) {
					if (bitSet.get(j)) {
						result.set(i*size+j);
					}
				}
			}
		}
		return result;
	}


}
