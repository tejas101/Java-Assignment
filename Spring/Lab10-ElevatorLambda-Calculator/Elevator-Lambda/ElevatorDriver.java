/* ElevatorDriver.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * Program is a simulation of elevator using concepts of Threads and Lambda expressions.
 * This code is the driver who 1st starts people threads, then starts elevtor and computation
 * based of weight constraints and momvment of the elevator.
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class ElevatorDriver {
	final static  int NUM_PEOPLE=10;
	public final static  int STORIES=5;
	static People[] people = new People[NUM_PEOPLE+1];
	static GenericLinkedList<People> peopleInfo =new GenericLinkedList<>();//Driver stores the data 
	public static Object o = new Object();
	static Bulding[] floor= new Bulding[STORIES+1];
	interface Adder 
	{ 
		int adding(int a, int b); 
	} 

	interface Subtract 
	{ 
		int substracting(int a, int b); 
	} 
	interface Percent 
	{ 
		float contribution(int a, int b); 
	} 

	public static void main(String[] args) throws InterruptedException {
		Adder addition = (int a, int b) -> (a + b);

		Random random = new Random();
/**
 * Create the threads for the people
 */
		for (int i = 1; i <= NUM_PEOPLE; i++) {

			people[i]= new People();
			int to=i;
			if(i<STORIES) {
				if(i==1)
				{to=2;}
				else {	
					to=addition.adding(to,1);					
				}
			}
			else {
				to = random.nextInt(STORIES - 2 + 1) + 2;
			}
			if(floor[to]==null) { 
				floor[to]= new Bulding();
			}
			people[i].registerPeople(STORIES,people[i],to,floor[to]);
		}
		Thread.sleep(100);
		/**
		 * Show the information on the console
		 */
		for (int i = 1; i < people.length; i++) {
			System.out.print("Number "+people[i].number);
			System.out.print(" Where "+people[i].where);
			System.out.print(" at "+people[i].at);
			System.out.print(" weight "+people[i].weight);
			System.out.println("");
		}
		int i=1;
		/**
		 * Driver starts processing the data by checking the weight constrainst and ordering
		 * elevator to move up and down.
		 */
		synchronized(o){
			new Thread(new Elevator()).start();
			while( true){	
				//Elevator will go up to drop people to office 
				for( ;i<people.length;i++) {

					if(people[i].weight+Elevator.load<=Elevator.CAPACITY) {
						people[i].pepObj=people[i];
						Elevator.currentPublic.add(people[i]);
						people[i].state=1;//person got in the elevtor
						Elevator.load=addition.adding(Elevator.load, people[i].weight);					
					}
					else
					{   
						break;
					}
				}
				if(Elevator.currentPublic.size()!=0) {
					System.out.println("lift goingn up");
					elevatorAndPersonSummary();//prints some basic information of the people
				}
				
				o.notifyAll();
				o.wait();
				//Elevator will go down to check who are returing back.
				for(int j=2;j<floor.length;j++)
				{
					if(floor[j].floorQ.size()!=0) {
						while(floor[j].floorQ.size()!=0 &&   people[floor[j].floorQ.get(0)].weight+Elevator.load<=Elevator.CAPACITY) {
							Elevator.load=addition.adding(Elevator.load, people[floor[j].floorQ.get(0)].weight);
							Elevator.currentPublic.add(people[floor[j].floorQ.remove(0)]);
						}
					}
				}
				if(Elevator.currentPublic.size()!=0) {
					System.out.println("lift going  up");
				}
				/*if(checkNull()==true) {
					System.out.println("No one left. Exiting code");
					//System.exit(0);
					Thread.sleep(Long.MAX_VALUE);
				}*/
				o.notifyAll();
				o.wait();
				
				
				
			}
		} 
	}
	private static void elevatorAndPersonSummary() {
		Percent calculate = (int a, int b) -> (((float)a/(float)b)*100);

		People[] evenArray=new People[Elevator.currentPublic.size()];
		for(int i=0;i<Elevator.currentPublic.size();i++) {
			evenArray[i]=Elevator.currentPublic.get(i);
		}
		Stream<People> stream1 = Arrays.stream(evenArray);
		stream1.filter(n->n.number% 2 == 0).forEach(el->{

			//System.out.println(el.number);
			System.out.println("Person "+el.number);
			System.out.println("   Weight  "+el.weight);
			System.out.println("   Status  on");	
			System.out.println("   Percent contribution  "+calculate.contribution(el.weight, Elevator.load));

		});


	}
	
	/*static boolean checkNull() {
		boolean flag =true;
		for(int i=1;i<people.length;i++) {
			if(people[i]!=null) {
				flag=false;
			}
		}
		return flag;
		
	}*/
}
