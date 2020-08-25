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
 * Program is a simulation of elevtor using conepts of Threads.
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.util.Arrays;
import java.util.Random;

/**
 *Processor class is the one which log's in users requests and 
 *moves the lift accordingly 
 *
 */
class Processor   {

	private static int map[][];//Keeping a track of requests
	private static int up[][];//store info of people going up
	private static int down[][];//store info of people going donw
	private static int weight[];//random weights of the people
	private static int included[];//keep check on people entred lift
	private static int stories;
	private int where;//which floor to go
	private int at;//current postion
	private static int liftLoad=0;
	private final int liftCapacity=700;
	private static int totalPeople;
	private int cp;//current pointer used in construtor
	private int wt;//weight
	private static boolean flag = false,doOnce=true;
	private static int counter = 0;
	private static int keepTrack=0;//track of how many people got of out the
	//budling
	static Object o= new Object();//used for synchronization
	/**
	 * 
	 * @param p  Number of people used to initilizes the data structures
	 * @param st Stroies of the bulding
	 */
	public Processor(int p, int st) {
		totalPeople = p;
		map = new int[2][p];
		up = new int[2][p];
		down = new int[2][p];
		weight=new int[p];
		stories = st;
		included=new int[p];

	}
	/**
	 * This contstrutor will be used to fill in the data in the map matrix
	 */
	public Processor(int where, int at, int cp, int wt) {
		this.cp = cp;

		if(cp<stories) {
			if(cp==0)
				this.where=2;
			else
				this.where = cp+1;	
		}
		else {
			this.where = where;
		}
		this.at = at;
		this.wt=wt;
	}

	public Processor() {
	}

	public void runElevator() throws InterruptedException {  
		synchronized(o){
			if(keepTrack<totalPeople) {//only allow if still people are in bulding
				counter++;
				if (flag == false) {// make the map only once
					map[0][cp] = where;
					map[1][cp] = at;
					weight[cp]=wt;
					included[cp]=-111;

					System.out.println(Thread.currentThread().getName()
							+ " wants to go from " + at + " to" + where);

					if (counter < totalPeople)
					{//System.out.println("Array not full yet");
					}
					else {
						flag = true;
					}

				} else {//Make/Initialize down and up arrays only once
					if(doOnce) {
						for (int i = 0; i < totalPeople; i++) {
							if (map[0][i] - map[1][i] < 0) {
								down[0][i] = map[0][i];
								down[1][i] = map[1][i];
							} else {

								up[0][i] = map[0][i];
								up[1][i] = map[1][i];
							}
						}
						System.out.println("Down array");
						for (int i = 0; i < 2; i++) {
							for (int k = 0; k < totalPeople; k++) {
								System.out.print(down[i][k] + "  ");

							}
							System.out.println();
						}

						System.out.println("up array");
						for (int i = 0; i < 2; i++) {
							for (int k = 0; k < totalPeople; k++) {
								System.out.print(up[i][k] + "  ");

							}
							System.out.println();
						}
						System.out.println("Weight array");

						for (int k = 0; k < totalPeople; k++) {
							System.out.print(weight[k] + "  ");

						}
						System.out.println();

						doOnce=!doOnce;
					}
					int floor = 1;
					int max=-1;//used to allow lift's movment in optimum way
					System.out.println("Elevator going up");

					for (int i = 1; i <= stories; i++) // number of stories change here
					{   
						for (int j = 0; j < totalPeople; j++) {

							if (up[1][j] == i ) {
								if(liftLoad+weight[j]<=liftCapacity) {// check weight constrains
									liftLoad+=weight[j];
									included[j]=1;
									//Calculate the max floor in up array till which lift should go
									if(max<up[0][j]) {
										max=up[0][j];
									}
									System.out.println("Person " + (j + 1)
											+ " gets in at floor " + i);
									up[1][j] = 0;
								}
								else {
									System.out.println("Cant take in person "+(j+1)+" as his weight is"
											+ " "+weight[j]+". Current lift load is "+liftLoad);
									included[j]=-1;
									continue;
								}
							}
							if (up[0][j] == i&&included[j]==1) {
								System.out.println("Person " + (j + 1) 
										+ " gets down at floor " + i);
								up[0][j] = 0;
								liftLoad-=weight[j];//person got down. Minus his weight
								//included[j]=-1;
								final int innerJ=j;
								final int innerI=i;
								/**Spawn  a new thread every time a person gets down at a floor.
								 *  This newly spawned thread calls this function again to take the person down. 
								 **/
								Thread ping=new Thread(new Runnable() {
									public void run() {
										try {
											/*System.out.println("Person " + (innerJ + 1) 
											+ " starts his workday ");*/
											Thread.sleep(3000);
											System.out.println("Person " + (innerJ + 1) 
													+ " ends his workday ");
											//update the down arrays as person has to come down
											down[1][innerJ]=innerI;
											down[0][innerJ]=1;
											//System.out.println(" thread "+Thread.currentThread().getName()+" calling elv again");
											if(keepTrack<totalPeople) {
												runElevator();
											}
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								ping.start();//person got down, swapn a new thread.
							}
						}
						//Calculate the max floor in down array till which lift should go
						for(int ik=0;ik<totalPeople;ik++) {
							if(max<down[1][ik]) {
								max=down[1][ik];
							}
						}
						if(max==i&&(keepTrack<totalPeople)) {
							System.out.println("Elevator reached floor"+max+" .Changing direction");
							max=-1;
							break;
						}
						Thread.sleep(500); 
					}
					System.out.println("Elevator going down");
					for (int i = stories; i >= 1; i--)// number of stories change here
					{
						for (int j = 0; j < totalPeople; j++) {
							if (down[1][j] == i && included[j]>=1) {
								if(liftLoad+weight[j]<=liftCapacity) {
									liftLoad+=weight[j];
									included[j]=1;
									System.out.println("Person " + (j + 1)
											+ " gets in at floor " + i);
									down[1][j] = 0;

								}
								else {System.out.println("Cant take in person "+(j+1)+" "
										+ "as his weight is "+weight[j]+". Current lift load is "+liftLoad);
								included[j]=2;//first time rejected 	
								continue;
								}
							}
							if (down[0][j] == i&& included[j]==1&&down[1][j]==0) {
								System.out.println("Person " + (j + 1)
										+ " gets down at floor " + i);
								down[0][j] = 0;
								liftLoad-=weight[j];
								included[j]=-1;
								//System.out.println("Updating keeptrack");
								++keepTrack;//as person got down, update counter
							}
						}
						Thread.sleep(500);// speed of the lift
					}
				}
			}
		}//if track
	}
}
/**
 * Elevator is the main class of the program which make requests to lift 
 * by using random number generator.
 */
public class ElevatorThread {
	/**
	 *The main program.
	 *
	 *@param    args    command line arguments (ignored)
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException  {
		Random random = new Random();
		int p = 10;//Number of the people
		int st = 5;//stories of the bulding
		new Processor(p, st);
		//Initialze the map matrix by using 10 threads 
		Thread t1[] = new Thread[p];
		int i = 0;
		for ( i = 0; i < p; i++) {
			final int innerI=i;

			t1[i] = new Thread(new Runnable() {
				public void run() {

					int wh = random.nextInt(st - 2 + 1) + 2;
					int wt=  random.nextInt(250-100)+100;
					//int at = random.nextInt(st - 1 + 1) + 1;
					int at=1;//currently at 1st floor
					while (wh == at) {
						wh = random.nextInt(st - 2 + 1) + 2;
						at = random.nextInt(st - 1 + 1) + 1;
						wt=  random.nextInt(250-100)+100;
					}
					Processor p=new Processor(wh, at, innerI,wt);
					try {
						p.runElevator();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		for (int ip = 0; ip < p; ip++) {
			t1[ip].start();
		}
		for (int ip = 0; ip < p; ip++)
			t1[ip].join();

		//start elevator
		Thread elv=new Thread(new Runnable() {
			public void run() {

				Processor p=new Processor();
				try {
					p.runElevator();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		elv.start();
		elv.join();
	}
}