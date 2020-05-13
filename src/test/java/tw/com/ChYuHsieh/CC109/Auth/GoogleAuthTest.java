package tw.com.ChYuHsieh.CC109.Auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.jupiter.api.Test;
import tw.com.ChYuHsieh.CC109.Auth.GoogleAuth;


class GoogleAuthTest {

//	@Test
	public void genSecretTest() {
		String secret = GoogleAuth.generateSecretKey();
		String url = GoogleAuth.getQRBarcodeURL("testuser", "testhost", secret);
		System.out.println("Please register " + url);
		System.out.println("Secret key is " + secret);
	}
	
//	@Test
	public void generateKey(){
		String secret = GoogleAuth.generateSecretKey();
		String url = GoogleAuth.getQRBarcodeURL("userAcc", "testhost", secret);
		System.out.println("Please register " + url);
		System.out.println("Secret key is " + secret);
	}
	
//	@Test
	public void generateSecretKey(){
		String secret = GoogleAuth.generateSecretKey();
		String url = GoogleAuth.getQRBarcodeURL("QRUser", "testhost", secret);
		System.out.println("Please register " + url);
		System.out.println("Secret key is " + secret);
	}
	
//	@Test
	public void genSecretKey(){
		String secret = GoogleAuth.generateSecretKey();
		String url = GoogleAuth.getQRBarcodeURL("QRTest", "testhost", secret);
		System.out.println("Please register " + url);
		System.out.println("Secret key is " + secret);
	}
	
//	@Test
	public void genSK() throws UnsupportedEncodingException {
		String secret = GoogleAuth.generateSecretKey();
		String url = GoogleAuth.getQRBarcodeURL("QRKeyTest", "testhost", secret);
		System.out.println("Please register " + url);
		System.out.println("Secret key is " + secret);
	}
	
	

//	static String savedSecret = "F6EUJJMYK7GDC4KI";
	static String savedSecret = "MUOTXIVFSTXQ7EC3";
	static String SecretKey = "DN5DGUE5PTO2ZEN5";
	static String QRSecretKey = "6BIU7PZ67IRMELSW";
	static String QRKey = "PL6TPWEWOPWCISZN";

	
//	@Test
	public void authTest() {
		long code = 432531;
		long t = System.currentTimeMillis();
		GoogleAuth ga = new GoogleAuth();
		ga.setWindowSize(5);
		boolean r = ga.check_code(savedSecret, code, t);
		System.out.println("Check code = " + r);
	}
	
//	@Test
	public void authKeyTest() {
		long code = 764467;
		long t = System.currentTimeMillis();
		GoogleAuth ga = new GoogleAuth();
		ga.setWindowSize(5);
		boolean r = ga.check_code(SecretKey, code, t);
		System.out.println("Check code = " + r);
	}
	
//	@Test
	public void authQRTest() {
		long code = 107005;
		long t = System.currentTimeMillis();
		GoogleAuth ga = new GoogleAuth();
		ga.setWindowSize(5);
		boolean r = ga.check_code(QRSecretKey, code, t);
		System.out.println("Check code = " + r);
	}

}
