/* PrimeAsFastAspossible.java  
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
import java.util.Collections;
import java.util.Scanner;


class lessThanEqualtoZero extends Exception {

    public lessThanEqualtoZero(String message) {
        super(message);
    }
}

class NumberOfThread extends Exception {

    public NumberOfThread(String message) {
        super(message);
    }
}

class PrimeAsFastAsPossible extends Exception {
    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     * @throws lessThanEqualtoZero 
     * @throws NumberOfThread 
     */
    public static void main(String args[]) throws 
    lessThanEqualtoZero, NumberOfThread {


        long startTime = System.currentTimeMillis();
        int n = 0;
        int NT = 0;
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(args[0]);
        NT = Integer.parseInt(args[1]);

        if (n <= 0 || NT <= 0) {
            throw new lessThanEqualtoZero("Input values should " +
                "be greater than Zero");
        }

        int step = n / NT;
        if (n < NT)
            {
        	throw new NumberOfThread("Number of Threads should"
        			+ " be less than the input"
        			+ "number");
            }

        int start = 1;
        int end = step;
        Calculation[] th = new Calculation[NT];
        for (int i = 0; i < NT; i++) {
            //System.out.println("Speration of number ranges for Thread "+i);
            //System.out.println("Start is " + start + " End is " + end);
            th[i] = new Calculation(start, end);
            th[i].start();
            start = end + 1;

            if (i == NT - 2) {
                end = end + step + (n % NT);
            } else
                end = start + step - 1;

        }
        
        for (int i = 0; i < NT; i++) {
                try {
                    th[i].join();
                } catch (InterruptedException e) {}
        }
        long endTime = System.currentTimeMillis() - startTime;
        Calculation c = new Calculation();
        Collections.sort(c.primeNumb);
        System.out.println("prime numbers are");
        for (int num: c.primeNumb)
            System.out.println(num);

        System.out.println("Total time:  " + (endTime / 1000.0)
        		+ " seconds.");


    }

}