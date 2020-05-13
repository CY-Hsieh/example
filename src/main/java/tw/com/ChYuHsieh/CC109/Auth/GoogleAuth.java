package tw.com.ChYuHsieh.CC109.Auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

public class GoogleAuth {

	public static final int SECRET_SIZE = 10;
	public static final String SEED = "g8GjEvTbW5oVSV7avL47357438reyhreyuryetredLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";
	public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
	int window_size = 3; // default 3 - max 17

	public void setWindowSize(int s) {
		if (s >= 1 && s <= 17)
			window_size = s;
	}

	public static String generateSecretKey() {
		SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
			sr.setSeed(Base64.decodeBase64(SEED));
			byte[] buffer = sr.generateSeed(SECRET_SIZE);
			Base32 codec = new Base32();
			byte[] bEncodedKey = codec.encode(buffer);
			String encodedKey = new String(bEncodedKey);
			return encodedKey;
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String getQRBarcodeURL(String user, String host, String secret) {
		String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
		return String.format(format, user, host, secret);
	}
	
	public static String getQRBarcode(String user, String secret) { 
		String format = "otpauth://totp/%s?secret=%s"; 
		return String.format(format, user, secret); 
	} 
	
    public boolean check_code(String secret, long code, long timeMsec) {  
        Base32 codec = new Base32();  
        byte[] decodedKey = codec.decode(secret);   
        long t = (timeMsec / 1000L) / 30L;  

        for (int i = -window_size; i <= window_size; ++i) {  
            long hash;  
            try {  
                hash = verify_code(decodedKey, t + i);  
            }catch (Exception e) {  

                e.printStackTrace();  
                throw new RuntimeException(e.getMessage());  
                //return false;  
            }  
            if (hash == code) {  
                return true;  
            }  
        }  
        // The validation code is invalid.  
        return false;  
    }  
      
    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {  
        byte[] data = new byte[8];
        long value = t;
        System.out.println("Long t = " + t);
        for (int i = 8; i-- > 0; value >>>= 8) {  
            data[i] = (byte) value;  
        } 
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1"); 
        Mac mac = Mac.getInstance("HmacSHA1");  
        mac.init(signKey);  
        byte[] hash = mac.doFinal(data);  
        int offset = hash[20 - 1] & 0xF; 
        System.out.println("offset = " + offset);
        long truncatedHash = 0;  
        for (int i = 0; i < 4; ++i) {  
            truncatedHash <<= 8;  
            truncatedHash |= (hash[offset + i] & 0xFF);  
        }  
        truncatedHash &= 0x7FFFFFFF; 
        truncatedHash %= 1000000; 
        System.out.println("truncatedHash = " + truncatedHash);
        return (int) truncatedHash;  
    }

}
