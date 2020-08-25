/* ConnectFourGame.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program handles all the functioning of the Connect Four Game .
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectFourGame implements ConnectFourGameInterface{

	ConnectFourPlayerInterface h1;
	ConnectFourPlayerInterface h2;
	static final int CHANCE=42;
	static int chanceCounter=0;
	static final int ROW=7;
	static final int COL=6;
	static String buffer;
	static char board[][]=new char[ROW][COL];
	HumanPlayer[] human;
	boolean doneflag=false;
	Scanner sc= new Scanner(System.in);
	ConnectFourGame(ConnectFourPlayerInterface o1, ConnectFourPlayerInterface o2)
	{
		h1=o1;
		h2=o2;
		human = new HumanPlayer[HumanPlayer.counter];
		human[0]=(HumanPlayer) h1;
		human[1]=(HumanPlayer) h2;
	}
	@Override
	public void getStats() {
		// TODO Auto-generated method stub
		 System.out.println("Wining Stats");
		 for(int i=0;i<human.length;i++) {
			 System.out.println("Player "+human[i].playerNumber+" :"+human[i].name+
					 " has won total "+human[i].getNumberOfWins()+ " game.");
			 
		 }
		 
	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		this.initializeBoard();
		this.start();
		
	}
	 
	void initializeBoard() {
		 System.out.println("Welcome to Connect Four! ");
		for(int i=0;i<ROW;i++) {
			for(int j=0;j<COL;j++) {
				board[i][j]='.';
			} 
		}
		displayBoard();
		
	}
	void displayBoard() {
		  
		for(int i=0;i<ROW;i++) {
			for(int j=0;j<COL;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	private void start() {
		// TODO Auto-generated method stub
		while(true&&(!doneflag)) {
			for(int i=0;i<human.length&&(!doneflag);i++) {
				//System.out.println("I is "+i);
				System.out.println("Player "+human[i].playerNumber+" :"+
				human[i].name+" select a coloum");
				
				int col=human[i].takeTurn();
				col--;
				if(!(0<=col&& col<COL)) {
					System.out.println("You should have entred value "
							+ "between 1 and 6. Chance missed");
					continue;
					
				}
				 int j;
				for( j=ROW-1;j>-1;j--) {
					if(((""+board[j][col]).equals("."))) {
						board[j][col]=human[i].getGamePiece();
						++chanceCounter;
						displayBoard();
						if(horizontalSearch(human[i].getRegex())) {
							System.out.println(human[i].name+" won!!");
						human[i].addWin();
						System.out.println("Want to play again? Reply in y "
								+ "for yes and anything else for no");
						String answer=sc.nextLine();
						if(answer.equals("y")) {
							reInitializedBoard();
						}
						else {
							
							endGame();
						}
						}
						else {
							if(chanceCounter==CHANCE) {
								System.out.println("Its a draw.");
								System.out.println("Want to play again? Reply in y "
										+ "for yes and anything else for no");
								String answer=sc.nextLine();
								if(answer.equals("y")) {
									reInitializedBoard();
								}
								else {endGame();}
							}
						}
						
						break;
						}
					}
				if(j==-1) {
				System.out.println("Coloumn "+(++col)+" is already full. "
						+ "Chance missed");}
			}
		}
		
	}
	 
	   void endGame() {
		 getStats();
		 System.exit(0);
		
	}
	void reInitializedBoard() {
		chanceCounter=0;  
		doneflag=false;
		playGame();
	}
	public boolean horizontalSearch(String data) {
		/***
		 * Here, searching starts here for 
		 * Horizontal direction 
		 */ 
	buffer = "";
		//horizontal search- forward
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				buffer += board[i][j];
			}


			if (checkPattern(buffer, data)) {
				 
				doneflag = true;
				return true;
			}

			 
			buffer = ""; //reset buffer
		}
		return verticalSearch(data);
}

public boolean verticalSearch(String data) {
	    /***
		 * Here, searching starts here for 
		 * Vertical direction 
		 */ 
		//Vertical search
		for (int j = 0; j < COL; j++) {
			for (int i = 0; i < ROW; i++) {
				buffer += board[i][j];
			}
			//vertical-forward
			if (checkPattern(buffer, data)) {
				 
				doneflag = true;
				return true;
			}
			 
			buffer = "";
		}
		return digonal1(data);
}
public boolean digonal1(String data) {
	     /***
		 * Here, searching starts here for 
		 * digonal direction lower triangle. 
		 */
		 //digonal down to up left to right 
		//lower triangle  \ below
		for (int i = COL - 1; i > 0; i--) {

			for (int j = 0, k = i; k <= ROW - 1; j++, k++) {
				buffer = buffer + board[k][j];
			}

			if (checkPattern(buffer, data)) {
				doneflag = true;
				return true;
			}
			buffer = "";
		}
		return digonal2(data);
}
public boolean digonal2(String data) {
	    /***
		 * Here, searching starts here for 
		 * digonal direction upper triangle. 
		 */
		//digonal down to up left to right
		//upper triangle - \above 
		for (int i = 0; i <= ROW - 1; i++) {

			for (int j = 0, k = i; k <= COL - 1; j++, k++) {
				buffer = buffer + board[j][k];

			}

			if (checkPattern(buffer, data)) {
				 
				doneflag = true;
				return true;
			}

			 

			buffer = "";

		}
		return digonal3(data);
}

public boolean digonal3(String data) {
		/***
		 * Here, searching starts here for 
		 * digonal direction upper triangle. 
		 */
		//Digonal right to left
		//upper triangle above / 
		for (int i = ROW - 1; i > -1; i--) {
	    //String buffer="";
	    int j=0;
	    
			for (int k=i; j <i; j++, k--) {
				buffer = buffer + board[k][j];
			}
			if(j<=5)
	    buffer = buffer + board[0][j];
			if (checkPattern(buffer, data)) {
				 
				doneflag = true;
				return true;
			}
			buffer = "";
	}
		return digonal4(data);
}
public boolean digonal4(String data) {
	    /***
		 * Here, searching starts here for 
		 * digonal direction lower triangle. 
		 */
		//Digonal right to left
		//lower triangle
		for (int k = 1; k < ROW; k++) {
			for (int i=ROW-1,j =k;j<COL; j++, i--) {
				buffer = buffer + board[i][j]+"-";
			}
			
			if (checkPattern(buffer, data)) {
				 
				doneflag = true;
				return true;
			}
			buffer = "";
	}
		return false;
}

		public boolean checkPattern(String buffer, String data) {

			Pattern checkRegex = Pattern.compile("^.*(" + data + ").*$");
			Matcher regexMatcher = checkRegex.matcher(buffer);
			boolean b = regexMatcher.find();

			if (b) {
				 
				return true;
			}

			return false;
		}

}
