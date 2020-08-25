/* Calculator.java
 * Version: 1
 *
 * Revisions:
 *
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/**
 * ----------Program Description-----------
 * This program performs basic operation of calculator using
 * Lambda expressions, with UDP.
 *@author      Tejas Raval
 *@author      Lipisha Chaudhary
 */
import java.io.*;
import java.net.*;

public class CalculatorClientUDP {
	public static void main(String[] args) throws IOException {
		if(args.length == 5) {
			System.out.println("Enter: <Port Number> <address> <num> <operation> <num> : for command line arguments");
			int port = Integer.parseInt(args[0]);
			InetAddress address = InetAddress.getByName(args[1]);
			DatagramSocket clientSocket = new DatagramSocket();
			byte data [] = null;
			String equation = args[2] + ":" + args[3] + ":" + args[4];
			data = new byte[100];
			data = equation.getBytes();
			//equation = data.getBytes();
			DatagramPacket sendData = new DatagramPacket(data, data.length, address, port);
			clientSocket.send(sendData);
			data = new byte[100];
			DatagramPacket dataRecieve = new DatagramPacket(data, data.length);
			clientSocket.receive(dataRecieve);
			String result = new String(dataRecieve.getData());
			result = result.trim();
			System.out.println("The result for the equation you requested is: " + result);
		} else {
			System.out.println("Enter the correct number of arguments!\n\t<Port Number> <address> <num> <operation> <num>");
		}
	}

}
