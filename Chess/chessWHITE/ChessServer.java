import java.io.IOException;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChessServer {
	public static int intData = 0;
		 public static void markServer(){
			try{
				//サーバーのポート番号を指定
				ServerSocket svSock = new ServerSocket(50000);
	
				Scanner s = new Scanner(System.in);
											
				
					//アクセスを待ち受け
					Socket sock = svSock.accept();
					
					
					//受信ストリームの取得
					DataInputStream in = new DataInputStream(sock.getInputStream());
					
					//データを受信
					intData = in.readInt();
					
					
					//バイト配列を文字列に変換して表示
					System.out.println("「"+ intData + "」を受信しました");
					
						//受信ストリームの終了
						in.close();
				
			
			
				
				//サーバー終了
				svSock.close();
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	public static void main(String[] args){
	}
}
