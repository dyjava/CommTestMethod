package com.method.util;

/**
 * made by dyong 
 * date 2009-10-14 上午11:44:53
 **/
public class Base64 {

	/**
	 * 将原始数据编码为base64编码
	 * @param data
	 * @return
	 */
	public char[] encode(byte[] data){
		char[] out = new char[((data.length + 2) / 3) * 4];
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4){
			boolean quad = false;
			boolean trip = false;
			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length){
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}
	
	/**
	 * 将base64编码的数据解码成原始数据
	 * @param data
	 * @return
	 */
	public byte[] decode(char[] data) {
		int len = ((data.length + 3) / 4) * 3;
		if(data.length > 0 && data[data.length - 1] == '='){
			--len;
		}
		if(data.length > 1 && data[data.length - 2] == '='){
			--len;   
		}
		byte[] out = new byte[len];
		int shift = 0;
		int accum = 0;
		int index = 0;
		for(int ix = 0; ix < data.length; ix++) {
			int value = codes[data[ix] & 0xFF];
			if(value >= 0){
				accum <<= 6;
				shift += 6;
				accum |= value;
				if(shift >= 8){
					shift -= 8;
					out[index++] = (byte)((accum >> shift) & 0xff);
				}
			}
		}
		if(index != out.length){
			throw new Error("miscalculated data length!");
		}
		return out;
	}
	
	
	static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
	static private byte[] codes = new byte[256];
	static {
		for (int i = 0; i < 256; i++){
			codes[i] = -1;
		}
		for (int i = 'A'; i <= 'Z'; i++){
			codes[i] = (byte) (i - 'A');
		}
		for (int i = 'a'; i <= 'z'; i++){
			codes[i] = (byte) (26 + i - 'a');
		}
		for (int i = '0'; i <= '9'; i++){
			codes[i] = (byte) (52 + i - '0');
		}
		codes['+'] = 62;
		codes['/'] = 63;
	}
	
     public static String encode(String code){
    	 Base64 base = new Base64() ;
    	 return new String(base.encode(code.getBytes())) ;
     }
     public static String decode(String code){
    	 Base64 base = new Base64() ;
    	 return new String(base.decode(code.toCharArray())) ;
     }
     
     public static void main(String[] args) throws Exception {
    	 Base64 b = new Base64() ;
    	 String s = "aHR0cDovL3oyLndpZnVuLmNvbS9yZXNvdXJjZXMvNC8zNy81NTgwNS82YTg1NzNieFVNdC5tcDM=" ;
    	 String strOut2 = new String(b.decode(s.toCharArray()));
    	 System.out.println(strOut2);
    	 
    	 String ss = "刘德华7562\\/--=()!@#$%^&*(<>?,./;'|" ;
    	 ss = Base64.encode(ss) ;
    	 System.out.println(ss) ;
    	 ss = Base64.decode(ss) ;
    	 System.out.println(ss) ;
    }
     
}
