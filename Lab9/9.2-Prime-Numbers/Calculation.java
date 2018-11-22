/* Calculation.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program calculates prime number for given range
 * 
 *@author      Tejas Raval 
 */
import java.util.ArrayList;

class Calculation extends Thread {
    int start, end;
    static ArrayList < Integer > primeNumb = new ArrayList < Integer > ();
    public Calculation(int start, int end) {
        this.start = start;
        this.end = end;

    }
    public Calculation() {
        // TODO Auto-generated constructor stub
    }

    public void run() {
        this.compute();

    }
    /**
     *isPrime()     will return true if number is prime
     *@param   x    number to be checked as prime
     *@param   size size the arraylist which holds all the 
     *              prime numbers less than x
     */
    public synchronized boolean isPrime(int x, int size) {
        for (int i = 0; i < size; i++) {
            if (primeNumb.get(i) != 0)
                if (x % primeNumb.get(i) == 0) {
                    return false;
                }
        }
        return true;
    }

    /**
     *compute()     will store all the prime numbers in the ArrayList
     */
    private synchronized void compute() {

        // TODO Auto-generated method stub
        for (int i = start; i <= end; i++) {
            if (primeNumb.size() == 0) {
                primeNumb.add(2);
            } else {
                if (this.isPrime(i, primeNumb.size())) {
                    primeNumb.add(i);
                    /*System.out.println(i + " added by " +
                        Thread.currentThread().getName());*/
                }
            }

        }

    }


}