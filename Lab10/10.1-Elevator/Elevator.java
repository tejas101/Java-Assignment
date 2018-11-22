/* Elevator.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 * ----------Program Description-----------
 * Program is a simulation of elevtor using ThreadPool and ThreadFactor
 *@author      Tejas Raval 
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;
import java.util.Random;

//Exception calss
class wrongInput extends Exception {

	public wrongInput(String message) {
		super(message);
	}
}

//ThreadFactory
class ElevatorThreadFactory implements ThreadFactory {
	private int counter;
	private String name;

	public ElevatorThreadFactory(String name) {
		counter = 1;
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable runnable) {
		Thread t = new Thread(runnable, name + "-Thread_" + counter);
		counter++;
		return t;
	}
}
/**
 *Processor class is the one which log's in users requests and 
 *moves the lift accordingly 
 *
 */
class Processor implements Runnable {

	private static int map[][];//Keeping a track of requests
	private static int up[][];
	private static int down[][];
	private static int stories;
	private int where;
	private int at;
	private int work[];
	private static int totalPeople;
	private int cp;
	private static boolean flag = false;
	private static int counter = 0;

	public Processor(int p, int st) {
		totalPeople = p;
		map = new int[2][p];
		up = new int[2][p];
		down = new int[2][p];
		stories = st;

	}

	public Processor(int where, int at, int cp) {
		this.cp = cp;
		this.where = where;
		this.at = at;
	}

	public Processor() {
	}

	public void run() {
		counter++;
		if (flag == false) {
			map[0][cp] = where;
			map[1][cp] = at;

			System.out.println(Thread.currentThread().getName()
					+ " wants to go from " + at + " to" + where);
			
			if (counter < totalPeople)
				System.out.println("Array not full yet");
			else {
				flag = true;

			}

		} else {
			System.out.println(Thread.currentThread().getName() + "is here");
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

			int floor = 1;
			System.out.println("Elevator going up");
			for (int i = 1; i <= stories; i++) // number of stories change here
			{
				for (int j = 0; j < totalPeople; j++) {

					if (up[1][j] == i) {
						System.out.println("Person " + (j + 1)
								+ " gets in at floor " + i);
						up[1][j] = 0;
					}
					if (up[0][j] == i) {
						System.out.println("Person " + (j + 1) 
								+ " gets down at floor " + i);
						up[1][j] = 0;
					}

				}

			}

			System.out.println("Elevator going down");
			for (int i = stories; i >= 1; i--)// number of stories change here
			{
				for (int j = 0; j < totalPeople; j++) {
					if (down[1][j] == i) {
						System.out.println("Person " + (j + 1)
								+ " gets in at floor " + i);
						down[1][j] = 0;
					}
					if (down[0][j] == i) {
						System.out.println("Person " + (j + 1)
								+ " gets down at floor " + i);
						down[1][j] = 0;
					}

				}

			}

		}

	}
}
/**
 * Elevator is the main class of the program which make requests to lift 
 * by using random number generator.
 */
public class Elevator {
	/**
     *The main program.
     *
     *@param    args    command line arguments (ignored)
     */
	public static void main(String[] args) throws wrongInput {
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("How many people?");
		int p = sc.nextInt();
		System.out.println("How many stories?");
		int st = sc.nextInt();
		sc.nextLine();
		if(p<=0||st<=1)
			throw new wrongInput("Wrong input");
		new Processor(p, st);


		ExecutorService executor = Executors.newFixedThreadPool(p,
				new ElevatorThreadFactory("Person"));
		for (int i = 0; i < p; i++) {

			int wh = random.nextInt(st - 2 + 1) + 2;
			int at = random.nextInt(st - 1 + 1) + 1;
			while (wh == at) {
				wh = random.nextInt(st - 2 + 1) + 2;
				at = random.nextInt(st - 1 + 1) + 1;
			}
			executor.submit(new Processor(wh, at, i));
		}

		executor.shutdown();


		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		}
		ExecutorService executorElevator = Executors.newFixedThreadPool(1);

		executorElevator.submit(new Processor());

	}

}