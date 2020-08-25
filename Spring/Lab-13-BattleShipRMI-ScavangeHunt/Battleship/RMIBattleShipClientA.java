/* RMIBattleShipClientA.java  
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
 *This file is the clinetA which is one of the player
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Scanner;

interface GameInterface extends java.rmi.Remote {
	boolean registerClinet(GameInterface ci) throws RemoteException;
	void sendMessageToClient(String message) throws RemoteException;
	void sendMatrixToServer(String[][] mat, int who)throws RemoteException;
	String takeIP()throws RemoteException;
	void refershBoard(int x, int y, int z)throws RemoteException;
}
public class RMIBattleShipClientA extends UnicastRemoteObject implements GameInterface  {
	private static final long serialVersionUID = 1L;
	private GameInterface server;
	static String[][] board = new String[10][10];
	static String[][] oppBoard = new String[10][10];
	static int myHit=0, oppHit=0;
	static int x=-1,y=-1;

	protected RMIBattleShipClientA(GameInterface chatinterface) throws RemoteException {
		this.server = chatinterface;
		server.registerClinet(this);
		server.sendMatrixToServer(board, 0);
		System.out.println("A Successfully Connected To RMI Server");
		System.out.println("You will start the game after the instructions.");
	}

	public RMIBattleShipClientA() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
/**
 * Method used for communication of the message.
 */
	public void sendMessageToClient(String message) throws RemoteException {

		if(message.startsWith("R")){
			this.updateBoard(message);
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
			System.out.println("Attack on new place, went waste");
			break;
		}
		case "1":{ 
			//sendThis="0";
			oppBoard[x][y]="X";
			System.out.println("Attack on target. Good");
			break;
		}
		case "7":{ 
			System.out.println("You win!! Now, new game.");
			this.initalizeShip();//A wins. Now, rest the game.
			server.sendMatrixToServer(board, 0);
			break;
		}
		}
		this.showOppBoard();

	}

	@Override
	public void sendMatrixToServer(String[][] mat, int who) {
		// TODO Auto-generated method stub		
	}

	public boolean registerClinet(GameInterface chatinterface) throws RemoteException {
		return true;
	}

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
	/**
	 * Method used to refresh board based on opponents move.
	 */
	public void refershBoard(int x, int y, int z) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("In refersh Board of A and Z is "+z);
		if(z==7) {
			System.out.println("You loose. Now, new game.");
			this.initalizeShip();
			server.sendMatrixToServer(board, 0);
		}else {
			if(z==1)
				board[x][y]="X";
			if(z==0)
				board[x][y]="O";
			this.showMyBoard();
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

	public static void main(String[] args) throws MalformedURLException,RemoteException,NotBoundException {
		RMIBattleShipClientA BSA= new RMIBattleShipClientA();
		BSA.initalizeShip();
		GameInterface gameInterface = (GameInterface)Naming.lookup("rmi://localhost/RMIServer");
		new RMIBattleShipClientA(gameInterface);
	}
}