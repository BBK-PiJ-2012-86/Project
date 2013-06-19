package walsh_hadamard;

import java.util.BitSet;
import java.util.Random;

public class WalshHadamardBitSet {

	public byte[] encrypt(byte[] message, int messageLengthBits) {
		BitSet input = BitSet.valueOf(message);
		int cypherLength = (int)Math.pow(messageLengthBits, 2);
		BitSet output = new BitSet(cypherLength);
		
		int bytesWritten = 2;
		
		if(input.get(0)) {
			output.set(1);
		}
		
		for(int i = 1; i < messageLengthBits; i++) {
			boolean flip = input.get(i);
			
			for(int j = 0; j < bytesWritten; j++) {
				boolean val = input.get(j);
				output.set(j + bytesWritten, val ^ flip);
			}
			
			bytesWritten *= 2;
		}
		
		return output.toByteArray();
	}

	public byte[] decrypt(byte[] cyphertext, int messageLengthBits) {
		BitSet cypherSet = BitSet.valueOf(cyphertext);
		int lengthOfPlaintext = binlog(messageLengthBits);
		BitSet output = new BitSet(lengthOfPlaintext);
		
		Random rand = new Random();
		
		for(int i = 0; i < lengthOfPlaintext; i++) {
			int x1 = rand.nextInt(messageLengthBits);
			int x2 = x1 ^ i;
			
			output.set(i, cypherSet.get(x1) ^ cypherSet.get(x2));
		}
		
		
		return output.toByteArray();
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WalshHadamardBitSet instance = new WalshHadamardBitSet();
		byte[] input = { 13 };
		byte[] cypher = instance.encrypt(input, 8);
		
		byte[] output = instance.decrypt(cypher, 256);
		
		System.out.println("Input: " + input[0]  + " Output: " + output[0]);

	}

}
