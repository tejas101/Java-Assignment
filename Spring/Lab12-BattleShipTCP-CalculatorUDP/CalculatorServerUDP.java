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
import java.util.*;

interface OperationFunctionalInterface{
    int operation(int a, int b);
}

interface CalculatorInterface{
    int performOperation(OperationFunctionalInterface ofi, int num1, int num2);
    static void helpMessage(int exitCode)    {
        //System.out.println("Usage: <num> <operation> <num>");
        System.exit(exitCode);
    }
}

public class CalculatorServerUDP implements CalculatorInterface {
    @Override
    public int performOperation(OperationFunctionalInterface ofi, int num1, int num2) {
        return ofi.operation(num1, num2);
    }

    public static void main(String[] args) throws IOException {
    	int server_port = 7171;
    	for(;;) {
            try {
            	DatagramSocket serverSocket = new DatagramSocket(server_port); 
                byte data[] = new byte[100]; 
   		     	DatagramPacket ServerPacket = new DatagramPacket(data, data.length);
   		     	serverSocket.receive(ServerPacket);
   		     	String dataRecieved = new String(data, 0, data.length);
   		     	String error_message = "\nWrong Inputs!\nIt can be either because of:\n\t"
   		     			+ "1. Wrong Number Format.\n\t2. Wrong operation.\nPlease enter correct arguments!";
   		     	byte[] sendErrorMsg = new byte[1024];
   		     	InetAddress address = ServerPacket.getAddress();
   		     	int port = ServerPacket.getPort();
   		     	dataRecieved = dataRecieved.trim();
   		     	String [] arrOfStr = dataRecieved.split(":"); 
   		     	int num_1 = Integer.parseInt(arrOfStr[0]);
   		     	int num_2 = Integer.parseInt(arrOfStr[2]);
   		     	String operation = arrOfStr[1];
   		     	String equation = arrOfStr[0] + arrOfStr[1] + arrOfStr[2];  
   		     	if(operation.equals("+")) {
   		     	System.out.println("Adding : \n\t" + num_1 + " + " + num_2);
   		     	}else if(operation.equals("-")) {
   		     	System.out.println("Subtracting \n\t: " + num_1 + " - " + num_2);
   		     	}else if(operation.equals("*")) {
   	   		     	System.out.println("Multiplying \n\t: " + num_1 + " * " + num_2);
   		     	}else if(operation.equals("/")) {
	   		     	System.out.println("Dividing \n\t: " + num_1 + " / " + num_2);
   		     	}
   		     	int result = 0;
   		     	CalculatorServerUDP calculator = new CalculatorServerUDP();
                switch (operation.charAt(0)) {
                case '+':
                	result = calculator.performOperation(((num1, num2) -> num1 + num2), num_1, num_2);
                	break;
                case '-':
                	result = calculator.performOperation(((num1, num2) -> num1 - num2), num_1, num_2);
                	 break;
                case '*':
                	result = calculator.performOperation(((num1, num2) -> num1 * num2), num_1, num_2);
                	break;
                case '/':
                        try {
                        	result = calculator.performOperation(((num1, num2) -> num1 / num2), num_1, num_2);
                            break;
                        }catch(ArithmeticException a){
                            System.out.println("Divide by 0 Error!");
                        }
                default:
                	sendErrorMsg = error_message.getBytes();
                    DatagramPacket sendError =
                    new DatagramPacket(sendErrorMsg, sendErrorMsg.length, address, port);
                    serverSocket.send(sendError);
                }
                System.out.println("Done Calculation!"); 
                String resultToSend = Integer.toString(result); 
                byte[] dataToSend = resultToSend.getBytes(); 
                DatagramPacket msgSend = new DatagramPacket(dataToSend, dataToSend.length, address, port);
			    serverSocket.send(msgSend);
			    serverSocket.close();
            }catch(NumberFormatException ex) {
                CalculatorInterface.helpMessage(2);
            }
    	}
    }
}
