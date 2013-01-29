package walsh_hadamard;

import java.util.Random;

public class WalshHadamardImpl implements WalshHadamard {
	
	private final static int BITS_PER_BYTE = 8;
	private final static int BYTE_FACTOR = 32;	// each byte of message gives 2^8 / 8 bytes of ciphertext
	private final static int BITS_IN_CYPHERTEXT_PER_MESSAGE_BYTE = 256;
	
	
	public WalshHadamardImpl() {
		// do nothing
	}

	@Override
	public byte[] encrypt(byte[] message) {
		
		int bytesInCyphertext = BYTE_FACTOR*message.length;
		
		byte[] result = new byte[bytesInCyphertext];
		boolean digit;
		
		for (int messageByteIx = 0; messageByteIx<message.length; messageByteIx++) {
			for (int bitIx = 0; bitIx<BITS_IN_CYPHERTEXT_PER_MESSAGE_BYTE; bitIx++) {
				
				byte prod = (byte) (bitIx & message[messageByteIx]);
				
				digit = false;
				for (int j = 0; j<BITS_PER_BYTE; j++) {
					digit = digit ^ ((prod & (1<<j))>0);
				}
				if (digit) {
					result[BYTE_FACTOR*messageByteIx+(bitIx/BITS_PER_BYTE)] |= (1<<(BITS_PER_BYTE-bitIx%BITS_PER_BYTE-1));
				}
				
			}
		}
		
		return result;
	}

	@Override
	public byte[] decrypt(byte[] ciphertext) {

		int bytesInPlaintext = ciphertext.length/BYTE_FACTOR;
		byte[] plaintext = new byte[bytesInPlaintext];
		
		Random randomGenerator = new Random();
		
		for (int plaintextByteIx = 0; plaintextByteIx<bytesInPlaintext; plaintextByteIx++) {
			for (int bitIx = 0; bitIx<BITS_PER_BYTE ; bitIx++) {
				
				int randIx = randomGenerator.nextInt(BITS_IN_CYPHERTEXT_PER_MESSAGE_BYTE);
				int corrIx = randIx^(1<<(BITS_PER_BYTE-bitIx-1));
				
				boolean randDigit = getDigitOf(ciphertext,plaintextByteIx,randIx);
				boolean corrDigit = getDigitOf(ciphertext,plaintextByteIx,corrIx);
				
				boolean digit = (randDigit != corrDigit);
				if (digit) {
					plaintext[plaintextByteIx] |= (1<<(BITS_PER_BYTE-bitIx-1));
				}
			}
		}
		return plaintext;
	}
	
	
	/*
	 * returns the digit at index ix of the ciphertext bytes corresponding to the plaintext byte
	 */
	private boolean getDigitOf(byte[] ciphertext, int plaintextByteIx, int ix) {
		int byteOfCyphertextIx = plaintextByteIx*BYTE_FACTOR + ix/BITS_PER_BYTE;
		byte rightByte = ciphertext[byteOfCyphertextIx];
		boolean digit = getDigit(rightByte,ix%BITS_PER_BYTE);
		return digit;
	}
	/*
	 * returns the digit of the byte at the index
	 */
	private boolean getDigit(byte myByte, int index) {
		int shift = BITS_PER_BYTE - index - 1;
		return ((myByte & (1<<shift))   >0 );		
	}


}
