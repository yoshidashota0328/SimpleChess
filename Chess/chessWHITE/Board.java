public class Board{

	//ゲーム実行中フラグ
	static boolean game = true;
		
	//チェス版に対応した多次元配列
	static String[][] board = new String [8][8];
	
	static final String EMPTY = "　";
	static final String BLACK = "●";
	static final String WHITE = "○";
	static final String KING = "K";
	static final String QUEEN = "Q";
	static final String ROOK = "R";
	static final String BISHOP = "B";
	static final String KNIGHT ="N";//キングとイニシャルがかぶるためNに変更
	static final String PAWN = "P";
	static public int[] bk ={0,3};//黒のキングの現在位置
	static public int[] wk ={7,3};//白のキングの現在位置
	static public int check = 0;//チェック判定　1ならばチェックされています。
	static String turn;//現在のターン
	static String nex_turn;//次のターン
	
	static public void initialize(){//チェス版の要素を全てクリアする
	
		for(int i = 0 ; i < 8; i++){
			
			for (int j = 0 ; j < 8 ; j++){
				
				board[i][j] = EMPTY;
			}
		} 
		
		//初期配置 BLACK
		board[0][0] = BLACK + ROOK ;
		board[0][7] = BLACK + ROOK;
		board[0][1] = BLACK + KNIGHT;
		board[0][6] = BLACK + KNIGHT;
		board[0][2] = BLACK + BISHOP;
		board[0][5] = BLACK + BISHOP;
		board[0][3] = BLACK + KING;
		board[0][4] = BLACK + QUEEN;
		board[1][0] = BLACK + PAWN;
		board[1][1] = BLACK + PAWN;
		board[1][2] = BLACK + PAWN;
		board[1][3] = BLACK + PAWN;
		board[1][4] = BLACK + PAWN;
		board[1][5] = BLACK + PAWN;
		board[1][6] = BLACK + PAWN;
		board[1][7] = BLACK + PAWN;
		
		//初期配置 WHITE
		board[7][0] = WHITE + ROOK ;
		board[7][7] = WHITE + ROOK;
		board[7][1] = WHITE + KNIGHT;
		board[7][6] = WHITE + KNIGHT;
		board[7][2] = WHITE + BISHOP;
		board[7][5] = WHITE + BISHOP;
		board[7][3] = WHITE + KING;
		board[7][4] = WHITE + QUEEN;
		board[6][0] = WHITE + PAWN;
		board[6][1] = WHITE + PAWN;
		board[6][2] = WHITE + PAWN;
		board[6][3] = WHITE + PAWN;
		board[6][4] = WHITE + PAWN;
		board[6][5] = WHITE + PAWN;
		board[6][6] = WHITE + PAWN;
		board[6][7] = WHITE + PAWN;
		
		//次のユーザーの色を指定
		turn = BLACK;
		nex_turn = WHITE;
		
		//ゲーム実行中フラグ
		game = true;
		
	}
	static public void showBoard(){//チェス版を描画する
	
		int i = 0;
		System.out.println("  |0 |1 |2 |3 |4 |5 |6 |7 |");
		System.out.println("___________________________");
		for (String [] sarr : board){
			
			System.out.print(i + " |");
			for(String s : sarr){
				
			System.out.print(s + "|");
				
		}	
			System.out.println();
			System.out.println("___________________________");
				
			i++;
		}
		
			System.out.println(turn + "のターンです");	
			if(check == 1){
				System.out.println("チェック！");	
			}
	}
	static public void yourTurnSet( int x,int y,int a,int b){//指定位置にコマを移動させる
		check = 0;
		//ボード外の座標を指定した場合の例外処理
		try{
			//ポーン
			if(board[y][x].equals(turn + PAWN)){
				pawnAction(x , y , a , b);
				
				turnChange(x,y);//白と黒のターン切り替え
				showBoard();

			//ルーク
			}else if(board[y][x].equals(turn + ROOK)){
				rookAction(x , y , a , b);
			
				turnChange(x,y);
				showBoard();	
				
				
				//ナイト
			}else if(board[y][x].equals(turn + KNIGHT)){
				knightAction(x , y , a , b);
				
				turnChange(x,y);
				showBoard();
				
				//ビショップ
			}else if(board[y][x].equals(turn + BISHOP)){
				bishopAction(x , y , a , b);
				
				turnChange(x,y);
				showBoard();
				
				
				//キング
			}else if(board[y][x].equals(turn + KING)){
				kingAction(x , y , a , b);
				
				turnChange(x,y);
				showBoard();
				
				//クイーン
			}else if(board[y][x].equals(turn + QUEEN)){
				queenAction(x , y , a , b);
				
				turnChange(x,y);
				showBoard();
				
			}else{
				System.out.println("不正な操作です");
				showBoard();
			}//KINGが取られた時にゲームを終了する
			if(turn == WHITE && !(board[wk[0]][wk[1]].equals(WHITE + KING))){
				System.out.println("ゲーム終了" +nex_turn +"の勝ち！");
			}else if(turn == BLACK && !(board[bk[0]][bk[1]].equals(BLACK + KING))){
				System.out.println("ゲーム終了" +nex_turn +"の勝ち！");
			}
			
			
		}catch( ArrayIndexOutOfBoundsException e){
			System.out.println("配列外");
			showBoard();
			
		}
	}
	//ポーンアクション
	static public void pawnAction(int x,int y,int a,int b){
		if(turn == BLACK){//黒のターンならば
			if (x > 0){
			
				if(board[y + 1][x - 1 ].contains(WHITE) && y + 1 == b && x - 1 == a){
					board[b][a] = BLACK + PAWN;
					board[y][x] = EMPTY;
				}
			
			}if(x < 7){
				 if(board[y + 1][x + 1 ].contains(WHITE) && y + 1 == b && x + 1 == a){
					board[b][a] = BLACK + PAWN;
					board[y][x] = EMPTY;
				}
			}
			if(y + 1 == b && x == a && board[b][a].equals(EMPTY)){
				board[b][a] = BLACK + PAWN;
				board[y][x] = EMPTY;
			}else if(y + 2 == b && x == a && y == 1&& board[b][a].equals(EMPTY)){
				board[b][a] = BLACK + PAWN;
				board[y][x] = EMPTY;
			}
			if (b < 7 && a > 0){//チェック判定
			
				if(board[b + 1][a - 1 ].equals(WHITE + KING)){
					check = 1;
				}
			
			}
			if(b < 7 && a < 7){
				 if(board[b + 1][a + 1 ].equals(WHITE + KING)){
					check = 1;
				}
			}
			if(b == 7){//昇格
				board[b][a] = BLACK + QUEEN;
			}
			
		}
		if(turn == WHITE){//白のターンならば
			if(x > 0){
				if(board[y - 1][x - 1 ].contains(BLACK) && y - 1 == b && x - 1 == a){
					board[b][a] = WHITE + PAWN;
					board[y][x] = EMPTY;
				}
			}if(x < 7){
				 if(board[y - 1][x + 1 ].contains(BLACK) && y - 1 == b && x + 1 == a){
					board[b][a] = WHITE + PAWN;
					board[y][x] = EMPTY;
				}
			}
			if(y - 1 == b && x == a && board[b][a].equals(EMPTY)){
				board[b][a] = WHITE + PAWN;
				board[y][x] = EMPTY;
			}else if(y - 2 == b && x == a && y == 6 && board[b][a].equals(EMPTY)){
				board[b][a] = WHITE + PAWN;
				board[y][x] = EMPTY;
			}
			
			if (b > 0 && a > 0){//チェック判定
			
				if(board[b - 1][a - 1 ].equals(BLACK + KING)){
					check = 1;
				}
			
			}if(b > 0 && a < 7){
				 if(board[b - 1][a + 1 ].equals(BLACK + KING)){
					check = 1;
				}
			}
			if(b == 0){//昇格
				board[b][a] = WHITE + QUEEN;
			}			
		}		
	}
	//ルークアクション
	static public void rookAction(int x,int y,int a,int b){
		if(turn == BLACK){//黒のターン
			if(x == a && b - y > 0 && board[b][a].contains(BLACK) == false){//下方向
				for(int i = 1; i <= b - y;i++){
					if(y + i == b){
						board[b][a] = BLACK + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y + i][x].equals(EMPTY)){
						continue;
						
					}
				}
			}
			if(x == a && y - b > 0 && board[b][a].contains(BLACK) == false){//上方向
				for(int i = 1; i <= y - b;i++){
					if(y - i == b){
						board[b][a] = BLACK + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y - i][x].equals(EMPTY)){
						continue;
						
					}
				}
			}
			if(y == b && a - x > 0 && board[b][a].contains(BLACK) == false){//右方向
				for(int i = 1; i <= a - x;i++){
					if(x + i == a){
						board[b][a] = BLACK + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y][x + i].equals(EMPTY)){
						continue;
						
					}
				}
			}
			if(y == b && x - a > 0 && board[b][a].contains(BLACK) == false){//左方向
				for(int i = 1; i <= x - a;i++){
					if(x - i == a){
						board[b][a] = BLACK + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y][x - i].equals(EMPTY)){
						continue;
						
					}
				}
			}//チェック判定
					
			for (int q = 1; q <= 7 ;q++){
				if( b + q <= 7){//下
					if(board[b + q][a].equals(WHITE + KING) ){
						check = 1;
						break;
					}else if(!(board[b + q][a].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b - q >= 0){//上
					if(board[b - q][a].equals(WHITE + KING) ){
						check = 1;
						break;
					}else if(!(board[b - q][a].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( a + q <= 7 ){//右
					if(board[b][a + q].equals(WHITE + KING) ){
						check = 1;
						break;
					}else if(!(board[b][a + q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( a - q >= 0 ){//左
					if(board[b][a - q].equals(WHITE + KING) ){
						check = 1;
						break;
					}else if(!(board[b][a - q].equals(EMPTY))){
						break;
					}
				}
			}
		}
		if(turn == WHITE){//白のターン
			if(x == a && b - y > 0 && board[b][a].contains(WHITE) == false){//下方向
				for(int i = 1; i <= b - y;i++){
					if(y + i == b){
						board[b][a] = WHITE + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y + i][x].equals(EMPTY)){
						continue;
						
					}
				}
			}
			if(x == a && y - b > 0 && board[b][a].contains(WHITE) == false){//上方向
				for(int i = 1; i <= y - b;i++){
					if(y - i == b){
						board[b][a] = WHITE + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y - i][x].equals(EMPTY)){
						continue;
						
					}
				}
			}
			if(y == b && a - x > 0 && board[b][a].contains(WHITE) == false){//右方向
				for(int i = 1; i <= a - x;i++){
					if(x + i == a){
						board[b][a] = WHITE + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y][x + i].equals(EMPTY)){
						continue;
						
					}
				}
			}
			if(y == b && x - a > 0 && board[b][a].contains(WHITE) == false){//左方向
				for(int i = 1; i <= x - a;i++){
					if(x - i == a){
						board[b][a] = WHITE + ROOK;
						board[y][x] = EMPTY;
						
					}else if(board[y][x - i].equals(EMPTY)){
						continue;
						
					}
				}
			}
					
			//チェック判定
			for (int q = 1;q <= 7;q++){
				if( b + q <= 7){//下
					if(board[b + q][a].equals(BLACK + KING) ){
						check = 1;
						break;
					}else if(!(board[b + q][a].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b - q >= 0){//上
					if(board[b - q][a].equals(BLACK + KING) ){
						check = 1;
						break;
					}else if(!(board[b - q][a].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( a + q <= 7 ){//右
					if(board[b][a + q].equals(BLACK + KING) ){
						check = 1;
						break;
					}else if(!(board[b][a + q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( a - q >= 0 ){//左
					if(board[b][a - q].equals(BLACK + KING) ){
						check = 1;
						break;
					}else if(!(board[b][a - q].equals(EMPTY))){
						break;
					}
				}
			}
		}		
	}
	//ナイトアクション
	static public void knightAction(int x,int y,int a,int b){
		if(turn == BLACK){//黒のターン
			//上
			if(y > 1 && y - 2 == b){
				if((x - 1 == a || x + 1 == a) && board[b][a].contains(BLACK) == false){
					board[b][a] = BLACK + KNIGHT;
					board[y][x] = EMPTY;
				}//下
			}else if(y < 6 && y + 2 == b){
				if((x - 1 == a || x + 1 == a) && board[b][a].contains(BLACK) == false){
					board[b][a] = BLACK + KNIGHT;
					board[y][x] = EMPTY;
				}//左
			}else if(x > 1 && x - 2 == a){
				if((y - 1 == b || y + 1 == b) && board[b][a].contains(BLACK) == false){
					board[b][a] = BLACK + KNIGHT;
					board[y][x] = EMPTY;
				}//右
			}else if(x < 6 && x + 2 == a){
				if((y - 1 == b || y + 1 == b) && board[b][a].contains(BLACK) == false){
					board[b][a] = BLACK + KNIGHT;
					board[y][x] = EMPTY;
				}
			}
			
			//チェック判定
			//上
			if(b > 1){
				if(board[b - 2][a - 1].equals(WHITE + KING) || board[b - 2][a + 1].equals(WHITE + KING)){
					check = 1;
				}//下
			}
			if(b < 6){
				if(board[b + 2][a - 1].equals(WHITE + KING) || board[b + 2][a + 1].equals(WHITE + KING)){
					check = 1;
				}//左
			}
			if(a > 1){
				if(board[b + 1][a - 2].equals(WHITE + KING) || board[b - 1][a - 2].equals(WHITE + KING)){
					check = 1;
				}//右
			}
			if(a < 6){
				if(board[b + 1][a + 2].equals(WHITE + KING) || board[b - 1][a + 2].equals(WHITE + KING)){
					check = 1;
				}
			}
		}
		if(turn == WHITE){//白のターン
			if(y > 1 && y - 2 == b){
				if((x - 1 == a || x + 1 == a) && board[b][a].contains(WHITE) == false){
					board[b][a] = WHITE + KNIGHT;
					board[y][x] = EMPTY;
				}//下
			}else if(y < 6 && y + 2 == b){
				if((x - 1 == a || x + 1 == a) && board[b][a].contains(WHITE) == false){
					board[b][a] = WHITE + KNIGHT;
					board[y][x] = EMPTY;
				}//左
			}else if(x > 1 && x - 2 == a){
				if((y - 1 == b || y + 1 == b) && board[b][a].contains(WHITE) == false){
					board[b][a] = WHITE + KNIGHT;
					board[y][x] = EMPTY;
				}//右
			}else if(x < 6 && x + 2 == a){
				if((y - 1 == b || y + 1 == b) && board[b][a].contains(WHITE) == false){
					board[b][a] = WHITE + KNIGHT;
					board[y][x] = EMPTY;
				}
			}
			
			//チェック判定
			//上
			if(b > 1){
				if(board[b - 2][a - 1].equals(BLACK + KING) || board[b - 2][a + 1].equals(BLACK + KING)){
					check = 1;
				}//下
			}
			if(b < 6){
				if(board[b + 2][a - 1].equals(BLACK + KING) || board[b + 2][a + 1].equals(BLACK + KING)){
					check = 1;
				}//左
			}
			if(a > 1){
				if(board[b + 1][a - 2].equals(BLACK + KING) || board[b - 1][a - 2].equals(BLACK + KING)){
					check = 1;
				}//右
			}
			if(a < 6){
				if(board[b + 1][a + 2].equals(BLACK + KING) || board[b - 1][a + 2].equals(BLACK + KING)){
					check = 1;
				}
			}
		}
	}
	//ビショップアクション
	static public void bishopAction(int x,int y,int a,int b){
		if (turn == BLACK){//黒のターン
	
			int i = y - b;
			int j = x - a; 
			int c = b - y;
			int d = a - x;
			if((i == j || i == d || c == d || c == j )&& board[b][a].contains(BLACK) == false){
				for (int k = 1; k <= i ||k <= c; k++){
					if(k == i && i == j && i > 0){
						board[b][a] = BLACK + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(k == i && i == d && i > 0){
						board[b][a] = BLACK + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(k == c && c == d && c > 0){
						board[b][a] = BLACK + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(k == c && c == j && c > 0){
						board[b][a] = BLACK + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(i == j && y > 0 && x > 0 && i > 0){
						if(!(board[y - k][x - k].equals(EMPTY))){
							System.out.println(k + "左上");
							break;
							
						}
					}else if(i == d && y > 0 && x < 7 && i > 0){
						if(!(board[y - k][x + k].equals(EMPTY))){
							System.out.println(k + "右上");
							break;
						
						}
					}else if(c == d && y < 7 && x < 7 && c > 0){
						if(!(board[y + k][x + k].equals(EMPTY))){
							System.out.println(k + "右下");
							break;
					
						}
					}else if(c == j && y < 7 && x > 0 && c > 0){
						if(!(board[y + k][x - k].equals(EMPTY))){
							System.out.println(k + "左下");
							break;
							
						}
					}else{
						System.out.println(k + "b");
						continue;
						
					}
				}
			}
			
			//チェック判定
			for(int q = 1; q <= 7 ; q++){
				if( b - q >= 0 && a - q >= 0){//左上
					if(board[b - q][a - q].equals(WHITE + KING)){
						check = 1;
						break;
					}else if(!(board[b - q][a - q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b - q >= 0 && a + q <= 7){//右上
					if(board[b - q][a + q].equals(WHITE + KING)){
						check = 1;
						break;
					}else if(!(board[b - q][a + q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b + q <= 7 && a + q <= 7 ){//右下
					if(board[b + q][a + q].equals(WHITE + KING)){
						check = 1;
						break;
					}else if(!(board[b + q][a + q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b + q <= 7 && a - q >= 0 ){//左下
					if(board[b + q][a - q].equals(WHITE + KING)){
						check = 1;
						break;
					}else if(!(board[b + q][a - q].equals(EMPTY))){
						break;
					}
				}
			}
		}
		if (turn == WHITE){//白のターン
	//左上
			int i = y - b;
			int j = x - a; 
			int c = b - y;
			int d = a - x;
			if((i == j || i == d || c == d || c == j )&& board[b][a].contains(WHITE) == false){
				for (int k = 1; k <= i ||k <= c; k++){
					if(k == i && i == j && i > 0){
						board[b][a] = WHITE + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(k == i && i == d && i > 0){
						board[b][a] = WHITE + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(k == c && c == d && c > 0){
						board[b][a] = WHITE + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(k == c && c == j && c > 0){
						board[b][a] = WHITE + BISHOP;
						board[y][x] = EMPTY;
						System.out.println(k + "k");
					}else if(i == j && y > 0 && x > 0 && i > 0){
						if(!(board[y - k][x - k].equals(EMPTY))){
							System.out.println(k + "左上");
							break;
							
						}
					}else if(i == d && y > 0 && x < 7 && i > 0){
						if(!(board[y - k][x + k].equals(EMPTY))){
							System.out.println(k + "右上");
							break;
						
						}
					}else if(c == d && y < 7 && x < 7 && c > 0){
						if(!(board[y + k][x + k].equals(EMPTY))){
							System.out.println(k + "右下");
							break;
					
						}
					}else if(c == j && y < 7 && x > 0 && c > 0){
						if(!(board[y + k][x - k].equals(EMPTY))){
							System.out.println(k + "左下");
							break;
							
						}
					}else{
						System.out.println(k + "b");
						continue;
						
					}
				}
			}
		
			//チェック判定
			for(int q = 1; q <= 7 ; q++){
				if( b - q >= 0 && a - q >= 0){//左上
					if(board[b - q][a - q].equals(BLACK + KING)){
						check = 1;
						break;
					}else if(!(board[b - q][a - q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b - q >= 0 && a + q <= 7){//右上
					if(board[b - q][a + q].equals(BLACK + KING)){
						check = 1;
						break;
					}else if(!(board[b - q][a + q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b + q <= 7 && a + q <= 7 ){//右下
					if(board[b + q][a + q].equals(BLACK + KING)){
						check = 1;
						break;
					}else if(!(board[b + q][a + q].equals(EMPTY))){
						break;
					}
				}
			}
			for (int q = 1;q <= 7;q++){
				if( b + q <= 7 && a - q >= 0 ){//左下
					if(board[b + q][a - q].equals(BLACK + KING)){
						check = 1;
						break;
					}else if(!(board[b + q][a - q].equals(EMPTY))){
						break;
					}
				}
			}
		}
	}
	//キングアクション
	static public void kingAction(int x,int y,int a,int b){
		if(turn == BLACK){//黒のターン
			if(!(board[b][a].contains(BLACK)) && (Math.abs(x - a) <= 1 && Math.abs(y - b) <= 1)){//上
				board[b][a] = BLACK + KING;
				bk[0] = b;
				bk[1] = a;
				board[y][x] = EMPTY;
			}
		}
		if(turn == WHITE){//白のターン
			if(!(board[b][a].contains(WHITE)) && (Math.abs(x - a) <= 1 && Math.abs(y - b) <= 1)){//上
				board[b][a] = WHITE + KING;
				wk[0] = b;
				wk[1] = a;
				board[y][x] = EMPTY;
			}
		}
	}
	//クイーンアクション
	static public void queenAction(int x,int y,int a,int b){
		if(turn == BLACK){//黒のターン
			
				rookAction(x ,y ,a ,b );
				
			
				bishopAction(x ,y ,a ,b );
				board[b][a] = BLACK + QUEEN;
			
		}
		if(turn == WHITE){//白のターン
			
				rookAction(x ,y ,a ,b );
				
				bishopAction(x ,y ,a ,b );
				board[b][a] = WHITE + QUEEN;
			
		}
		
	}
	static public void turnChange(int x, int y){//白と黒のターンの切り替え
		if(board[y][x].equals(EMPTY)){
							String cng_turn = turn;
							turn = nex_turn;
							nex_turn = cng_turn;
		}	
	}
}