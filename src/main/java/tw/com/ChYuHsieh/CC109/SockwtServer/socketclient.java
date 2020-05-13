package tw.com.ChYuHsieh.CC109.SockwtServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;
public class socketclient {
	
    public static final String IP_ADDR = "localhost";//伺服器地址 
    public static final int PORT = 12345;//伺服器埠號  

	public static void main(String[] args) {
		 System.out.println("客戶端啟動...");  
	        System.out.println("當接收到伺服器端字元為 \"OK\" 的時候, 客戶端將終止\n"); 
	        while (true) {  
	            Socket socket = null;
	            try {
	                //建立一個流套接字並將其連線到指定主機上的指定埠號
	                socket = new Socket(IP_ADDR, PORT);  
	                  
	                //讀取伺服器端資料  
	                DataInputStream input = new DataInputStream(socket.getInputStream());  
	                //向伺服器端傳送資料  
	                DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	                System.out.print("請輸入: \t");  
	                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();  
	                out.writeUTF(str);  
	                  
	                String ret = input.readUTF();   
	                System.out.println("伺服器端返回過來的是: " + ret);  
	                // 如接收到 "OK" 則斷開連線  
	                if ("OK".equals(ret)) {  
	                    System.out.println("客戶端將關閉連線");  
	                    Thread.sleep(500);  
	                    break;  
	                }  
	                
	                out.close();
	                input.close();
	            } catch (Exception e) {
	                System.out.println("客戶端異常:" + e.getMessage()); 
	            } finally {
	                if (socket != null) {
	                    try {
	                        socket.close();
	                        System.out.println("socket is closed");
	                    } catch (IOException e) {
	                        socket = null; 
	                        System.out.println("客戶端 finally 異常:" + e.getMessage()); 
	                    }
	                }
	            }
	        }

	}

}
