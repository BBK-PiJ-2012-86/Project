package prob;

import java.util.BitSet;


public class Eqn extends Sized{	//THINK ABOUT xij = xji REDUNDANCY...
	private BitSet coeffs;	// canonical ordering
	private boolean rhs;
	
	public Eqn(int numVars) {
		super(numVars);
		coeffs = new BitSet(numVars*numVars);
		rhs = false;
	}
	
	public void setCoeff(int i, int j, boolean val) {
		if(j<i) {throw new RuntimeException("Not allowing xij j<i for now...");}
		coeffs.set(numVars*(i-1)+j-1, val);
		//coeffs.set(numVars*(j-1)+i-1, val);
	}
	
	public BitSet getCoeffs() {
		return coeffs;
	}
	
	public boolean getRhs() {
		return rhs;
	}
	
	public void setRhs(boolean val) {
		this.rhs = val;
	}
	
	@Override
	public String toString() {
		String result = "Eqn: ";
		boolean started = false;
		int i, j;
		for (int n = 0; n< numVars*numVars; n++) {
			if(coeffs.get(n)) {
				i = n/numVars+1;
				j = n%numVars+1;
				if (i<=j) {
					result+=(started?" + ":"")+"x"+(n/numVars+1)+"*x"+(n%numVars+1);
					started = true;
				}
			}
		}
		result+= " = "+((rhs)?1:0);
		return result;
	}
	
}
