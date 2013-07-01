package utilities;

import java.util.BitSet;

public class WH {
	
	public static BitSet encode(BitSet input, int inLength) {
		int outLength = (int)Math.pow(2, inLength);
		BitSet output = new BitSet(outLength);
		int bitsDone = 2;
		
		if(input.get(inLength-1)) {
			output.set(1);
		}
		for(int i = inLength-2; i >= 0; i--) {	//remaining bits of input
			boolean flip = input.get(i);
			for(int j = 0; j <bitsDone ; j++) {	//bits of output already done
				boolean val = output.get(j);
				output.set(j + bitsDone, val ^ flip);
			}
			
			bitsDone *= 2;
		}
		
		return output;
	}

}
