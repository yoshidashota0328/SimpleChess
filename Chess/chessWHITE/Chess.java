import java.util.Scanner;

public class Chess{
    
    //あなたの色は白です
	static final String yourColor = "○";
	static public int resetInt = 0;
	public static void main(String [] args){
		
		Board.initialize();//チェス版の要素を全てクリアする
    	Board.showBoard();//現在のボードを表示
            	   	
        Scanner s = new Scanner(System.in);
    
        //ゲーム実行中フラグがtrueのあいだループする
        while(Board.game){
        resetInt = 0;
           outside: if(Board.turn.equals(yourColor)){
                                               
                System.out.print("動かしたい駒のx座標を入力してください:");
    			int x = s.nextInt();
                reset(x);
               
                yourTurnSend.sendData(x);
                if(resetInt == 1){
                    break outside;
                }
                            
                System.out.print("動かしたい駒のy座標を入力してください:");
    			int y = s.nextInt();
                reset(y);
                
                yourTurnSend.sendData(y);
                if(resetInt == 1){
                    break outside;
                }
                
                System.out.print("駒を移動させるx座標を入力してください:");
    			int a = s.nextInt();
                reset(a);
              
                yourTurnSend.sendData(a);
                if(resetInt == 1){
                    break outside;
                }
                
                System.out.print("駒を移動させるy座標を入力してください:");
    			int b = s.nextInt();
                reset(b);
                
                    yourTurnSend.sendData(b);
                if(resetInt == 1){
                    break outside;
                }  
                
                if(resetInt == 0){      
    			    Board.yourTurnSet(x, y, a, b);//指定位置にコマを移動させる
    			}
                                
            }else{               
                                   
                System.out.println("相手のターンです。");
                
                ChessServer.markServer();
                int x = ChessServer.intData;
                reset(x);
                if(resetInt == 1){
                    break outside;
                }
                
                ChessServer.markServer();
                int y = ChessServer.intData;
                reset(y);
                if(resetInt == 1){
                    break outside;
                }
                
                ChessServer.markServer();
                int a = ChessServer.intData;
                reset(a);
                if(resetInt == 1){
                    break outside;
                }
                
                ChessServer.markServer();
                int b = ChessServer.intData;
                reset(b);
                if(resetInt == 1){
                    break outside;
                }
                
                if(resetInt == 0){  
                    Board.yourTurnSet(x, y, a, b);
                }
            }                             
        }
    } 
    static public void reset(int i){//8が入力された時、入力をやり直す
        if(i == 8){
            resetInt = 1;
            Board.showBoard();
        }  
    }  	
}
