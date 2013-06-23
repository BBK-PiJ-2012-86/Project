package pcp;

import java.util.BitSet;
import java.util.Random;

public class WalshHadamard {

	private final static Random rand = new Random();
	
	public static boolean encodeBit(BitSet input, int inLength, BitSet position) {
		boolean result = false;
		for (int i = 0; i<inLength; i++) {
			result = result^(input.get(i)&&position.get(i));
		}
		return result;
	}
	

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
	
	public static BitSet decode(BitSet input, int inLength) {
		int outLength = binlog(inLength);
		BitSet output = new BitSet(outLength);
		
		for(int i = 0; i < outLength; i++) {
			int x1 = rand.nextInt(inLength);
			int x2 = x1 ^ (1 << (outLength-i-1));
			
			output.set(i, input.get(x1) ^ input.get(x2));
		}
		
		return output;
	}
	
	public static boolean recoverBitAtPosition (BitSet encoding, int length, int targetBit){
		int x1 = rand.nextInt(length);
		int x2 = x1 ^ targetBit;
		return encoding.get(x1) ^ encoding.get(x2);
	}
	
	public static int binlog( int bits ) // returns 0 for bits=0
	{
	    int log = 0;
	    if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
	    if( bits >= 256 ) { bits >>>= 8; log += 8; }
	    if( bits >= 16  ) { bits >>>= 4; log += 4; }
	    if( bits >= 4   ) { bits >>>= 2; log += 2; }
	    return log + ( bits >>> 1 );
	}

}
