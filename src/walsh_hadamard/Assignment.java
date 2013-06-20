package walsh_hadamard;

import java.util.BitSet;

public class Assignment extends Sized{
	private BitSet ass;
	
	public Assignment(int numVars) {
		super(numVars);
		ass = new BitSet(numVars);
	}
	
	public BitSet getAss() {
		return ass;
	}
	
	public void setAss(int... ones) {
		ass.clear();
		for (int i : ones) {
			if (i<1 || i>getNumVars()) {
				throw new IllegalArgumentException("Can only assign values to x1 to x"+getNumVars());
			}
			ass.set(i-1);
		}
	}
	
	@Override
	public String toString() {
		String result = "Ass: ";
		for (int i = 0; i<getNumVars()-1; i++) {
			result+="x"+(i+1)+" = "+(ass.get(i)?1:0)+", ";
		}
		result+="x"+(getNumVars())+" = "+(ass.get(getNumVars()-1)?1:0);
		return result;
	}

}
