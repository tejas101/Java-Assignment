/* BattleShipClient.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program is the implementation of BattleShip game based on 
 *TCP-IP Protocol
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
public class BattleShipClient {
	static String[][] board = new String[10][10];
	static String[][] oppBoard = new String[10][10];
	static ObjectInputStream ois = null;
	static ObjectOutputStream oos = null;
	static String oppName="";
	static int myHit=0, oppHit=0;

/**
 * Send a user name and accept a username from opponent
 * @throws IOException
 * @throws ClassNotFoundException
 */
	void initializeGame() throws IOException, ClassNotFoundException {
		Socket s = new Socket("localhost", 60010);
		oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject("A");
		oos.flush();
		
		ois = new ObjectInputStream(s.getInputStream());
		oppName= (String)ois.readObject();
		System.out.println("opp name is "+oppName);
		ois.close();
		oos.close();
		s.close();
	}
	/**
	 * Print the current user's board.
	 */
	private void showBoard() {
		// TODO Auto-generated method stub
		System.out.println("Opp borad");	
		for(int i=0;i<oppBoard.length;i++) {
			for(int j=0;j<oppBoard.length;j++) {
				System.out.print(oppBoard[i][j]+"    ");
			}
			System.out.println();
		}
	}
/**
 * Place the ships on the board.
 */
	private void initalizeShip() {
		// TODO Auto-generated method stub
		myHit=0; 
		oppHit=0;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				board[i][j]="_";
				oppBoard[i][j]="_";
			}

		}
//Random number generation logic for placing ships
		Random random = new Random();
		//random.nextInt(max - min + 1) + min
		int rand =random.nextInt(6 - 1 + 1) + 1;//Random number between 1 and 6
		board[rand][rand-1]="S";
		board[rand][rand]="S";

		while(true) {
			int rand2 =random.nextInt(6 - 1 + 1) + 1;//Random number between 1 and 6
			if(rand==rand2) {
				continue;
			}
			else {
				board[rand2][rand2-1]="S";
				board[rand2][rand2]="S";
				board[rand2][rand2+1]="S";
				board[rand2][rand2+2]="S";
				break;
			}
		}
		System.out.println("My borad");
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	private String takeInput(Socket s) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the 1st X and and then Y cordinate ");
		String send=sc.nextLine();
		return send;
	}
	/**
	 * Check the status of the hit/miss
	 * 
	 */
	private String checkBoard(String caseString,int x ,int y, Socket s) throws IOException {
		String sendThis="";
		switch(caseString) {
		case "_":{ 
			board[x][y]="0";
			sendThis="0";
			System.out.println("Attack on new place went waste");
			break;
		}
		case "0":{
			sendThis="0";
			System.out.println("Attack on same place went waste");
			break;
		}
		case "X":{
			sendThis="0";
			System.out.println("Attack on same palce went waste");

			break;
		}

		case "S":{ 
			board[x][y]="X";

			oppHit++;
			if(oppHit==6) {
				sendThis="-1,-1";
				System.out.println("Server wins");
				this.initalizeShip();
			}
			else {
				sendThis="1";
				System.out.println("Attack on my ship");
			}

			break;

		}
		}
		String send=sendThis;
		return send;
	}
	/**
	 * 
	 * Start the game by connecting to server.
	 */
	private void startGame() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		while(true)	 
		{   
			Scanner sc= new Scanner(System.in);
			Socket s = new Socket("localhost", 60010);
			oos = new ObjectOutputStream(s.getOutputStream());

			System.out.println("Enter   X and and then Y cordinate ");
			String send=sc.nextLine();
			String sendArr[]=send.split(",",2);
			int x=Integer.parseInt(sendArr[0]);
			int y=Integer.parseInt(sendArr[1]);
			//Client sending XY cords to server
			oos.writeObject(send);
			oos.flush();
            //accepting the hit/miss response
			ois = new ObjectInputStream(s.getInputStream());
			String receive= (String)ois.readObject();
			if(receive.length()==5) {
				System.out.println("Clinet wins");
				this.initalizeShip();
			}
			else{
				int caseGot=Integer.parseInt(""+receive.charAt(0));
				switch(caseGot) {

				case 1:{
					oppBoard[x][y]="X";
					myHit++;
					break;

				}

				case 0:{
					if(oppBoard[x][y].equals("_")) {
						oppBoard[x][y]="O";}
					break;
				}
				}
			}
			this.showBoard();
			//Giving controll back to server for making his move
			oos.writeObject("take");
			oos.flush();
			ois = new ObjectInputStream(s.getInputStream());
			receive= (String)ois.readObject();
			//receive XY cord from Server and then check board 
			int xOpp=Integer.parseInt(""+receive.charAt(0));
			int yOpp=Integer.parseInt(""+receive.charAt(1));
			String caseString=board[xOpp][yOpp];
			String sendNow=this.checkBoard(caseString,xOpp,yOpp,s);
			oos.writeObject(sendNow);
			oos.flush();
		}
	}
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		BattleShipClient bsc = new BattleShipClient();
		bsc.initializeGame();
		//bsc.acceptName();
		bsc.initalizeShip();
		System.out.println("Game started. You(A/Client) makes the 1st move");
		//bsc.showBoard();
		bsc.startGame();
	}



}
