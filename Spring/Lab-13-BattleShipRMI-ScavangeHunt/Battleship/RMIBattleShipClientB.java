/* RMIBattleShipClientB.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *Program implements BattleShip game with RMI
 *protocol.
 *This file is the clinetB which is one of the player
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

interface GameInterface extends java.rmi.Remote {
	boolean registerClinet(GameInterface ci) throws RemoteException;
	void sendMessageToClient(String message) throws RemoteException;
	void sendMatrixToServer(String[][] board, int i)throws RemoteException;
	String takeIP()throws RemoteException;
	void refershBoard(int x, int y, int z)throws RemoteException;
}
public class RMIBattleShipClientB extends UnicastRemoteObject implements GameInterface  {
	private static final long serialVersionUID = 1L;
	private GameInterface server;

	static String[][] board = new String[10][10];
	static String[][] oppBoard = new String[10][10];
	static int myHit=0, oppHit=0;
	static int x=-1,y=-1;

	protected RMIBattleShipClientB(GameInterface gameInterface) throws RemoteException {
		this.server = gameInterface;
		server.registerClinet(this);
		System.out.println("B Successfully Connected To RMI Server");
		System.out.println("Client A will start the game. Your turn will be later.");
		server.sendMatrixToServer(board, 1);

	}

	public RMIBattleShipClientB() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
	public void sendMatrixToServer(String[][] mat, int who) {
		// TODO Auto-generated method stub

	}
	/**
	 * Method used for communication of the message.
	 */
	public void sendMessageToClient(String message) throws RemoteException {
		/* replyMessage=message;*/

		if(message.startsWith("R")){
			this.updateBoard(message);

		}
		//System.out.println(message); 
	}
	private void showOppBoard() {
		// TODO Auto-generated method stub
		System.out.println("Opp borad");
		for(int i=0;i<oppBoard.length;i++) {
			for(int j=0;j<oppBoard.length;j++) {
				System.out.print(oppBoard[i][j]+" ");
			}
			System.out.println();
		}
	}
	private void showMyBoard() {
		// TODO Auto-generated method stub
		System.out.println("MY borad");
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	/**
	 * Update board based on the hit/miss
	 */
	private void updateBoard(String message) throws RemoteException {
		// TODO Auto-generated method stub
		switch(""+message.charAt(1)) {

		case "0":{ 
			if(!oppBoard[x][y].equals("X")) {
				oppBoard[x][y]="0";}
			//sendThis="0";
			System.out.println("Attack went waste");
			break;
		}
		case "1":{ 
			//sendThis="0";
			oppBoard[x][y]="X";
			System.out.println("Attack on target. Good");
			break;
		}
		case "7":{ 
			System.out.println("You win!! Now, New game.");
			this.initalizeShip();
			server.sendMatrixToServer(board, 1);

			break;
		}
		}

		this.showOppBoard();
	}
	/**
	 * Method used to refresh board based on opponents move.
	 */
	@Override
	public void refershBoard(int x, int y, int z) throws RemoteException {
		// TODO Auto-generated method stub
		if(z==7) {
			System.out.println("You loose. Now, new game.");
			this.initalizeShip();
			server.sendMatrixToServer(board, 1);
		}else {
			if(z==1)
				board[x][y]="X";
			if(z==0)
				board[x][y]="O";
			this.showMyBoard();
		}

	}

	public boolean registerClinet(GameInterface chatinterface) throws RemoteException {
		return true;
	}

	private void initalizeShip()  {
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
	/**
	 * Method used by server to instruct client for taking IP
	 */
	public  String takeIP() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter   X and and then Y cordinate ");
		String send=sc.nextLine();
		String sendArr[]=send.split(",",2);
		x=Integer.parseInt(sendArr[0]);
		y=Integer.parseInt(sendArr[1]);
		return ""+x+y;
	}

	public static void main(String[] args) throws MalformedURLException,RemoteException,NotBoundException {
		Scanner scanner = new Scanner(System.in);
		RMIBattleShipClientB BSB= new RMIBattleShipClientB();
		BSB.initalizeShip();

		GameInterface gameinterface = (GameInterface)Naming.lookup("rmi://localhost/RMIServer");
		new RMIBattleShipClientB(gameinterface);
		System.out.println("B Successfully Connected To RMI Server");
		System.out.println("Client A will start the game");
	}
}