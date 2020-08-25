import java.util.Scanner;

public class ComputerPlayer implements ConnectFourPlayerInterface {
	String name="";
	int playerNumber=0;
	char gamePiece;
	int wins=0;
	static int counter=0;
	Scanner sc= new Scanner(System.in);
	
	
	 
	ComputerPlayer(){
		name="Computer-Jack";
		System.out.println(name+"  select a single char game piece");
		this.gamePiece=sc.next().charAt(0);
		this.playerNumber=2;
		counter++;
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
	
	public int getPlayerNumber() {
		// TODO Auto-generated method stub
		return playerNumber;
		
	}
	public String getRegex() {
		// TODO Auto-generated method stub
		return ""+this.gamePiece+this.gamePiece+this.gamePiece+this.gamePiece;
	}
	
	

}
