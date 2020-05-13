package tw.com.ChYuHsieh.CC109.SockwtServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class socketserver {
	
	public static final int PORT = 12345;
    public void init() {  
        try {  
            ServerSocket serverSocket = new ServerSocket(PORT);  
            while (true) {  
                // 一旦有堵塞, 則表示伺服器與客戶端獲得了連線  
                Socket client = serverSocket.accept();  
                // 處理這次連線  
                new HandlerThread(client);  
            }  
        } catch (Exception e) {  
            System.out.println("伺服器異常: " + e.getMessage());  
        }  
    }  
  
    private class HandlerThread implements Runnable {  
        private Socket socket;  
        public HandlerThread(Socket client) {  
            socket = client;  
            new Thread(this).start();  
        }  
  
        public void run() {  
            try {  
                // 讀取客戶端資料  
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String clientInputStr = input.readUTF();//這裡要注意和客戶端輸出流的寫方法對應,否則會拋 EOFException
                // 處理客戶端資料  
                System.out.println("客戶端發過來的內容:" + clientInputStr);  
  
                // 向客戶端回覆資訊  
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
                System.out.print("請輸入:\t");  
                // 傳送鍵盤輸入的一行  
                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();  
                out.writeUTF(s);  
                
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("伺服器 run 異常: " + e.getMessage());  
            } finally {  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (Exception e) {  
                        socket = null;  
                        System.out.println("服務端 finally 異常:" + e.getMessage());  
                    }  
                }  
            } 
        }  
    }

	public static void main(String[] args) {		
	       System.out.println("伺服器啟動...\n");  
	       socketserver server = new socketserver();  
	        server.init();  

	        
	}

}
