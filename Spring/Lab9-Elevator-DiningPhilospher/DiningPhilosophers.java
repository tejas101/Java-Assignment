/* DiningPhilosophers.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * Program is a solution for Dining Philosophers problems
 *@Author           Prof. HP
 *@Contributor      Tejas Raval 
 *@Contributor      Lipisha Chaudhary
 */
import java.util.concurrent.Semaphore;

/** A class implementing the Dining Philosphers */
public class DiningPhilosophers extends Thread {

  protected int me;             	// number for trace
  protected static Semaphore left, right;   	// my chopsticks
  static Semaphore f[] = new Semaphore[5];
  Object ol = new Object();
  Object or = new Object();
  public DiningPhilosophers (int me, Semaphore left, Semaphore right) {
    this.me = me; this.left = left; this.right = right;
  }
/** philosopher's body: think and eat 5 times */
  public void run () {
    for (int n = 1; n <= 100000; ++ n) {
      System.out.println(me+" thinks");
      /*try {
        Thread.sleep((long)(random.nextFloat()*1000));
      } catch(Exception e) {
	e.printStackTrace();
      }*/
      System.out.println(me+" is trying to eat"); 
      synchronized ( ol )  {
    	  try {
			left.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   synchronized ( or )  {
		   try {
			right.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    System.out.println("\t" + me+" eats"); 
		    /*try {
		      Thread.sleep((long)(random.nextFloat()*1000));
                    } catch(Exception e) {
			e.printStackTrace();
	            }*/
		    right.release();
	  }
	   left.release();
    }
    System.out.println("\t" + me+" leaves");
  }
 }
/** sets up for 5 philosophers */
  public static void main (String args []) {
	  
	  
    for (int n = 0; n < 5; ++ n)
		f[n] = new Semaphore(1);
    DiningPhilosophers p[] = new DiningPhilosophers[5];
    //Pass Semaphore to the constructor 
    p[0] = new DiningPhilosophers(0, f[4], f[0]);      // backwards
    for (int n = 1; n < 5; ++ n) {
		p[n] = new DiningPhilosophers(n, f[n-1], f[n]);
		p[n].start();
    }
     
  }
}