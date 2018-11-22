/*
 * TestMath.java 
 * 
 * Version: 
 *     1 
 * 
 * Revisions: 
 *     0 
 *     
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     
 *     
 *     Algorithm used : Babylonian method for getting square root.
 */
/** 
 * This program calculates the square root, absoulte value
 * and maximum and minimum between the given numbers.
 * 
 * @author      Tejas Raval 
 */
public class TestMath {
	/**
	  * The main program.
	  *
	  * @param    args    command line arguments (ignored)
	  */
    public static void main(String[] arguments) {
        double findSqrt = 49; 
        //Number for Squareroot calculation*/
        double calabs = -55; 
        //Abousolute value calculation*/
        int value1 = 45;
        int value2 = 56;
        // Numbers for Maximum number calculation


        double result = sqrt(findSqrt);//Square root calculator
        if (result == -1)
            System.out.println("Squareroot of " + 
        findSqrt + " is: Undefined");
        else
            System.out.println("Squareroot of " +
        findSqrt + " is: " + result);

        double absAnswer = abs(calabs); //Absoulte value calculator
        System.out.println("Absolute value of " +
        calabs + " is " + absAnswer);

        max(value1, value2);//Max value calculator
        testSqrt();
    }
    /**
     * sqrt method calculates the square root of the
     * number by using Babylonian method
     *
     * @param      findSqrt    Number for which square root 
     *  		               is calculated.
     * @return                 Square root in double format 
     */
    public static double sqrt(double findSqrt) {
        if (findSqrt == 0 ) {
            double answer = 0;
            return answer;
        }
        if (findSqrt < 0)
            return -1;

        double seed = 1; 
        double epsilon = 0.000000001;
        boolean flag = true;
        boolean goOn = true;
        double answer = 0;
        /**
          *seed is the value which will be used for approximation
          *of Square root
          */
        while (flag) {
            if (seed * seed < findSqrt) {
                seed++;
            } else if (seed * seed == findSqrt) { //Case for perfect square root
                flag = false;
                answer = seed;
            } else {
                --seed;
            //Here we get the seed value which is close to the perfect square
                
                flag = false;

                while (goOn) {
                	//Babylonian method for getting square root
                    answer = (seed + (findSqrt / seed)) / 2;
                    seed = answer;
                    if (!((answer * answer - findSqrt)
                    		> epsilon)) { 
                        goOn = false;
                    }

                }
            }
        }
        return answer;
    }
    /**
     * abs method calculates the absolute value
     *
     * @param      findSqrt    number for which absolute value 
     *  		               is calculated.
     * @return                 Answer in double format
     * 
     */
    public static double abs(double calAbs) {
        if (calAbs < 0) {
            calAbs = calAbs * (-1);
            
        }
        return calAbs;
    }
    /**
     * abs method calculates the absolute value
     *
     * @param      max    Maximum number between 2 numbers 
     *  		              
     * 
     * 
     */
    public static void max(int value1, int value2) {
        if (value1 < value2)
            System.out.println(value2 + " is greater between "
        + value1 + " and " + value2);
        else
            System.out.println(value1 + " is greater between " 
        + value1 + " and " + value2);
    }
    /**
     * Various square root calculation test cases implemented in
     *  testSqrt() method.
     */
    static void testSqrt() {
        System.out.println("Running test cases below");
        if (0 != sqrt(0))
            System.out.println("Test 1: sqrt failed");
        else
            System.out.println("Test 1: Passed");
        if (0 != sqrt(-0))
            System.out.println("Test 2: sqrt failed");
        else
            System.out.println("Test 2: Passed");
        if (Double.NaN == sqrt(-1))
            System.out.println("Test 3: sqrt failed");
        else
            System.out.println("Test 3: Passed");
        if (Double.NaN == sqrt(Double.NaN))
            System.out.println("Test 4: sqrt failed");
        else
            System.out.println("Test 4: Passed");
        double result;
        //double aDouble;
        double epsilon = 0.000000001;

        double theDoubles[] = {1,2,3,4,5};
        for (int index = 0; index < theDoubles.length; index++) {
            result = sqrt(theDoubles[index]);
            if (abs(result * result - theDoubles[index]) > epsilon) {

                System.out.println("Test 5: sqrt failed: " + 
                (result * result - theDoubles[index]));

            } else
                System.out.println("Test 5: Passed");
        }
    }
}