import java.util.*; 

public class TicTAcToe{
	public static void main(String[] args) {
		System.out.println("Welcome to TicTacToe!\nThis is our gameboard:");
		System.out.println();
		String[][] board = {{"1","2","3"},
				 		{"4","5","6"},
				 		{"7","8","9"}};
		boolean[][] cboard = {{true,true,true},
				 			  {true,true,true},
				 			  {true,true,true}};
		printBoard(board);
		play(board,cboard);
	}

	public static void play(String[][]board, boolean[][] cboard){ //very rough work
		Scanner console = new Scanner(System.in);
		int i =0;
		while(i<9){
		System.out.println("You are playing as X.\nChoose the number in the box that you want to place an X in.");
		int place = console.nextInt();
		board = placeX(board,place);
		cboard = cplaceX(cboard,place);
		printBoard(board);
		minimax(board,cboard,false);
		// MINvalue(board,cboard);
		printBoard(board);
		i+=2;
		System.out.println("Is the game over?\n" + terminal(board,cboard));
		}
	}

	public static int minimax(String[][] current_board, boolean cboard[][], boolean maxplayer){
		if(terminal(current_board,cboard)){
			return utility(current_board);
		}
		if(maxplayer){
			int v = Integer.MIN_VALUE;
			ArrayList<String> actions = actions(current_board, cboard);
			for (String s: actions){
				//printBoard(current_board);
				v = max(v,minimax(placeX(current_board,Integer.parseInt(s)),cplaceX(cboard,Integer.parseInt(s)),false));
			}
			return v;
		} else{
			int v = Integer.MAX_VALUE;
			ArrayList<String> actions = actions(current_board, cboard);
			for (String s: actions){
				//printBoard(current_board);
				v = min(v,minimax(result(current_board,s),cresult(cboard,s),true));
			}
			return v;
		}
	}

	public static int MINvalue(String[][] current_board, boolean cboard[][]){
		if(terminal(current_board,cboard)){
			return utility(current_board);
		}
		int v = Integer.MAX_VALUE;
		ArrayList<String> actions = actions(current_board, cboard);
			for (String s: actions){
				//printBoard(current_board);
				v = min(v,MAXvalue(result(current_board,s),cresult(cboard,s)));
			}
		return v;
	}

	public static int MAXvalue(String[][] current_board, boolean cboard[][]){
		if(terminal(current_board,cboard)){
			return utility(current_board);
		}
		int v = Integer.MIN_VALUE;
		ArrayList<String> actions = actions(current_board, cboard);
			for (String s: actions){
				//printBoard(current_board);
				v = max(v,MINvalue(placeX(current_board,Integer.parseInt(s)),cplaceX(cboard,Integer.parseInt(s))));
			}
		return v;
	}
 
	public static String[][] result(String[][]current_board, String place){
		switch(place){
			case "1": 
				current_board[0][0] = "o"; 
				break;
			case "2": 
				current_board[0][1] = "o"; 
				break;
			case "3": 
				current_board[0][2] = "o"; 
				break;
			case "4": 
				current_board[1][0] = "o";
				break; 
			case "5": 
				current_board[1][1] = "o"; 
				break;
			case "6": 
				current_board[1][2] = "o";
				break;
			case "7": 
				current_board[2][0] = "o";
				break; 
			case "8": 
				current_board[2][1] = "o"; 
				break;
			case "9": 
				current_board[2][2] = "o";
				break;
		}

		return current_board;
	}

	public static boolean[][] cresult(boolean[][] cboard, String place){
		switch(place){
			case "1": 
				cboard[0][0] = false; 
				break;
			case "2": 
				cboard[0][1] = false; 
				break;
			case "3": 
				cboard[0][2] = false; 
				break;
			case "4": 
				cboard[1][0] = false; 
				break; 
			case "5": 
				cboard[1][1] = false; 
				break;
			case "6": 
				cboard[1][2] = false;  
				break;
			case "7": 
				cboard[2][0] = false; 
				break; 
			case "8": 
				cboard[2][1] = false; 
				break;
			case "9": 
				cboard[2][2] = false; 
				break;
		}

		return cboard;
	}

	public static ArrayList<String> actions(String[][] board, boolean[][] cboard){
		ArrayList<String> actions = new ArrayList<String>(); 
		for(int i = 0; i < cboard[0].length; i++){
			for(int j = 0; j <cboard.length; j++){
				if(cboard[i][j] == true){
					actions.add(board[i][j]);
				}
			}
		}
		System.out.println(actions);
		return actions; 
	}

	public static int utility(String[][] current_board){
		if(didXWin(current_board) && !didOWin(current_board)){
			return Integer.MAX_VALUE; 
		}
		if(!didXWin(current_board) && didOWin(current_board)){
			return Integer.MIN_VALUE; 
		}
		if(!didXWin(current_board) && !didOWin(current_board)){
			return 0;
		}
		return 0;
	}

	public static boolean didXWin(String[][] current_board){
		//test all rows 
			for(int i = 0; i < current_board[0].length; i++){
				int win = 0; 
				for(int j = 0; j <current_board.length; j++){
					if(current_board[i][j] == "x"){
						win++;
					}
				}
				if (win==3){
					return true; 
				}
			}
		//test all columns
			for(int j = 0; j < current_board.length; j++){
				int win = 0; 
				for(int i = 0; i <current_board[0].length; i++){
					if(current_board[i][j] == "x"){
						win++;
					}
				}
				if (win==3){
					return true; 
				}
			}
		//test diagonally 
			if(current_board[0][0] == "x" && current_board[1][1] == "x" && current_board[2][2] == "x"){
				return true;
			}
			if(current_board[0][2] == "x" && current_board[1][1] == "x" && current_board[2][0] == "x"){
				return true;
			}
		return false; 
	}

	public static boolean didOWin(String[][] current_board){
		//test all rows 
			for(int i = 0; i < current_board[0].length; i++){
				int win = 0; 
				for(int j = 0; j <current_board.length; j++){
					if(current_board[i][j] == "o"){
						win++;
					}
				}
				if (win==3){
					return true; 
				}
			}
		//test all columns
			for(int j = 0; j < current_board.length; j++){
				int win = 0; 
				for(int i = 0; i <current_board[0].length; i++){
					if(current_board[i][j] == "o"){
						win++;
					}
				}
				if (win==3){
					return true; 
				}
			}
		//test diagonally 
			if(current_board[0][0] == "o" && current_board[1][1] == "o" && current_board[2][2] == "o"){
				return true;
			}
			if(current_board[0][2] == "o" && current_board[1][1] == "o" && current_board[2][0] == "o"){
				return true;
			}
		return false;
	}

	public static boolean terminal(String[][] current_board, boolean cboard[][]){
		boolean isTerminal = false; 
		//Test: did x win
		if(didXWin(current_board)){
			return true;
		}
		//Test: did o win
		if(didOWin(current_board)){
			return true;
		}
			
		isTerminal = true; 
		//Test: is board filled
		for(int i = 0; i < cboard[0].length; i++){
			for(int j = 0; j <cboard.length; j++){
				if(cboard[i][j] == true){
					isTerminal = false; 
				}
			}
		}
		return isTerminal;
	}

	public static String[][] placeX(String[][] current_board, int place){
		switch(place){
			case 1: 
				current_board[0][0] = "x"; 
				break;
			case 2: 
				current_board[0][1] = "x"; 
				break;
			case 3: 
				current_board[0][2] = "x"; 
				break;
			case 4: 
				current_board[1][0] = "x";
				break; 
			case 5: 
				current_board[1][1] = "x"; 
				break;
			case 6: 
				current_board[1][2] = "x";
				break;
			case 7: 
				current_board[2][0] = "x";
				break; 
			case 8: 
				current_board[2][1] = "x"; 
				break;
			case 9: 
				current_board[2][2] = "x";
				break;
		}

		return current_board;
	}

	public static boolean[][] cplaceX(boolean[][] cboard, int place){
		switch(place){
			case 1: 
				cboard[0][0] = false; 
				break;
			case 2: 
				cboard[0][1] = false; 
				break;
			case 3: 
				cboard[0][2] = false; 
				break;
			case 4: 
				cboard[1][0] = false; 
				break; 
			case 5: 
				cboard[1][1] = false; 
				break;
			case 6: 
				cboard[1][2] = false;  
				break;
			case 7: 
				cboard[2][0] = false; 
				break; 
			case 8: 
				cboard[2][1] = false; 
				break;
			case 9: 
				cboard[2][2] = false; 
				break;
		}

		return cboard;
	}

	public static int min(int a, int b){
		if(a > b){
			return b;
		}
		return a;
	}

	public static int max(int a, int b){
		if(a > b){
			return a;
		}
		return b;
	}

	public static void printBoard(String[][] board){
		for(int i = 0; i < board[0].length; i++){
			System.out.print(board[i][0]);
			for(int j = 1; j <board.length; j++){
				System.out.print(" | " + board[i][j]);
			}
			System.out.println();
			if(i<2){
				System.out.println("--+---+--");
			}
		}
	}
}