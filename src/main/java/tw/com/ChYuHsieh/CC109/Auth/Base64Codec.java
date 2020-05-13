package tw.com.ChYuHsieh.CC109.Auth;

import java.io.UnsupportedEncodingException;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;


public class Base64Codec {
	

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//Apache Commons Codec
		
		final Base64 base64 = new Base64();
		final String text = "字串文字";
		final byte[] textByte = text.getBytes("UTF-8");
		//編碼
		final String encodedText = base64.encodeToString(textByte);
		System.out.println(encodedText);
		//解碼
		System.out.println(new String(base64.decode(encodedText), "UTF-8"));
		
		//Java Util Base64
		
//		final Base64.Decoder decoder = Base64.getDecoder();
//		final Base64.Encoder encoder = Base64.getEncoder();
//		final String text = "字串文字";
//		final byte[] textByte = text.getBytes("UTF-8");
//		//編碼
//		final String encodedText = encoder.encodeToString(textByte);
//		System.out.println(encodedText);
//		//解碼
//		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
		

	}

}
