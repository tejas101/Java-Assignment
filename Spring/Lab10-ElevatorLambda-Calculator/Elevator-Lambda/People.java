/* People.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * This is the people class which contains the threads created by the
 * driver. Thread maintain a state according to their current activity
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.util.Random;

public class People implements Comparable<People>  {
	static Object o= new Object();
	static final int p = 10;
	static int stories=5;
	volatile int weight;
	volatile int where;
	volatile boolean s0,s1,s2,s3,s4;
	volatile int number;
	volatile int at;
	volatile int state;
	static int cp=0;
	People pepObj;
	Bulding fl;
	public People() {
	}
	public  void registerPeople(int stories,People pepObj,int to,Bulding floor) {
		synchronized(o) {
			Random random = new Random();
			this.where=to;
			this.number=++cp;
			this.at = 1;
			this.weight=random.nextInt(250-100)+100;
			this.state=0;
			this.s0=false;
			this.s1=false;
			this.s2=false;
			this.s3=false;
			final People tempP=this;
			//-----
			Runnable r = () -> {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(tempP.state==0&&!tempP.s0) {
						//System.out.println("Person "+tempP.number+"Will register him in the lobby");
						tempP.s0=!tempP.s0;

					}

					if(tempP.state==1 && !tempP.s1) {
						System.out.println("Person "+(tempP.number)+" Will get in the lift");
						tempP.s1=!tempP.s1;
					}

					if(tempP.state==2 && !tempP.s2) {
						System.out.println("Person "+(tempP.number)+"Will start work day");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						tempP.state=3;
						tempP.s2=!tempP.s2;
					}

					if(tempP.state==3 && !tempP.s3) {
						System.out.println("persone "+(tempP.number)+"request for returning back home");
						tempP.s3=!tempP.s3;
						tempP.at=tempP.where;
						tempP.where=1;
						/**
						 * As soon as person thread wakes up, it changes state and registers itself in the return queue 
						 * of the respective floor.
						 */
						floor.floorQ.add(tempP.number);
					}
					if(tempP.state==4 ) {
						//System.out.println("Person "+tempP.number+" got down of the lift, going home");
						//ElevatorDriver.people[tempP.number]=null;
						break;
						
					}
					
					
				}
                };
                new Thread(r).start();
			//-----
		}

	}
	public int compareTo(People e) {
		// TODO Auto-generated method stub
		if(this.at==1) {
			return this.where - e.where;
		}
		else {
			return this.at - e.at;
		}
	}
}


