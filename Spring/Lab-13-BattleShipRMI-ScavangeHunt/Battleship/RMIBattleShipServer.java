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
 *This file is the server which which controll the same play
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

interface GameInterface extends java.rmi.Remote {
	boolean registerClinet(GameInterface ci) throws RemoteException;
	void sendMessageToClient(String message) throws RemoteException;
	void sendMatrixToServer(String[][] mat, int who)throws RemoteException;
	String takeIP()throws RemoteException;
	void refershBoard(int x, int y, int z)throws RemoteException;
}
public class RMIBattleShipServer extends UnicastRemoteObject implements GameInterface {
	private static final long serialVersionUID = 1L;
	private ArrayList<GameInterface> clientList;
	static volatile  String[][] boardA = new String[10][10];
	static volatile String[][] boardB = new String[10][10];
	static int AHits=0, BHits=0;
	protected RMIBattleShipServer() throws RemoteException {
		clientList = new ArrayList<GameInterface>();
	}
/**
 * This method keeps track of the clients by keep them in an array
 */
	public   boolean registerClinet(GameInterface gameInterface) throws RemoteException {  
		this.clientList.add(gameInterface);
		return true;
	}
	/**
	 * This method accepts the matrix sent by client 
	 */
	@Override
	public void sendMatrixToServer(String[][] mat, int who) throws RemoteException {
		// TODO Auto-generated method stub
		if(who==0) {
			boardA=mat;
			System.out.println(" A borad");
			for(int i=0;i<boardA.length;i++) {
				for(int j=0;j<boardA.length;j++) {
					System.out.print(boardA[i][j]+" ");
				}
				System.out.println();
			}
		}
		else {
			boardB=mat;
			System.out.println("B  borad");
			for(int i=0;i<boardB.length;i++) {
				for(int j=0;j<boardB.length;j++) {
					System.out.print(boardB[i][j]+" ");
				}
				System.out.println();
			}

			this.startGame();//As now both the matrix are with server, lets start the game
		}   
		// TODO Auto-generated method stub
	}
	private void startGame() throws RemoteException {
		// TODO Auto-generated method stub
		while(true) {
			String IP= clientList.get(0).takeIP();
			System.out.println("IP from A is "+IP);
			String caseString=this.checkBoard(IP,0);
			clientList.get(1).refershBoard(Integer.parseInt(""+IP.charAt(0)), Integer.parseInt(""+IP.charAt(1)), Integer.parseInt(""+caseString.charAt(0)));
			this.showBoard(0);
			this.showBoard(1);
			IP= clientList.get(1).takeIP();
			System.out.println("IP from B is "+IP);
			caseString=this.checkBoard(IP,1);
			clientList.get(0).refershBoard(Integer.parseInt(""+IP.charAt(0)), Integer.parseInt(""+IP.charAt(1)), Integer.parseInt(""+caseString.charAt(0)));
			this.showBoard(0);
			this.showBoard(1);
		}
	}
	private void showBoard(int k) {
		// TODO Auto-generated method stub
		if(k==0) {
			System.out.println("A borad");
			for(int i=0;i<boardA.length;i++) {
				for(int j=0;j<boardA.length;j++) {
					System.out.print(boardA[i][j]+" ");
				}
				System.out.println();
			}
		}
		else {
			System.out.println("B borad");
			for(int i=0;i<boardB.length;i++) {
				for(int j=0;j<boardB.length;j++) {
					System.out.print(boardB[i][j]+" ");
				}
				System.out.println();
			}  
		}
	}
/**
 * Check the sataus of the attack the client.
 * @param IP Input string containg the co-ordinates
 * @param i 0 for Client A and 1 for Client B
 * @return  Prefix
 * @throws RemoteException
 */
	private String checkBoard(String IP,int i) throws RemoteException {
		// TODO Auto-generated method stub
		String sendThis="";
		if(i==0) {
			int x=Integer.parseInt(""+IP.charAt(0));
			int y=Integer.parseInt(""+IP.charAt(1));
			String status=boardB[x][y];
			System.out.println("Status of B board is "+status);
			switch(status) {

			case "_":{ 
				//boardA[x][y]="0";
				boardB[x][y]="0";
				sendThis="R0";
				System.out.println("Attack on new place, went waste");
				break;
			}
			case "0":{ 
				sendThis="R0";
				System.out.println("Attack on same place,went waste");
				break;
			}
			case "X":{
				sendThis="R0";
				System.out.println("Attack on same palce, went waste");
				break;
			}

			case "S":{
				//boardA[x][y]="X";
				boardB[x][y]="X";
				AHits++;
				if(AHits==6) {
					sendThis="R7";
					System.out.println("Client A wins");
					AHits=0;BHits=0;
					//this.initalizeShip();
				}
				else {
					sendThis="R1";
					System.out.println("Attack on my ship");
				}
				break;
			}
			}
			clientList.get(0).sendMessageToClient(sendThis);
		}
		else {
			int x=Integer.parseInt(""+IP.charAt(0));
			int y=Integer.parseInt(""+IP.charAt(1));
			String status=boardA[x][y];
			System.out.println("Status for B is "+status);
			switch(status) {

			case "_":{ 
				//boardB[x][y]="0";
				boardA[x][y]="0";
				sendThis="R0";
				System.out.println("Attack on new place, went waste");
				break;
			}
			case "0":{ 
				sendThis="R0";
				System.out.println("Attack on same place,went waste");
				break;
			}
			case "X":{
				sendThis="R0";
				System.out.println("Attack on same palce, went waste");
				break;
			}

			case "S":{
				//boardB[x][y]="X";
				boardA[x][y]="X";
				BHits++;
				if(BHits==6) {
					sendThis="R7";
					System.out.println("Client B wins");
					clientList.get(0).refershBoard(Integer.parseInt(""+IP.charAt(0)), 
							Integer.parseInt(""+IP.charAt(1)), 7);
					//this.initalizeShip();
				}
				else {
					sendThis="R1";
					System.out.println("Attack on my ship");
				}
				break;
			}

			}
			clientList.get(1).sendMessageToClient(sendThis);

		}
		return ""+sendThis.charAt(1);
	}

	public   void sendMessageToClient(String message) throws RemoteException{}

	public static void main(String[] arg) throws RemoteException, MalformedURLException {
		Naming.rebind("RMIServer", new RMIBattleShipServer());
	}

	@Override
	public String takeIP() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refershBoard(int x, int y, int z) {
		// TODO Auto-generated method stub

	}
}