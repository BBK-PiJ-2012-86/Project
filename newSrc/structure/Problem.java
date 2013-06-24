package structure;


public class Problem extends Sized {

	private Eqns eqns;
	private Assignment ass;
	
	public Problem(int numVars, Eqns eqns, Assignment ass) {
		super(numVars);
		setEqns(eqns);
		setAss(ass);
	}
	
	public Eqns getEqns() {
		return eqns;
	}
	public void setEqns(Eqns eqns) {
		if(eqns.getNumVars()!=getNumVars()) {
			throw new IllegalArgumentException("Number of variables do not match");
		}
		this.eqns = eqns;
	}
	public Assignment getAss() {
		return ass;
	}
	public void setAss(Assignment ass) {
		if(ass.getNumVars()!=getNumVars()) {
			throw new IllegalArgumentException("Number of variables do not match");
		}
		this.ass = ass;
	}

	@Override
	public String toString() {
		return "---\r\n"+eqns.toString()+"\r\n"+ass.toString()+"\r\n---";
	}
	

}
