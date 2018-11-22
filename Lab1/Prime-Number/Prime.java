/* 
 * Prime.java 
 * 
 * Version: 
 *     1 
 * 
 * Revisions: 
 *     0 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */

/** 
 * This program calculates the sum of prime factorials of numbers from 2 to 10
 * 
 * @author      Tejas Raval 
 * 
 */
class Prime {
 /**
  * The main program.
  *
  * @param    args    command line arguments (ignored)
  */
 public static void main(String args[]) {

  int[] primeNumbers = new int[5]; //Array to hold prime numbers
  int[] outputList = new int[5]; // Array to hold prime factors
  int counter1 = 0; // to iterate over the primeNumbers array 
  int counter2 = 0; // to iterate over the array which 
                    //holds the prime factor of a non-prime number
  int sum = 0;
  for (int index = 2; index <= 10; index++)
   if (isPrime(index)) {
    System.out.println("The sum of all primes for " + index + ":     "
    		+ " " + index + "      (" + index + ")");
    primeNumbers[counter1++] = index; 
    /**
     *Store the prime numbers
     *which we encounter in the given range
     */
   } else { 
	   /**
	     * Below is the code for calculating prime 
	     * factorials for non-prime numbers
	     */
   int temp = index;
   /**
     *Assign the current index to a temporary variable so that 
     *div and mod opertaion can be carried out on temporary variable
     */


   for (int i = 0; i < counter1; i++) {
 
	 while (temp % primeNumbers[i] == 0) {
     outputList[counter2++] = primeNumbers[i];
     /** 
      * if I get a prime factor of a non-prime number
      * then I store it for further processing
      */
     temp = temp / primeNumbers[i];
    }
   }
   String outputString = ""; 
   /** 
     * Below, the string formating is done by 
     * using outputList[] to get the desired output.
     */
   for (int l = 0; l < counter2; l++) {
    sum = sum + outputList[l];
    if (outputString == "") {
    	outputString = outputString + outputList[l] + "+";
    } else {
     if (!(counter2 - l == 1)) { //to check if last element of the array is not processed.
    	 outputString = outputString + outputList[l] + "+";
     }else {
    	 outputString = outputString + outputList[l];
    }}
   }

   System.out.println("The sum of all primes for " + index + 
		   ":      " + sum + "      (" + outputString + ")");
   
   /**
     * Below the re-initialization of the variables and array is done 
     * to use it for finding the prime factors of the next non-prime numbers
     */
   sum =counter2= 0;
   for (int l = 0; l < outputList.length; l++) {
    outputList[l] = 0;
   }


  }

 }
 /**
  * isPrime method checks whether the number is a prime or not.
  *
  * @param       n    Number passed from main class.
  * @return           Boolean value true if its a prime or else false.
  */
 
 public static boolean isPrime(int n) {

	  for (int index = 2; index < n; index++) {
	   if (n % index == 0)
	    return false;
	  }

	  return true;
	 }
}