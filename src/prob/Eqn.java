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
	}
	
	public BitSet getCoeffs() {
		return coeffs;
	}
	
	public void setCoeffs(BitSet coeffs) {
		this.coeffs = coeffs;
	}
	
	public boolean getRhs() {
		return rhs;
	}
	
	public void setRhs(boolean val) {
		this.rhs = val;
	}
	
	/*public boolean isSubEqnOf(Eqn other) {
		for(int i = 0; i<numVars; i++) {
			if(coeffs.get(i)&& !other.getCoeffs().get(i)) {
				return false;
			}
		}
		return true;
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((coeffs == null) ? 0 : coeffs.hashCode());
		result = prime * result + (rhs ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Eqn))
			return false;
		Eqn other = (Eqn) obj;
		if (coeffs == null) {
			if (other.coeffs != null)
				return false;
		} else if (!coeffs.equals(other.coeffs))
			return false;
		if (rhs != other.rhs)
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {		// changed xij and xji now both allowed
		String result = "Eqn: ";
		boolean started = false;
		int i, j;
		for (int n = 0; n< numVars*numVars; n++) {
			if(coeffs.get(n)) {
				i = n/numVars+1;
				j = n%numVars+1;
				if (true) {
					result+=(started?" + ":"")+"x"+(n/numVars+1)+"*x"+(n%numVars+1);
					started = true;
				}
			}
		}
		if(started==false) {
			result+="0";
		}
		result+= " = "+((rhs)?1:0);
		return result;
	}
	
}
