package structure;

import java.util.BitSet;
import java.util.Random;

public class Eqn extends Sized {

	//private int numVars;
	private BitSet coeffs;	// canonical ordering
	private boolean rhs;
		
	public Eqn(int numVars, BitSet coeffs, boolean rhs) {
		super(numVars);
		this.coeffs = coeffs;
		this.rhs = rhs;
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
	public void setRhs(boolean rhs) {
		this.rhs = rhs;
	}
	
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		boolean started = false;
		for (int n = 0; n< coeffs.length(); n++) {
			if(coeffs.get(n)) {
				if(started) {builder.append(" + ");	}
				builder.append("x");
				builder.append(n/getNumVars()+1);
				builder.append("x");
				builder.append(n%getNumVars()+1);
				started = true;
			}
		}
		builder.append(" = ");
		builder.append(rhs?"1":"0");
		return builder.toString();
	}
	
	
	public static Eqn make(int numVars) {
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
		return new Eqn(numVars, coeffs, rand.nextBoolean());
	}
	
	/*@Override
	public Object clone() {
		return new Eqn(numVars, (BitSet)coeffs.clone(), rhs);
	}*/
	


}
