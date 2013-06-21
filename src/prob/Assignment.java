package prob;

import java.util.BitSet;


public class Assignment extends Sized{
	private BitSet assSet;
	
	public Assignment(int numVars) {
		super(numVars);
		assSet = new BitSet(numVars);
	}
	
	public BitSet getAssSet() {
		return assSet;
	}
	
	public void setAssSet(int... ones) {
		assSet.clear();
		for (int i : ones) {
			if (i<1 || i>getNumVars()) {
				throw new IllegalArgumentException("Can only assign values to x1 to x"+getNumVars());
			}
			assSet.set(i-1);
		}
	}
	
	public void setVal(int index, boolean value) {
		assSet.set(index-1,value);
	}
	
	@Override
	public String toString() {
		String result = "Ass: ";
		for (int i = 0; i<getNumVars()-1; i++) {
			result+="x"+(i+1)+" = "+(assSet.get(i)?1:0)+", ";
		}
		result+="x"+(getNumVars())+" = "+(assSet.get(getNumVars()-1)?1:0);
		return result;
	}

}
