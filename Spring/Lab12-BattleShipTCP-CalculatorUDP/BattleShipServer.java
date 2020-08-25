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
//import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
public class BattleShipServer {
	static String[][] board = new String[10][10];
	static String[][] oppBoard = new String[10][10];
	static String oppName="";
	static int xVar=-1,yVar=-1;
	static ServerSocket  ss;
	static ObjectOutputStream oos;
	static ObjectInputStream ois = null;
	static int myHit=0, oppHit=0;
	BattleShipServer() throws IOException{
		ss= new ServerSocket(60010);
	}


/**
 * Send a user name and accept a username from opponent
 * @throws IOException
 * @throws ClassNotFoundException
 */
	private void acceptNameSendName() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Socket s= ss.accept();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		oppName=(String)ois.readObject();
		System.out.println("OPP name is "+oppName);
		oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject("B");
		oos.flush();
		oos.close();
		ois.close();
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
				System.out.print(oppBoard[i][j]+" ");
			}
			System.out.println();
		}
	}
	/**
	 * Place the ships on the board.
	 */
	private void initalizeShip() {
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
		System.out.println("Enter the  X and and then Y cordinate ");
		String takeIp=sc.nextLine();
		String[] takeIpArr = takeIp.split(",",2);
		xVar=Integer.parseInt(takeIpArr[0]);
		yVar=Integer.parseInt(takeIpArr[1]);
		//String send=""+xVar+yVar;
		return ""+xVar+yVar;
	}
	/**
	 * Check the status of the hit/miss
	 * 
	 */
	private void checkBoard(String caseString,int x ,int y, Socket s, ObjectInputStream ois) throws IOException, ClassNotFoundException {
		String sendThis="";
		switch(caseString) {

		case "_":{ 
			board[x][y]="0";
			sendThis="0";
			System.out.println("Attack on new place, went waste");
			break;
		}
		case "0":{ 
			sendThis="0";
			System.out.println("Attack on same place,went waste");
			break;
		}
		case "X":{
			sendThis="0";
			System.out.println("Attack on same palce, went waste");
			break;
		}

		case "S":{
			board[x][y]="X";
			oppHit++;
			if(oppHit==6) {
				sendThis="-1,-1";
				System.out.println("Client wins");
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
		oos  = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(send);
		oos.flush();
		String receive=(String)ois.readObject();
		String ip=this.takeInput(s);
		send=ip;
		System.out.println("send this IP "+send);
		oos  = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(send);
		oos.flush();

		receive= (String)ois.readObject();
		if(receive.length()==5) {
			System.out.println("Server wins");
			this.initalizeShip();
		}
		else {
			int caseGot=Integer.parseInt(""+receive.charAt(0));

			switch(caseGot) {

			case 1:{
				oppBoard[Integer.parseInt(""+send.charAt(0))][Integer.parseInt(""+send.charAt(1))]="X";
				break;

			}

			case 0:{
				if(oppBoard[Integer.parseInt(""+send.charAt(0))][Integer.parseInt(""+send.charAt(1))].equals("_")) {
					oppBoard[Integer.parseInt(""+send.charAt(0))][Integer.parseInt(""+send.charAt(1))]="O";
				}
				break;
			}
			}
		}
		this.showBoard();
		oos.close();
		s.close();
	}
	/**
	 * 
	 * Start the game accepting  connecting by client.
	 */
	private void startGame() throws IOException, ClassNotFoundException {

		while(true) {

			// TODO Auto-generated method stub
			Socket s= ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			String receive=(String)ois.readObject();
			String[] receiveArr = receive.split(",",2);
			int xOpp,yOpp=0;
			xOpp=Integer.parseInt(""+receiveArr[0]);
			yOpp=Integer.parseInt(""+receiveArr[1]);
			String caseString=board[xOpp][yOpp];
			this.checkBoard(caseString,xOpp,yOpp,s,ois);
		}
	}
	public static void main(String args[]) throws IOException, ClassNotFoundException {

		BattleShipServer bss= new BattleShipServer();
		bss.acceptNameSendName();
		bss.initalizeShip();
		System.out.println("Game started. You are Server. A(Client) makes the first move.");
		bss.startGame();
	}
}
