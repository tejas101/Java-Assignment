/* BinarySum.java  
 * Version: 1
 *      
 * Revisions: 0
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * The program performs a summation of a given
 * two binary string inputs, while displaying 
 * the summation ouput in the binary, decimal,
 * and hexadecimal formats.
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */

public class BinarySum {
	public static int ToDecimal(int binary) {
		/**
		 * The ToDecimal method takes a binary number as
		 * an input and converts it into a decimal number 
		 * format.
		 * 
		 * @param binary String to be converted to decimal format.
		 */
		int decimal = 0;
		int power = 0;
		while(binary!=0)
        {
            decimal+=((binary%10)*Math.pow(2,power));
            binary = binary/10;
            power++;
        }
        return decimal;
    }
	
	public static String ToBinary(int sumNumber){
		/**
		 * The ToBinary function converts the number into
		 * a binary number format.
		 * 
		 * @param sumNumber number to be converted
		 */
		if (sumNumber == 0) {
	           return "0";
	       }
	       String binary = "";
	       while (sumNumber > 0) {
	           int temp = sumNumber % 2;
	           binary = temp + binary;
	           sumNumber = sumNumber / 2;
	       }
	       return binary;
    	
	}
	
	public static String ToHex(int sumNumber){
		/**
		 * The ToHex function converts the number into
		 * a hexadecimal number format.
		 * 
		 * @param sumNumber number to be converted
		 */
	 char hex[]={'0','1','2','3','4','5','6','7','8','9',
			 'A','B','C','D','E','F'};
	 String hexNumber = "";
	 if (sumNumber == 0) {
         return "0";
     }
	 while(sumNumber>0)
        {
            int temp = sumNumber%16;
            hexNumber = hex[temp] + hexNumber;
            sumNumber = sumNumber/16;
        }
	  return hexNumber;
	 }
	
	public static void SumCalculation(int binary1, int binary2) {
		/**
		 * The method SumCalculation takes in two number as parameters
		 * and calculates their sum. And prints of the desired output.
		 * 
		 * @param binary1 binary number
		 * @param binary2 binary number 
		 */
		int dec_1 = ToDecimal(binary1);
		int dec_2 = ToDecimal(binary2);
		System.out.printf("Adding %d + %d%n",dec_1, dec_2);
		int sum = dec_1 + dec_2;
		String binaryOutput = ToBinary(sum);
		String hexOutput = ToHex(sum);
		String output = String.format("%8s", binaryOutput).replace(' ', '0');
		System.out.printf("Sum in binary: b'%s'%n", output);
		System.out.printf("Sum in decimal: %d%n", sum);
		System.out.printf("Sum in hex: 0x%s", hexOutput);
	}
	 
	public static boolean CheckNumber(int binarynumber) {
		/**
		 * The method CheckNumber checks whether the parameter 
		 * number passed is of the correct binary format or not.
		 * It returns ture if correct, else false.
		 *  
		 * @param binaryNumber Binary number to be checked
		 * 					   for the correct binary number
		 * 					   format.
		 */
		boolean check = true;
        while(true){
            if(binarynumber == 0){
                break;
            } else {
                int temp = binarynumber%10;
                if(temp > 1){
                    check = false;
                    break;
                }
                binarynumber = binarynumber/10;
            }
        }
        return check;
	}
	
	public static void main(String[] args) {
		/**
		 * @param args Two binary numbers.
		 */
		if(args.length == 2) {
			int binaryNumber1 = Integer.parseInt(args[0]);
			int binaryNumber2 = Integer.parseInt(args[1]);
			if(binaryNumber1<0 || binaryNumber2<0) {
			 System.out.println("Enter the number in correct format");
			 System.exit(0);
			
			}
			if(CheckNumber(binaryNumber1) && CheckNumber(binaryNumber2)) {
				SumCalculation(binaryNumber1, binaryNumber2);
			}
			else {
				System.out.println("Enter a correct binary format number!");
			}
		}
		else {
			System.out.println("Enter correct number of arguments!");
		}
	}
}
