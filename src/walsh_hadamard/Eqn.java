package walsh_hadamard;

import java.util.BitSet;

public class Eqn extends Sized{	//THINK ABOUT xij = xji REDUNDANCY...
	private BitSet coeffs;	// x1*x1, x1*x2, x1*x3,.. x2*x1, x2*x2, x2*x3,.. etc (atm)
	private boolean rhs;
	
	public Eqn(int numVars) {
		super(numVars);
		coeffs = new BitSet(numVars*numVars);
		rhs = false;
	}
	
	public void setCoeff(int i, int j, boolean val) {
		coeffs.set(numVars*(i-1)+j-1, val);
		coeffs.set(numVars*(j-1)+i-1, val);
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
