package structure;

import java.util.BitSet;
import java.util.Random;

public class EqnPlus extends Eqn {
		
	public EqnPlus(int numVars, BitSet coeffs, boolean rhs) {
		super(numVars, coeffs, rhs);
		niceify();
	}
	/*public EqnPlus(int numVars, BitSet coeffs, boolean rhs, boolean niceify) {	//just for testing
		this.numVars = numVars;
		this.coeffs = coeffs;
		this.rhs = rhs;
		if (niceify) niceify();
	}*/
	@Override
	public void setCoeffs(BitSet coeffs) {
		super.setCoeffs(coeffs);
		niceify();
	}

	private void niceify() {
		int numVars = getNumVars();
		BitSet coeffs = getCoeffs();
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
		super.setCoeffs(newCoeffs);
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
	
	/*public static EqnPlus makeFaster(int numVars) {
		int crossSize = numVars*numVars;
		int numLongs = crossSize/64+1;
		long[] longs = new long[numLongs];
		Random rand = new Random();
		for (int i = 0; i< numLongs; i++) {
			longs[i]=rand.nextLong();
		}

		BitSet coeffs = BitSet.valueOf(longs);
	
		int a = 0;
		int n = 0;
		while (n<numVars) {
			coeffs.clear(a,a+ n);
			a+=numVars;
			n++;
		}
		coeffs.clear(crossSize,crossSize+64);
		return new EqnPlus(numVars, coeffs, rand.nextBoolean(), false);
	}*/

}
