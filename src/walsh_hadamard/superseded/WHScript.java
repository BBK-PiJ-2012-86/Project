package walsh_hadamard.superseded;


public class WHScript {

	public static void main(String[] args) {
		WHScript s = new WHScript();
		s.launch();
	}
	
	private void launch() {
		WalshHadamard wh = new WalshHadamardImpl();
		
		String input = "Hello";
		System.out.println(input);
		
		byte[] message = input.getBytes();
		System.out.println(byteArrTo01(message));
				
		byte[] ciphertext = wh.encrypt(message);
		System.out.println(byteArrTo01(ciphertext));
		
		byte[] cracked = wh.decrypt(ciphertext);
		System.out.println(byteArrTo01(cracked));
		
		System.out.println(new String(cracked));
		
	}
	
	private String byteArrTo01 (byte[] byteArr) {
		String result = "";
		for (byte next: byteArr) {
			result+=byteTo01(next)+" ";
		}
		return result;
	}
	
	private String byteTo01(byte myByte) {
		String str = "";
		for (int i = 7; i>=0; i--) {
			
			if ((myByte & 1<<i) == 0) {
				str += "0";
			} else {
				str += "1";
			}
		}
		return str;
	}
}