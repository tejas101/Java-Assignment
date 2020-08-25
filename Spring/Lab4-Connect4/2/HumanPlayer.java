/* HumanPlayer.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This code handles the information related to a palyer of the
 *connect four game.
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.util.Scanner;

public class HumanPlayer implements ConnectFourPlayerInterface {
	String name="";
	int playerNumber=0;
	char gamePiece;
	int wins=0;
	//static int counter=0;
	Scanner sc= new Scanner(System.in);
	
	
	HumanPlayer(String name){
		this.name=name;
		System.out.println(name+"  select a single char game piece");
		this.gamePiece=sc.next().charAt(0);
		this.playerNumber=1;
		 
	}
	HumanPlayer(){
		name="Group-7";
		System.out.println(name+"  select a single char game piece");
		this.gamePiece=sc.next().charAt(0);
		this.playerNumber=2;
		 
	}
	@Override
	public int takeTurn() {
		// TODO Auto-generated method stub
		int temp=sc.nextInt();
		sc.nextLine();
		return temp;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	@Override
	public int getNumberOfWins() {
		// TODO Auto-generated method stub
		return this.wins;
	}
	@Override
	public void addWin() {
		// TODO Auto-generated method stub
		this.wins+=1;
		
	}
	@Override
	public char getGamePiece() {
		// TODO Auto-generated method stub
		return this.gamePiece;
	}
	@Override
	public void setGamePiece(char gamePiece) {
		// TODO Auto-generated method stub
		this.gamePiece=gamePiece;
		
	}
	@Override
	public void setPlayerNumber(int num) {
		// TODO Auto-generated method stub
		this.playerNumber=num;
		
	}
	@Override
	public int getPlayerNumber() {
		// TODO Auto-generated method stub
		return playerNumber;
		
	}
	
	public String getRegex() {
		// TODO Auto-generated method stub
		return ""+this.gamePiece+this.gamePiece+this.gamePiece+this.gamePiece;
	}
	
	
	

}
