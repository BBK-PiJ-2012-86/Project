package walsh_hadamard;

import java.util.HashSet;
import java.util.Set;

public class SystemEqn {
	int numVars;
	private Set<Eqn> eqns;
	
	public SystemEqn(int numVars) {
		this.numVars = numVars;
		eqns = new HashSet<Eqn>();
	}
	
	public boolean addEqn(Eqn eqn) {
		if (eqn.getNumVars()==numVars) {
			eqns.add(eqn);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String result = "-----\r\n";
		for (Eqn eqn : eqns) {
			result+=eqn+"\r\n";
		}
		result+="-----";
		return result;
	}
	
	public static void main(String[] args) {
		SystemEqn equations = new SystemEqn(3);
		Eqn eqn1 = new Eqn(3);
		eqn1.setCoeff(2, 3, true);
		eqn1.setCoeff(2, 2, true);
		equations.addEqn(eqn1);
		Eqn eqn2 = new Eqn(3);
		eqn2.setCoeff(1, 3, true);
		eqn2.setRhs(true);
		equations.addEqn(eqn2);
		System.out.println(equations);
	}

}
