package tw.com.ChYuHsieh.CC109.Auth;

//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.imageio.ImageIO;
//
//import com.swetake.util.Qrcode;
//
//import jp.sourceforge.qrcode.QRCodeDecoder;
//import jp.sourceforge.qrcode.data.QRCodeImage;
//import jp.sourceforge.qrcode.exception.DecodingFailedException;


public class code {
//
//	public class TwoDimensionCodeImage implements QRCodeImage {
//		BufferedImage bufImg;
//		public TwoDimensionCodeImage(BufferedImage bufImg) {
//		this.bufImg = bufImg;
//		}
//        @Override
//        public int getHeight() {
//        return bufImg.getHeight();
//        }
//        @Override
//        public int getPixel(int x, int y) {
//        return bufImg.getRGB(x, y);
//        }
//        @Override
//        public int getWidth() {
//        return bufImg.getWidth();
//        }
//	}
//
//	/**
//	 * 解析二維碼（QRCode）
//	 * 
//	 * @param imgPath 圖片路徑
//	 * @return
//	 */
//	public String decoderQRCode(String imgPath) {
//		// QRCode 二維碼圖片的文件
//		File imageFile = new File(imgPath);
//		BufferedImage bufImg = null;
//		String content = null;
//		try {
//			bufImg = ImageIO.read(imageFile);
//			QRCodeDecoder decoder = new QRCodeDecoder();
//			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
//		} catch (IOException e) {
//			System.out.println("Error: " + e.getMessage());
//			e.printStackTrace();
//		} catch (DecodingFailedException dfe) {
//			System.out.println("Error: " + dfe.getMessage());
//			dfe.printStackTrace();
//		}
//		return content;
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片
//	 * 
//	 * @param content 存儲內容
//	 * @param imgPath 圖片路徑
//	 */
//	public void encoderQRCode(String content, String imgPath) {
//		this.encoderQRCode(content, imgPath, "png", 7);
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片
//	 * 
//	 * @param content 存儲內容
//	 * @param output  輸出流
//	 */
//	public void encoderQRCode(String content, OutputStream output) {
//		this.encoderQRCode(content, output, "png", 7);
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片
//	 * 
//	 * @param content 存儲內容
//	 * @param imgPath 圖片路徑
//	 * @param imgType 圖片類型
//	 */
//	public void encoderQRCode(String content, String imgPath, String imgType) {
//		this.encoderQRCode(content, imgPath, imgType, 7);
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片
//	 * 
//	 * @param content 存儲內容
//	 * @param output  輸出流
//	 * @param imgType 圖片類型
//	 */
//	public void encoderQRCode(String content, OutputStream output, String imgType) {
//		this.encoderQRCode(content, output, imgType, 7);
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片
//	 * 
//	 * @param content 存儲內容
//	 * @param imgPath 圖片路徑
//	 * @param imgType 圖片類型
//	 * @param size    二維碼尺寸
//	 */
//	public void encoderQRCode(String content, String imgPath, String imgType, int size) {
//		try {
//			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
//
//			File imgFile = new File(imgPath);
//			// 生成二維碼QRCode圖片
//			ImageIO.write(bufImg, imgType, imgFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片
//	 * 
//	 * @param content 存儲內容
//	 * @param output  輸出流
//	 * @param imgType 圖片類型
//	 * @param size    二維碼尺寸
//	 */
//	public void encoderQRCode(String content, OutputStream output, String imgType, int size) {
//		try {
//			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
//			// 生成二維碼QRCode圖片
//			ImageIO.write(bufImg, imgType, output);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 生成二維碼(QRCode)圖片的公共方法
//	 * 
//	 * @param content 存儲內容
//	 * @param imgType 圖片類型
//	 * @param size    二維碼尺寸
//	 * @return
//	 */
//	private BufferedImage qRCodeCommon(String content, String imgType, int size) {
//		BufferedImage bufImg = null;
//		try {
//			Qrcode qrcodeHandler = new Qrcode();
//			// 設置二維碼排錯率，可選L(7%)、M(15%)、Q(25%)、H(30%)，排錯率越高可存儲的信息越少，但對二維碼清晰度的要求越小
//			qrcodeHandler.setQrcodeErrorCorrect('M');
//			qrcodeHandler.setQrcodeEncodeMode('B');
//			// 設置設置二維碼尺寸，取值範圍1-40，值越大尺寸越大，可存儲的信息越大
//			qrcodeHandler.setQrcodeVersion(size);
//			// 獲得內容的字節數組，設置編碼格式
//			byte[] contentBytes = content.getBytes("utf-8");
//			// 圖片尺寸
//			int imgSize = 67 + 12 * (size - 1);
//			bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
//			Graphics2D gs = bufImg.createGraphics();
//			// 設置背景顏色
//			gs.setBackground(Color.WHITE);
//			gs.clearRect(0, 0, imgSize, imgSize);
//
//			// 設定圖像顏色> BLACK
//			gs.setColor(Color.BLACK);
//			// 設置偏移量，不設置可能導致解析出錯
//			int pixoff = 2;
//			// 輸出內容> 二維碼
//			if (contentBytes.length > 0 && contentBytes.length < 800) {
//				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
//				for (int i = 0; i < codeOut.length; i++) {
//					for (int j = 0; j < codeOut.length; j++) {
//						if (codeOut[j][i]) {
//							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
//						}
//					}
//				}
//			} else {
//				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
//			}
//			gs.dispose();
//			bufImg.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return bufImg;
//	}
//
//	/**
//	 * 解析二維碼（QRCode）
//	 * 
//	 * @param input 輸入流
//	 * @return
//	 */
//	public String decoderQRCode(InputStream input) {
//		BufferedImage bufImg = null;
//		String content = null;
//		try {
//			bufImg = ImageIO.read(input);
//			QRCodeDecoder decoder = new QRCodeDecoder();
//			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
//		} catch (IOException e) {
//			System.out.println("Error: " + e.getMessage());
//			e.printStackTrace();
//		} catch (DecodingFailedException dfe) {
//			System.out.println("Error: " + dfe.getMessage());
//			dfe.printStackTrace();
//		}
//		return content;
//	}
//
//	public static void main(String[] args) {
//		// 二維碼輸出地址
//		String imgPath = "D:/Test/totpNew.png";
//		// 二維碼內容，此處放的是跳轉地址，掃碼後自動進入
//		String content = "小黑認真上課阿";
//		code handler = new code();
//		handler.encoderQRCode(content, imgPath, "png", 12);
//		System.out.println("生成二維碼成功");
//
//		String decoderContent = handler.decoderQRCode(imgPath);
//		System.out.println("解析結果：");
//		System.out.println(decoderContent);
//	}

}
