package pcp_interactive;

import java.util.BitSet;

import structure.Eqn;
import structure.Eqns;
import utilities.Manip;
import utilities.Rand;

public class VerifierSplit {

	private boolean[] assTestRhs = new boolean[2];
	private BitSet[] retain = new BitSet[3];
	private boolean[] retPro = new boolean[3];
	private long time;
	
	public void log(String message) {
		System.out.println(message+": "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		
	}
	
	
	public BitSet[] request1(Eqns eqns) {
		//log("BEGIN REQUEST");
		BitSet[] result = new BitSet[306];
		int assSize = eqns.getNumVars();
		for (int i = 0; i<300; i+=3) {
			result[i] = Rand.make(assSize);
			result[i+1] = Rand.make(assSize);
			result[i+2] = (BitSet) result[i].clone();
			result[i+2].xor(result[i+1]);
		}
		//log("FIRST LINEARITY");
		for (int i = 0; i<3; i++) {
			result[300+2*i] = Rand.make(assSize);
			result[300+2*i+1] = Rand.make(assSize);
			retain[i] = Manip.cross(result[300+2*i],result[300+2*i+1], assSize);
		}
		//log("REL");
		return result;
	}
	
	public BitSet[] request2(Eqns eqns) {
		int crossSize = eqns.getNumVars()*eqns.getNumVars();
		BitSet[] result = new BitSet[305];
		for (int i = 0; i<300; i+=3) {
			result[i] = Rand.make(crossSize);
			result[i+1] = Rand.make(crossSize);
			result[i+2] = (BitSet) result[i].clone();
			result[i+2].xor(result[i+1]);
		}
		//log("LINEARITY DONE");
		for (int i = 0; i<3; i++) {
			result[300+i] = retain[i];
		}
		
		// ass testing
		for (int i = 0; i<2; i++) {
			int numEqns = eqns.size();
			BitSet random = Rand.make(numEqns);
			BitSet newCoeffs = new BitSet(crossSize);
			Boolean rhs = false;
			int k = 0;
			for (Eqn eqn : eqns) {
				if (random.get(k)) {
					newCoeffs.xor( eqn.getCoeffs());
					rhs = rhs^eqn.getRhs();
				}
				k++;
			}
			result[303+i] = newCoeffs;
			assTestRhs[i]=rhs;
		}
		//log("ASS");
		return result;
	}

	
	public boolean verify1(BitSet pInf) {
		for (int i = 0; i<100; i++) {
			if(  (pInf.get(3*i)^pInf.get(3*i+1))  != pInf.get(3*i+2) ) {
				return false;
			}
		}
		for (int i = 0; i<3; i++) {
			retPro[i] = pInf.get(300+2*i) && pInf.get(300+2*i+1);
		}
		return true;
		
	}
	
	public boolean verify2(BitSet pInf) {
		for (int i = 0; i<100; i++) {
			if(  (pInf.get(3*i)^pInf.get(3*i+1))  != pInf.get(3*i+2) ) {
				return false;
			}
		}
		for (int i = 0; i<3; i++) {
			if( retPro[i] != pInf.get(300+i)) {
				return false;
			}
		}
		for (int i = 0; i<2; i++) {
			if( pInf.get(303+i)!= assTestRhs[i]) {
				return false;
			}
		}
		return true;
		
	}
}
