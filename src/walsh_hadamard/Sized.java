package walsh_hadamard;


public abstract class Sized {
	protected int numVars;
	
	protected Sized(int numVars) {
		if (numVars<1) {
			throw new IllegalArgumentException("Must have at least one variable");
		}
		this.numVars = numVars;
	}
	
	public int getNumVars() {
		return numVars;
	}
}
