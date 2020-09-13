import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class yourTurnSend{
	static public void sendData(int s){
		try{
			//送信先のIPアドレスなど（ドメインなどの名前）とポートを指定
			Socket sock = new Socket("localhost",50000);
			
			//送信ストリームの取得
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			
			out.writeInt(s);
						
			out.close();
				
				//終了
				sock.close();
			}catch(IOException e){
				e.printStackTrace();		
		}
	}
}