package prob;

import java.util.HashSet;
import java.util.Set;


public class SysEqn extends Sized{
	private Set<Eqn> eqns;
	
	public SysEqn(int numVars) {
		super(numVars);
		eqns = new HashSet<Eqn>();
	}
	
	public Set<Eqn> getEqns() {
		return eqns;
	}
	
	public boolean addEqn(Eqn eqn) {
		if (eqn.getNumVars()==numVars) {
			return eqns.add(eqn);
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
	
}
