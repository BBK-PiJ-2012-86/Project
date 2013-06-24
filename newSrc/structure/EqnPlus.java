package structure;

import java.util.BitSet;
import java.util.Random;

public class EqnPlus {
	private int numVars;
	private BitSet coeffs;	// canonical ordering, cancelled and xij is st i<=j
	private boolean rhs;
		
	public EqnPlus(int numVars, BitSet coeffs, boolean rhs) {
		this.numVars = numVars;
		this.coeffs = coeffs;
		this.rhs = rhs;
		niceify();
	}
	public int getNumVars() {
		return numVars;
	}
	public BitSet getCoeffs() {
		return coeffs;
	}
	public void setCoeffs(BitSet coeffs) {
		this.coeffs = coeffs;
		niceify();
	}
	public boolean getRhs() {
		return rhs;
	}
	public void setRhs(boolean rhs) {
		this.rhs = rhs;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coeffs == null) ? 0 : coeffs.hashCode());
		result = prime * result + numVars;
		result = prime * result + (rhs ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EqnPlus))
			return false;
		EqnPlus other = (EqnPlus) obj;
		if (coeffs == null) {
			if (other.coeffs != null)
				return false;
		} else if (!coeffs.equals(other.coeffs))
			return false;
		if (numVars != other.numVars)
			return false;
		if (rhs != other.rhs)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		boolean started = false;
		if (coeffs!=null) {
			for (int n = 0; n< coeffs.length(); n++) {
				if(coeffs.get(n)) {
					if(started) {builder.append(" + ");	}
					builder.append("x");
					builder.append(n/numVars+1);
					builder.append("x");
					builder.append(n%numVars+1);
					started = true;
				}
			}
		}
		builder.append(" = ");
		builder.append(rhs?"1":"0");
		return builder.toString();
	}
	
		
	private void niceify() {
		if (coeffs == null) {return;}
		BitSet newCoeffs = new BitSet(numVars*numVars);
		int n, m, diag;
		for (int i = 0; i<numVars; i++) {
			diag = i*(numVars+1);
			newCoeffs.set(diag, coeffs.get(diag));
			for (int j = i+1; j<numVars; j++) {
				n = i*numVars+j;
				m = j*numVars+i;
				newCoeffs.set(n,coeffs.get(n)^coeffs.get(m));
			}
		}
		coeffs = newCoeffs;
	}
	
	public static EqnPlus make(int numVars) {
		int numLongs = numVars*numVars/64+1;
		long[] longs = new long[numLongs];
		Random rand = new Random();
		for (int i = 0; i< numLongs; i++) {
			longs[i]=rand.nextLong();
		}
		BitSet ones = new BitSet();
		ones.flip(0, numVars*numVars);
		BitSet coeffs = BitSet.valueOf(longs);
		coeffs.and(ones);
		return new EqnPlus(numVars, coeffs, rand.nextBoolean());
	}
	
	@Override
	public Object clone() {
		return new EqnPlus(numVars, (BitSet)coeffs.clone(), rhs);
	}
	

}
