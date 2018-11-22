/* ConsumerProducer.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program implements N consumer, M Producer problem 
 *using Threads
 * 
 *@author      Tejas Raval 
 */
import java.util.ArrayList;
import java.util.Scanner;

class lessThanEqualtoZero extends Exception {

    public lessThanEqualtoZero(String message) {
        super(message);
    }
}
public class ConsumerProducer extends Exception {
    static ArrayList < Integer > tray = new ArrayList < Integer > ();
    static int counter = 0;
    static Object mutex = new Object();
    static int s;
    static int plimit;
    static int climit;

    public static void main(String args[]) throws InterruptedException,
        lessThanEqualtoZero {
            Scanner sc = new Scanner(System.in);
            int c = Integer.parseInt(args[0]); //number of consumers
            int p = Integer.parseInt(args[1]); //number of producers
            int prodLimit=Integer.parseInt(args[3]);//limit to produce for producer
            int custLimit=Integer.parseInt(args[4]);//limit to consume for consumer
            s = Integer.parseInt(args[2]); //size of storage

            if (s <= 0 || p <= 0 || c <= 0 || prodLimit <= 0 || custLimit <= 0) {
                throw new lessThanEqualtoZero("Input values should " +
                    "be greater than Zero");
            }
            ConsumerProducer CP = new ConsumerProducer();

            Thread t1[] = new Thread[p];
            int i = 0;
            for (i = 0; i < p; i++) {
                t1[i] = new Thread(new Runnable() {

                    public void run() {

                        try {
                            CP.Producer(s,prodLimit);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
            Thread t2[] = new Thread[c];
            int k = 0;
            for (k = 0; k < c; k++)
                t2[k] = new Thread(new Runnable() {

                    public void run() {
                        try {
                            CP.Consumer(custLimit);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });

           for (int ip = 0; ip < p; ip++)
                t1[ip].start();
            for (int ic = 0; ic < c; ic++)
                t2[ic].start();

            for (int ic = 0; ic < c; ic++)
                t1[ic].join();
            for (int ip = 0; ip < p; ip++)
                t2[ip].join();
        }
    /**
     *Producer() will implement producer function
     *@param   s        Size of the storage/tray
     */
    void Producer(int s,int prodLimit) throws InterruptedException {
        while (true) {
            synchronized(mutex) {
            	if(tray.size() < s && plimit<=prodLimit) {
                System.out.println(Thread.currentThread().getName() + " Producer" +
                    " added " + (counter + 1));
                //System.out.println("producer limit "+(plimit+1));
                plimit++;
                tray.add(0, ++counter);}
                mutex.notifyAll();
                System.out.println("Tray size after addition of producer "+tray.size());
                //System.out.println("Limit is"+s);
                if (tray.size() == s ||  plimit>=prodLimit)
                {  
                	System.out.println("producer is waiting");
                	if(plimit>prodLimit)
                    	plimit=0;
                	mutex.wait(); 
                }
            }
            Thread.sleep(1000);
        }
    }
    /**
     *Consumer() will implement consumer function
     */
    void Consumer(int custLimit) throws InterruptedException {
        while (true) {
            synchronized(mutex) {
            	 while(climit<custLimit && tray.size()!=0) {
                	 System.out.println(Thread.currentThread().getName() + " Conusmer " +
                             "removed " + tray.get(tray.size() - 1));
                tray.remove(tray.size() - 1);
                climit++;
                }
                System.out.println("Tray size after removal from consumer "+tray.size());
                if (tray.size() == 0 ||  climit>=custLimit)
                {   
                	System.out.println("Consumer is waiting");
                	climit=0;
                	mutex.wait();
               }

                mutex.notifyAll();
            }
            Thread.sleep(1000); 
        }
    }
}