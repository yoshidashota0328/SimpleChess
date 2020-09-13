import java.util.Scanner;

public class Chess{

    //あなたの色は黒です
	static final String yourColor = "●";
   
    	
	public static void main(String [] args){
		
		Board.initialize();
    	Board.showBoard();
    	
    	

 //コンソールからの入力を受け付ける
    Scanner s = new Scanner(System.in);

    //ゲーム実行中フラグがtrueのあいだループする
    while(Board.game){
        if(Board.turn.equals(yourColor)){
            
            
            System.out.print("動かしたい駒のx座標を入力してください:");
			int x = s.nextInt();
            yourTurnSend.sendData(x);
            
            System.out.print("動かしたい駒のy座標を入力してください:");
			int y = s.nextInt();
            yourTurnSend.sendData(y);
            
            System.out.print("駒を移動させるx座標を入力してください:");
			int a = s.nextInt();
            yourTurnSend.sendData(a);
            
            System.out.print("駒を移動させるy座標を入力してください:");
			int b = s.nextInt();
            yourTurnSend.sendData(b);
            

			Board.yourTurnSet(x, y, a, b);
            
           
    
            }else{
            System.out.println("相手のターンです");
            
            ChessServer.markServer();
            int x = ChessServer.intData;
            
            ChessServer.markServer();
            int y = ChessServer.intData;
            
            ChessServer.markServer();
            int a = ChessServer.intData;
            
            ChessServer.markServer();
            int b = ChessServer.intData;
            
            Board.yourTurnSet(x, y, a, b);
            
            
        }
    }

  }
	
}