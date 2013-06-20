package walsh_hadamard;

import java.util.BitSet;
import java.util.Random;

public class WalshHadamard {

	private final static Random rand = new Random();

	public static BitSet encode(BitSet message, int messageLengthBits) {
		int cypherLength = (int)Math.pow(2, messageLengthBits);
		BitSet output = new BitSet(cypherLength);
		int bitsWritten = 2;
		
		if(message.get(messageLengthBits-1)) {
			output.set(1);
		}
		for(int i = messageLengthBits-2; i >= 0; i--) {	//remaining bits of input
			boolean flip = message.get(i);
			for(int j = 0; j <bitsWritten ; j++) {	//bits already worked out
				boolean val = output.get(j);
				output.set(j + bitsWritten, val ^ flip);
			}
			
			bitsWritten *= 2;
		}
		
		return output;
	}
	
	public static BitSet decode(BitSet cyphertext, int codeLength) {
		int plainLength = binlog(codeLength);
		BitSet output = new BitSet(plainLength);
		
		for(int i = 0; i < plainLength; i++) {
			int x1 = rand.nextInt(codeLength);
			int x2 = x1 ^ (1 << (plainLength-i-1));
			
			output.set(i, cyphertext.get(x1) ^ cyphertext.get(x2));
		}
		
		return output;
	}
	
	public static boolean recoverBitAtPosition (BitSet cyphertext, int codeLength, int targetBit){
		//int codeLength = (int)Math.pow(2, messageLengthBits);
		int x1 = rand.nextInt(codeLength);
		int x2 = x1 ^ targetBit;
		return cyphertext.get(x1) ^ cyphertext.get(x2);
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
