/* Elevator.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * Program is a simulation of elevator using concepts of Threads. This code is the elevator
 * which is controlled by driver class.
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.util.Random;

public class Elevator implements Runnable {
	public static final int  CAPACITY=700;
	public static  int  load;
	static GenericLinkedList<People> currentPublic =new GenericLinkedList<>();

	public void run() {

		synchronized(ElevatorDriver.o){
			while(true) {
				currentPublic.sort();
				if(currentPublic.size()!=0&&currentPublic.get(0).at==1) {//Elevator movment up
					goUp();
				}
				else { 
					goDown();
				}
				Elevator.load=0;
				currentPublic.clear();
				try {ElevatorDriver.o.notifyAll();
				ElevatorDriver.o.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}//while
		}

		
		
	}
	static void  goUp() {
		
		for(int i=0;i<currentPublic.size();i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(currentPublic.get(i).at==1) {
				System.out.println("Person "+currentPublic.get(i).number+" gets donw at floor "+currentPublic.get(i).where);
				People temp=currentPublic.get(i).pepObj;
				temp.state=2;//persone got donw on the desried floor to start wokrday
			}
		}
		System.out.println("lift going down");
	}
	
	static void goDown() {
		//Elevator moving down.
		if(currentPublic.size()!=0) {
			System.out.println("lift going down");}
		for(int i=currentPublic.size()-1;i>=0;i--)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Person "+currentPublic.get(i).number+" gets in at floor "+currentPublic.get(i).at +" for going down ");
			People temp=currentPublic.get(i).pepObj;
			temp.state=4;//person went got back in the lobby to go home
		}
		if(currentPublic.size()!=0) {
			System.out.println("every one gets down at floor 1");
		}
	}
}

